package com.KtWd.Rekall.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDTO {
	private Long quoteId;
	private String quoteContent;
}
