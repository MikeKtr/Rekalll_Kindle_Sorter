package com.KtWd.Rekall.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "authors")
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long authorId;
	
	private String name;

	@OneToMany(mappedBy="bookId",cascade={CascadeType.DETACH,CascadeType.MERGE})
	private Set<Book> books;
	
}
