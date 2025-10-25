package com.KtWd.Rekall.service;

import org.apache.coyote.BadRequestException;

import com.KtWd.Rekall.Payload.TagDTO;
import com.KtWd.Rekall.Payload.TagResponse;

public interface TagService {
	public TagResponse getAll();
	public TagDTO addNewTag(String tagName) throws BadRequestException;
}
