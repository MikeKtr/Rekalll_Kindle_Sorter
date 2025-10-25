package com.KtWd.Rekall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KtWd.Rekall.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long>{
	
}
