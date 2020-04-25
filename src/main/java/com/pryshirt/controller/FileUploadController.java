package com.pryshirt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pryshirt.model.File;
import com.pryshirt.service.FileStorageService;

@RestController
@RequestMapping("/pryshirt")
public class FileUploadController {

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/upload")
	public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<File>(new File(fileStorageService.storeFile(file),
				fileStorageService.getFileStorageLocation() + "/" + fileStorageService.storeFile(file), file.getName(),
				file.getSize()), HttpStatus.OK);
	}

	@PostMapping("/uploads")
	public ResponseEntity<List<File>> uploadFiles(@RequestParam("files") MultipartFile[] multipartFiles) {
		List<File> files = new ArrayList<>();
		for (MultipartFile file : multipartFiles) {
			files.add(new File(fileStorageService.storeFile(file),
					fileStorageService.getFileStorageLocation() + "/" + fileStorageService.storeFile(file),
					file.getName(), file.getSize()));
		}
		return new ResponseEntity<List<File>>(files, HttpStatus.OK);
	}

	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
