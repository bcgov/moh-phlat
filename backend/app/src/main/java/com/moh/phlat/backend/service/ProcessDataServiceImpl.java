package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.moh.phlat.backend.service.dto.ReportSummary;
import com.moh.phlat.backend.service.RowStatusService;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {

    @Autowired
    ProcessDataRepository processDataRepository;

    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus) {

        if (StringUtils.hasText(rowStatus)) {
            return processDataRepository.getProcessDataWithMessages(controlTableId, rowStatus);
        } else {
            return processDataRepository.getProcessDataWithMessages(controlTableId);
        }

    }

    public List<ReportSummary> getReportSummary(Long controlTableId) {
		String rowStatus;
		Long rcount;

		List<ReportSummary> items = new ArrayList<ReportSummary>();

		rowStatus ="TOTAL INPUT RECORDS";
		rcount = processDataRepository.countByControlTableId(controlTableId);
		ReportSummary rs1 = new ReportSummary();
		rs1.setRowStatus((String) rowStatus);
		rs1.setRowStatusCount((Long)rcount);
		items.add(rs1);
		
		rowStatus ="TOTAL INITIAL ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.INITIAL);
		ReportSummary rs2 = new ReportSummary();
		rs2.setRowStatus((String) rowStatus);
		rs2.setRowStatusCount((Long)rcount);
		items.add(rs2);		
		
		rowStatus ="TOTAL DO_NOT_LOAD ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.DO_NOT_LOAD);
		ReportSummary rs3 = new ReportSummary();
		rs3.setRowStatus((String) rowStatus);
		rs3.setRowStatusCount((Long)rcount);
		items.add(rs3);		
		
		rowStatus ="TOTAL INVALID ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.INVALID);
		ReportSummary rs4 = new ReportSummary();
		rs4.setRowStatus((String) rowStatus);
		rs4.setRowStatusCount((Long)rcount);
		items.add(rs4);		

		rowStatus ="TOTAL VALID ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.VALID);
		ReportSummary rs5 = new ReportSummary();
		rs5.setRowStatus((String) rowStatus);
		rs5.setRowStatusCount((Long)rcount);
		items.add(rs5);		
		
		rowStatus ="TOTAL WARNING ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.WARNING);
		ReportSummary rs6 = new ReportSummary();
		rs6.setRowStatus((String) rowStatus);
		rs6.setRowStatusCount((Long)rcount);
		items.add(rs6);				
		
		rowStatus ="TOTAL COMPLETED ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.COMPLETED);
		ReportSummary rs7 = new ReportSummary();
		rs7.setRowStatus((String) rowStatus);
		rs7.setRowStatusCount((Long)rcount);
		items.add(rs7);	
		
		rowStatus ="TOTAL POTENTIAL_DUPLICATE ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.POTENTIAL_DUPLICATE);
		ReportSummary rs8 = new ReportSummary();
		rs8.setRowStatus((String) rowStatus);
		rs8.setRowStatusCount((Long)rcount);
		items.add(rs8);	
		
		rowStatus ="TOTAL LOAD_ERROR ROWSTATUS";
		rcount = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.LOAD_ERROR);
		ReportSummary rs9 = new ReportSummary();
		rs9.setRowStatus((String) rowStatus);
		rs9.setRowStatusCount((Long)rcount);
		items.add(rs9);			
		
		// adding message code and desc to the list

		List<Object[]> listMsg = processDataRepository.getProcessDataWithMessageCodeCount(controlTableId);

		for (Object[] msg : listMsg){
			String code = (String) msg[1];
			if (StringUtils.hasText(code)) {
				ReportSummary rsMessage = new ReportSummary();
				rowStatus = (String) msg[0] + " " + (String) msg[1] + " " + (String) msg[2];
				rcount = (Long) msg[3];
				rsMessage.setRowStatus((String) rowStatus);
	 			rsMessage.setRowStatusCount((Long)rcount);
				items.add(rsMessage);	
			}	
		}   
		return items;
	}
}
