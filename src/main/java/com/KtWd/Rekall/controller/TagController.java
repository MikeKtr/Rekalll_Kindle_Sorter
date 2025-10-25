package com.KtWd.Rekall.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KtWd.Rekall.Payload.BookDTO;
import com.KtWd.Rekall.Payload.TagDTO;
import com.KtWd.Rekall.Payload.TagResponse;
import com.KtWd.Rekall.service.BookService;
import com.KtWd.Rekall.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

	@Autowired
	public TagService tagService;
	@Autowired
	public BookService bookService;
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTags(){
		TagResponse response = tagService.getAll();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}


	@PostMapping("/addBook")
	public ResponseEntity<?> addTagToBook(@RequestParam String tagName,@RequestParam Long bookId) throws BadRequestException{
		
		BookDTO response = bookService.addTagToBook(tagName, bookId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addTag(@RequestParam String tagName) throws BadRequestException{
		TagDTO response = tagService.addNewTag(tagName);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
