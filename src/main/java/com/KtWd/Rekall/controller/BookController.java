package com.KtWd.Rekall.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.KtWd.Rekall.Payload.BookDTO;
import com.KtWd.Rekall.Payload.BookResponse;
import com.KtWd.Rekall.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBooks(){
		BookResponse books = bookService.FindAllBooks();
		return new ResponseEntity<>(books,HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) throws BadRequestException{
		BookDTO savedBook = bookService.addBook(bookDTO);
		return new ResponseEntity<>(savedBook,HttpStatus.OK);
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file){
		ResponseEntity response = bookService.ConvertFile(file);
		return response;
	}

}
