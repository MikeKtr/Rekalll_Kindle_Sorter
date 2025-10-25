package com.KtWd.Rekall.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KtWd.Rekall.Payload.QuoteDTO;
import com.KtWd.Rekall.Payload.QuoteResponse;
import com.KtWd.Rekall.model.Quote;
import com.KtWd.Rekall.repository.QuoteRepository;

@Service
public class  QuoteServiceImpl implements QuoteService {

	@Autowired
	QuoteRepository quoteRepo;

	@Autowired
	ModelMapper modelMapper;

    @Override
    public QuoteResponse getAllQuotes() {
        List<Quote> quotes = quoteRepo.findAll();
		List<QuoteDTO> quotesDTO = quotes.stream().map(quote -> modelMapper.map(quote,QuoteDTO.class)).toList();
		QuoteResponse response = new QuoteResponse();
		response.setQuotes(quotesDTO);
		return response;
	}

}
