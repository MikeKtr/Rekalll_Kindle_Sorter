package com.KtWd.Rekall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KtWd.Rekall.model.Book;


public interface  BookRepository extends JpaRepository<Book,Long>{

	Optional<Book> findByTitle(String title);
	Optional<Book> findBookByBookId(Long id);
}
