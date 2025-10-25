package com.KtWd.Rekall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KtWd.Rekall.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	Optional<Author> findByName(String authorName);
	
}
