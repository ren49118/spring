package com.mmit.jpit;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class fileUpload {
	public static void saveFile(String fileDir,String fileName,MultipartFile file) {
		try {
			Path uploadPath = Paths.get(fileDir);
			
			if(!Files.exists(uploadPath)) { // if not exists
				Files.createDirectories(uploadPath);
			}
			InputStream in = file.getInputStream();
			Path uploadPathName = uploadPath.resolve(fileName);
			Files.copy(in, uploadPathName, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			
		}
	}
}
