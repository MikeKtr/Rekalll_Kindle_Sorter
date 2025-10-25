package com.KtWd.Rekall.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "quotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quotes")
public class Quote {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quoteId;

	private String quoteContent;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	private LocalDateTime saveDateTime;

	private Long pageNumber;

	public Quote(String quoteContent, Book book, LocalDateTime saveDateTime, Long pageNumber) {
		this.quoteContent = quoteContent;
		this.book = book;
		this.saveDateTime = saveDateTime;
		this.pageNumber = pageNumber;
	}
	
	// private Set<Collection> collections;

	
}
