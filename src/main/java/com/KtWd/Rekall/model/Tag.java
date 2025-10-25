package com.KtWd.Rekall.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tags")
@Table(name = "tags")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tag {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long tagId;

	@EqualsAndHashCode.Include
	private String tagName;
	

	@ManyToMany(mappedBy = "tags")
	@ToString.Exclude
	private Set<Book> books = new HashSet<>();
    private int bookCount = 0;

    public Tag(String tagName) {
        this.tagName = tagName;

    }

	public void addBook(Book book) {
        if (book == null) return;
        if (books.contains(book)) return;
        books.add(book);
    }
	public void removeBook(Book book) {
        if (book == null) return;
        books.remove(book);
    }
	@Override
    public String toString() {
        String bookTitles = books.isEmpty()
                ? "[]"
                : books.stream()
                       .map(Book::getTitle)
                       .toList()
                       .toString();

        return "Tag(id=%d, tagName='%s', books=%s)"
                .formatted(tagId, tagName, bookTitles);
    }


}
