package com.moh.phlat.backend.controller;

import com.moh.phlat.backend.model.ColumnsDisplayPreference;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.ColumnsDisplayPreferenceService;
import com.moh.phlat.backend.validation.DisplayColumnCreateGroup;
import com.moh.phlat.backend.validation.DisplayColumnsUpdateGroup;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/columns-display-preference")
@CrossOrigin(origins = "*")
public class ColumnsDisplayPreferenceController {

    private static final Logger logger = LoggerFactory.getLogger(ColumnsDisplayPreferenceController.class);
    private static final String MESSAGE_KEY_PREFERENCE_MODIFICATION_DENIED = "columns.displayPreference.modificationDenied";

    private final String MESSAGE_KEY_PREFERENCE_NOT_FOUND = "columns.displayPreference.not.found";
    private final String MESSAGE_KEY_PREFERENCE_ALREADY_EXIST = "columns.displayPreference.alreadyExists";


    @Autowired
    private MessageSource messageSource;


    @Autowired
    private ColumnsDisplayPreferenceService preferencesService;

    @PostMapping("")
    public ResponseEntity<ResponseMessage> saveOrUpdatePreferences(
            @Validated(DisplayColumnCreateGroup.class) @RequestBody ColumnsDisplayPreference columnsDisplayPreference) {


        //if id is passed, it is ignored.
        columnsDisplayPreference.setId(null);


        String userId = AuthenticationUtils.getAuthenticatedUserId();
        columnsDisplayPreference.setUserId(userId);
        setAuditColumns(columnsDisplayPreference, userId);

        //check duplicate based on user id and page name
        boolean isExist = preferencesService.isExist(userId,
                                                     columnsDisplayPreference.getPageName());

        if (isExist) {
            logger.info("Preference record is duplicate...");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(new ResponseMessage("error", 409,
                                                           messageSource.getMessage(MESSAGE_KEY_PREFERENCE_ALREADY_EXIST,
                                                                                    null,
                                                                                    LocaleContextHolder.getLocale()), ""));
        }

        ColumnsDisplayPreference savedPreference = preferencesService.saveOrUpdatePreferences(columnsDisplayPreference);
        logger.info("created Preference record ...");
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new ResponseMessage("success",
                                                       200,
                                                       "", savedPreference));

    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updatePreferences(@PathVariable("id") long id,
                                                             @Validated(DisplayColumnsUpdateGroup.class) @RequestBody ColumnsDisplayPreference columnsDisplayPreference) {


        ColumnsDisplayPreference existingDisplayPreference = preferencesService.getPreferences(id);

        if (existingDisplayPreference != null) {
            logger.info("Preference record found...");

            String userId = AuthenticationUtils.getAuthenticatedUserId();

            boolean isOwner = userId.equals(existingDisplayPreference.getUserId());

            //check if user is not modifying someone else preference
            if (!isOwner) {
                logger.info("User sending the request cannot modify the preference details of the other user...");
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                     .body(new ResponseMessage("error",
                                                               403,
                                                               messageSource.getMessage(MESSAGE_KEY_PREFERENCE_MODIFICATION_DENIED,
                                                                                        null,
                                                                                        LocaleContextHolder.getLocale()), ""));
            }


            //only display columns can be modified by user
            existingDisplayPreference.setDisplayColumns(columnsDisplayPreference.getDisplayColumns());
            setAuditColumns(existingDisplayPreference, userId);
            existingDisplayPreference.setId(id);
            ColumnsDisplayPreference updatedPreference = preferencesService.saveOrUpdatePreferences(existingDisplayPreference);
            logger.info("udpated Preference record ...");
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ResponseMessage("success",
                                                           200,
                                                           "", updatedPreference));

        }
        logger.info("preference details not found for updation...");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ResponseMessage("error",
                                                       404,
                                                       messageSource.getMessage(MESSAGE_KEY_PREFERENCE_NOT_FOUND,
                                                                                null,
                                                                                LocaleContextHolder.getLocale()), ""));
    }


    @GetMapping("/{pageName}")
    public ResponseEntity<ResponseMessage> getPreferences(@PathVariable String pageName) {
        String userId = AuthenticationUtils.getAuthenticatedUserId();
        ColumnsDisplayPreference columnsDisplayPreference = preferencesService.getPreferences(userId, pageName);
        if (columnsDisplayPreference != null) {
            logger.info("preference details found ...");
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ResponseMessage("success",
                                                           200,
                                                           "", columnsDisplayPreference));

        }

        logger.info("preference details not found ...");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ResponseMessage("error",
                                                       404,
                                                       messageSource.getMessage(MESSAGE_KEY_PREFERENCE_NOT_FOUND,
                                                                                null,
                                                                                LocaleContextHolder.getLocale()), ""));


    }

    private void setAuditColumns(ColumnsDisplayPreference columnsDisplayPreference, String userId) {
        if (columnsDisplayPreference.getId() != null) {
            columnsDisplayPreference.setUpdatedBy(userId);
            columnsDisplayPreference.setUpdatedAt(new Date());
        } else {
            columnsDisplayPreference.setCreatedBy(userId);
            columnsDisplayPreference.setCreatedAt(new Date());
        }
    }


}
