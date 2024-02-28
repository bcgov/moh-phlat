package com.moh.phlat.backend.testsupport.factories;

import com.moh.phlat.backend.model.Control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ControlTableFactory {

    public static List<Control> createControlList() {
        List<Control> controlList = new ArrayList<>();

        // Create and add three Control objects to the list
        controlList.add(createControl(1L, "File1.txt", "user1", createDate(2024, Calendar.JANUARY, 1),
                                      createDate(2024, Calendar.JANUARY, 26), null, "Label1",
                                      true, false, true, false,
                                      true, false, true,
                                      "STATUS1", createDate(2024, Calendar.FEBRUARY, 1),
                                      "Admin1", null, null));

        controlList.add(createControl(2L, "File2.txt", "user2", createDate(2024, Calendar.FEBRUARY, 1),
                                      createDate(2024, Calendar.FEBRUARY, 2),
                                      null, "Label2", false, true,
                                      false, true, false,
                                      true, false, "STATUS2", createDate(2024, Calendar.MARCH, 4),
                                      "Admin2", createDate(2024, Calendar.MARCH, 5), "Admin2"));

        controlList.add(createControl(3L, "File3.txt", "user3", null,
                                      createDate(2024, Calendar.JANUARY, 26), null,
                                      "Label3", true, true, false,
                                      true, true, true,
                                      true, "STATUS3", createDate(2024, Calendar.APRIL, 1),
                                      "Admin3", null, null));

        return controlList;
    }

    private static Control createControl(Long id, String fileName, String userId, Date fileExtractedDate,
                                         Date processStartDate, Date processEndDate,
                                         String batchLabelName, Boolean loadTypeFacility, Boolean loadTypeHds,
                                         Boolean loadTypeOrg,
                                         Boolean loadTypeOFRelationship, Boolean loadTypeOORelationship,
                                         Boolean loadTypeIORelationship,
                                         Boolean loadTypeWOXref, String statusCode, Date createdAt, String createdBy,
                                         Date updatedAt,
                                         String updatedBy) {
        return Control.builder()
                      .id(id)
                      .fileName(fileName)
                      .userId(userId)
                      .fileExtractedDate(fileExtractedDate)
                      .processStartDate(processStartDate)
                      .processEndDate(processEndDate)
                      .batchLabelName(batchLabelName)
                      .loadTypeFacility(loadTypeFacility)
                      .loadTypeHds(loadTypeHds)
                      .loadTypeOrg(loadTypeOrg)
                      .loadTypeOFRelationship(loadTypeOFRelationship)
                      .loadTypeOORelationship(loadTypeOORelationship)
                      .loadTypeIORelationship(loadTypeIORelationship)
                      .loadTypeWOXref(loadTypeWOXref)
                      .statusCode(statusCode)
                      .createdAt(createdAt)
                      .createdBy(createdBy)
                      .updatedAt(updatedAt)
                      .updatedBy(updatedBy)
                      .build();


    }

    private static Date createDate(int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(year, month, dayOfMonth);
        return calendar.getTime();
    }

}
