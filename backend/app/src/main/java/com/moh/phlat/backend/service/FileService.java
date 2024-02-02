package com.moh.phlat.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	boolean hasCsvFormat(MultipartFile file, String tableName);

	void processAndSaveData(MultipartFile file, Long controlTableId);
}
