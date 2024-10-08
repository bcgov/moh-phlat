package com.moh.phlat.backend.controller;

import com.moh.phlat.backend.model.ColumnsDisplayPreference;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.ColumnsDisplayPreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/columns-display-preference")
@CrossOrigin(origins = "*")
public class ColumnsDisplayPreferenceController {

    private static final Logger logger = LoggerFactory.getLogger(ColumnsDisplayPreferenceController.class);
    private static final String MESSAGE_KEY_PREFERENCE_INVALID_VIEW_NAME = "columns.displayPreference.invalidViewName";


    @Value("#{'${columns-display-preference.allowed-viewNames}'.split(',')}")
    private List<String> allowedViewNames;

    @Autowired
    private MessageSource messageSource;


    @Autowired
    private ColumnsDisplayPreferenceService preferencesService;


    @Operation(description = "Creates or updates the user preference for column display on a specified view. Supported views include:" +
            " file-task-management, process-data-management, source-data-management, and status-codes-management")
    @PutMapping(value = "/{viewName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
    public ResponseEntity<ResponseMessage> saveOrupdatePreferences(@PathVariable("viewName") String viewName,
                                                                   @Valid @RequestBody ColumnsDisplayPreference columnsDisplayPreference) {

        if (!isValidViewName(viewName)) {
            return badRequestResponse(messageSource.getMessage(
                    MESSAGE_KEY_PREFERENCE_INVALID_VIEW_NAME,
                    allowedViewNames.toArray(),
                    LocaleContextHolder.getLocale()));
        }

        String userId = AuthenticationUtils.getAuthenticatedUserId();

        ColumnsDisplayPreference existingDisplayPreference = preferencesService.getPreferences(userId, viewName);

        if (existingDisplayPreference != null) {
            logger.info("Preference record found...");
            ColumnsDisplayPreference savedPreference = updateDisplayColumns(existingDisplayPreference, columnsDisplayPreference, userId);
            logger.info("Updated Preference record ...");
            return okResponse("User's Column Preferences updated", savedPreference);
        } else {
            logger.info("Preference record not found. Creating ...");
            ColumnsDisplayPreference savedPreference = createPreference(viewName, columnsDisplayPreference, userId);
            logger.info("Preference record created...");
            return okResponse("User's Column Preferences created", savedPreference);
        }
    }


    @Operation(description = "Returns the user preference for column display on a specified view. Supported views include:" +
            " file-task-management, process-data-management, source-data-management, and status-codes-management")
    @GetMapping(value = "/{viewName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
    public ResponseEntity<ResponseMessage> getPreferences(@PathVariable String viewName) {
        String userId = AuthenticationUtils.getAuthenticatedUserId();
        ColumnsDisplayPreference columnsDisplayPreference = preferencesService.getPreferences(userId, viewName);
        String message = (columnsDisplayPreference != null) ? "User saved column display preferences found" : "User saved column display preferences not found";
        logger.info(message);

        ColumnsDisplayPreference preference = (columnsDisplayPreference != null) ? columnsDisplayPreference : new ColumnsDisplayPreference();

        if (columnsDisplayPreference == null) {
            preference.setDisplayColumns(List.of());
            preference.setViewName(viewName);
        }
        return okResponse(message, preference);
    }


    private boolean isValidViewName(String viewName) {
        return StringUtils.hasText(viewName) && allowedViewNames.contains(viewName);
    }

    private ResponseEntity<ResponseMessage> badRequestResponse(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new ResponseMessage("error", 400, message, null, ""));
    }


    private ResponseEntity<ResponseMessage> okResponse(String message, ColumnsDisplayPreference preference) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new ResponseMessage("success", 200, message, null, preference));
    }

    private ColumnsDisplayPreference createPreference(
            String viewName, ColumnsDisplayPreference columnsDisplayPreference, String userId) {
        columnsDisplayPreference.setId(null);
        columnsDisplayPreference.setUserId(userId);
        columnsDisplayPreference.setViewName(viewName);
        setAuditColumns(columnsDisplayPreference, userId);
        return preferencesService.saveOrUpdatePreferences(columnsDisplayPreference);

    }

    private ColumnsDisplayPreference updateDisplayColumns(
            ColumnsDisplayPreference existingDisplayPreference,
            ColumnsDisplayPreference columnsDisplayPreference,
            String userId) {
        existingDisplayPreference.setDisplayColumns(columnsDisplayPreference.getDisplayColumns());
        setAuditColumns(existingDisplayPreference, userId);
        return preferencesService.saveOrUpdatePreferences(existingDisplayPreference);

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
