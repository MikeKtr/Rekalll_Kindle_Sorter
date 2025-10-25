package com.KtWd.Rekall.service;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.KtWd.Rekall.Payload.BookDTO;
import com.KtWd.Rekall.Payload.BookResponse;

public interface BookService<Book> {
	
	ResponseEntity<?> ConvertFile(MultipartFile file);

	BookResponse FindAllBooks();

	BookDTO addBook(BookDTO bookDTO) throws BadRequestException;

	BookDTO addTagToBook(String tagName, Long bookId) throws BadRequestException;
}
