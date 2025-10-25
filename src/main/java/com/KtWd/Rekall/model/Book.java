package com.KtWd.Rekall.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "books")
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long bookId;

	@EqualsAndHashCode.Include
	private String title;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;

	@OneToMany(mappedBy = "book",cascade={CascadeType.MERGE,CascadeType.PERSIST})
	private Set<Quote> quotes = new HashSet<>();

	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(name = "book_tags",
				joinColumns = @JoinColumn(name = "book_id"),
				inverseJoinColumns = @JoinColumn(name = "tag_id"))
	@ToString.Exclude
	private Set<Tag> tags = new HashSet<>();

	public Book(String title, Author author) {
		this.title = title;
		this.author = author;
	}

    public void addTag(Tag tag) {
        if (tag == null) {System.out.println("null"); return;}
        if (tags.contains(tag)) {System.out.println("jest już") ; return;}
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        if (tag == null) return;
        tags.remove(tag);
    }


    @Override
    public String toString() {
        String tagNames = tags.isEmpty()
                ? "[]"
                : tags.stream()
                      .map(Tag::getTagName)
                      .toList()
                      .toString();

        return "Book(id=%d, title='%s', tags=%s)"
                .formatted(bookId, title, tagNames);
    }
	// // @ToString.Include(name = "tagNames")
	// private String tagNames(){
	// 	return this.getTags().stream().map(Tag::getTagName).toList().toString();
	// }

	

	

}
