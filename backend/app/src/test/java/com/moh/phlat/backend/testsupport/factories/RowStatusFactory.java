package com.moh.phlat.backend.testsupport.factories;

import com.moh.phlat.backend.model.RowStatus;

import java.util.ArrayList;
import java.util.List;

public class RowStatusFactory {
    public static List<RowStatus> createTwoRowStatuses() {
        List<RowStatus> rowStatuses = new ArrayList<>();

        RowStatus rowStatus1 = createRowStatus(1L, "INITIAL",
                                               "Set status for all records when the record is initially loaded into the phat table"
        );

        RowStatus rowStatus2 = createRowStatus(2L, "DO_NOT_LOAD",
                                               "Set status if do not load flag is set to y"
        );
        rowStatuses.add(rowStatus1);
        rowStatuses.add(rowStatus2);

        return rowStatuses;
    }

    private static RowStatus createRowStatus(Long id, String code, String desc) {
        return RowStatus.builder()
                        .id(id)
                        .code(code)
                        .description(desc)
                        .isDeleted(false)
                        .build();

    }
}

