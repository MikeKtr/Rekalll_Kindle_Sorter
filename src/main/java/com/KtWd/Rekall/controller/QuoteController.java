package com.KtWd.Rekall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KtWd.Rekall.Payload.QuoteResponse;
import com.KtWd.Rekall.service.QuoteService;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

	@Autowired
	QuoteService quoteService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllQuotes(){
		QuoteResponse quotes = quoteService.getAllQuotes();
		return new ResponseEntity<>(quotes,HttpStatus.OK);
	}
}
