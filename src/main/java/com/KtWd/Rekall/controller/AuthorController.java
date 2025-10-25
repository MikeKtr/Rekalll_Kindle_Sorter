package com.KtWd.Rekall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KtWd.Rekall.Payload.AuthorResponse;
import com.KtWd.Rekall.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	@Autowired
	AuthorService authorService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllAuthors(){
		AuthorResponse response = authorService.getAll();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
