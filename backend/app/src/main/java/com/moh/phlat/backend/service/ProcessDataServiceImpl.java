package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {

    @Autowired
    ProcessDataRepository processDataRepository;

    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus) {

        if (StringUtils.hasText(rowStatus)) {
            return processDataRepository.getProcessDataWithMessages(controlTableId);
        } else {
            return processDataRepository.getProcessDataWithMessages(controlTableId, rowStatus);
        }


    }
}
