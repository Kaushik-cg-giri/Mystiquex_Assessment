package com.movieflix.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.service.FileServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileServiceImpl service;
	
	@Value("${project.poster}")
	private String path;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException{
		//upload file	
		String uploadFileName = service.uploadFile(path, file);
		
		return ResponseEntity.ok("File Uploaded :"+ uploadFileName);	
	}
	
	@GetMapping("/{fileName}")
	public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		
		InputStream resultFile = service.getResourceFile(path, fileName);
		response.setContentType(org.springframework.http.MediaType.IMAGE_PNG_VALUE);
		org.springframework.util.StreamUtils.copy(resultFile, response.getOutputStream());
		
	}
}
