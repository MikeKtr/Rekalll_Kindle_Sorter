package com.KtWd.Rekall.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KtWd.Rekall.Payload.AuthorDTO;
import com.KtWd.Rekall.Payload.AuthorResponse;
import com.KtWd.Rekall.model.Author;
import com.KtWd.Rekall.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepo;
	@Autowired
	ModelMapper modelMapper;

    @Override
    public AuthorResponse getAll() {
        List<Author> authors = authorRepo.findAll();
		List<AuthorDTO> authorsDTO = authors.stream().map(author -> modelMapper.map(author,AuthorDTO.class)).toList(); 
		AuthorResponse response = new AuthorResponse();
		response.setAuthors(authorsDTO);
		return response;
    }

	
	
}
