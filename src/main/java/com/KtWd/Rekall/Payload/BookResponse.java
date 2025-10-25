package com.KtWd.Rekall.Payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
	private List<BookDTO> books;

	// private Long totalElements;
	// private Long totalFavourites;
	// private Long collectionCount;
}
