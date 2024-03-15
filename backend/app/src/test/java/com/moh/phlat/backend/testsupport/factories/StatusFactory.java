package com.moh.phlat.backend.testsupport.factories;

import com.moh.phlat.backend.model.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusFactory {
    public static List<Status> createTwoStatuses() {
        List<Status> statusList = new ArrayList<>();

        Status status1 = createStatus(1L, "System", "INITIAL",
                                      "Set status for all records when the record is initially loaded into the phat table"
        );

        Status status2 = createStatus(2L, "UserDefined", "DO_NOT_LOAD",
                                      "Set status if do not load flag is set to y"
        );
        statusList.add(status1);
        statusList.add(status2);

        return statusList;
    }

    private static Status createStatus(Long id, String type, String code, String desc) {
        return Status.builder()
                     .id(id)
                     .code(code)
                     .description(desc)
                     .type(type)
                     .isDeleted(false)
                     .build();


    }
}

