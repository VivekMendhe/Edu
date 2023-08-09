package com.edu.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

//    public final String UPLOAD_DIR = "C:\\Users\\vivek\\eclipse-workspace-2\\EDU\\src\\main\\resources\\static\\image";

	public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();

	public FileUploadHelper() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean uploadFile(MultipartFile file) {

		boolean f = false;

		try {
			// read the file
//            InputStream is = file.getInputStream();
//            byte data[] = new byte[is.available()];
//            is.read(data);

			// write the file
			Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());

//            Files.write(path, data);

			// use Files.copy() to upload the file
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}
}
