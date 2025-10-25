package com.KtWd.Rekall.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KtWd.Rekall.Payload.TagDTO;
import com.KtWd.Rekall.Payload.TagResponse;
import com.KtWd.Rekall.model.Tag;
import com.KtWd.Rekall.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService{
	@Autowired
	public TagRepository tagRepository;

	@Autowired
	public ModelMapper modelMapper;
	
    @Override
	public TagResponse getAll(){
		List<Tag> tags = tagRepository.findAll();
		for(Tag tag : tags){
			tag.setBookCount(tag.getBooks().size());
		}
		List<TagDTO> tagsDTO = tags.stream().map(tag -> modelMapper.map(tag,TagDTO.class)).toList();
		return new TagResponse(tagsDTO);
	}

    @Override
    public TagDTO addNewTag(String tagName) throws BadRequestException{
        if(tagRepository.existsByTagName(tagName)){
			throw new BadRequestException("Tag with that name already exists");
		}
		Tag newTag = new Tag(tagName);
		Tag responseTag = tagRepository.save(newTag);
		System.out.println(responseTag.toString());
		TagDTO response = modelMapper.map(responseTag,TagDTO.class);
		return response;
	}

	
	
}
