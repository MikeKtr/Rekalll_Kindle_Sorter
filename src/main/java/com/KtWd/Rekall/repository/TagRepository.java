package com.KtWd.Rekall.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KtWd.Rekall.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {

	public Optional<Tag> findTagByTagName(String tagName);
    public Boolean existsByTagName(String tagName);
}
