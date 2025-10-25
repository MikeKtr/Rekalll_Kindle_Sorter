package com.KtWd.Rekall.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KtWd.Rekall.Payload.BookDTO;
import com.KtWd.Rekall.Payload.BookResponse;
import com.KtWd.Rekall.model.Author;
import com.KtWd.Rekall.model.Book;
import com.KtWd.Rekall.model.Quote;
import com.KtWd.Rekall.model.Tag;
import com.KtWd.Rekall.repository.AuthorRepository;
import com.KtWd.Rekall.repository.BookRepository;
import com.KtWd.Rekall.repository.QuoteRepository;
import com.KtWd.Rekall.repository.TagRepository;

import jakarta.transaction.Transactional;


@Service
public class BookServiceImpl implements BookService{



	@Autowired
	BookRepository bookRepo;
	@Autowired
	QuoteRepository quoteRepo;
	@Autowired
	AuthorRepository authorRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TagRepository tagRepository;

	public static LocalDateTime processDateLine(String dateLine){
		dateLine = dateLine.substring(dateLine.lastIndexOf(',') + 2);
        System.out.println(dateLine);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm:ss", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateLine,format);
        return dateTime;
        }

	public static Long processPageLine(String dateLine){
		Matcher matcher = Pattern.compile("\\d+").matcher(dateLine);
		matcher.find();
		Long page = Long.valueOf(matcher.group());
		return page;
	}

	@Override
	public ResponseEntity<?> ConvertFile(MultipartFile file) {
		
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				
				String titleLine = "";
				String dateLine = "";
				List<String> quoteList = new ArrayList<>();


				while (line != null && !line.equals("==========")) {

					
					System.out.println("line = " + line);
					System.out.println("title = " + titleLine);
					System.out.println("date = " + dateLine);
					

					if (titleLine.isEmpty()) {
						titleLine = line;

					} else if (dateLine.isEmpty()) {
						dateLine = line;

					} else {
						quoteList.add(line);
					}
					line = reader.readLine();
				}

				if(titleLine.isEmpty() || dateLine.isEmpty() || quoteList.isEmpty()){
					continue;
				}
				System.out.println("tutaj");
				String title = titleLine.replaceAll("\\s*\\(.*\\)$", "").trim();
                String authorName = titleLine.replaceAll("^.*\\((.*)\\)$", "$1").trim();

				Author author = authorRepo.findByName(authorName).orElseGet(() -> {
					Author a = new Author();
					a.setName(authorName);
					return authorRepo.save(a);
				});

				Book book = bookRepo.findByTitle(title).orElseGet(() -> {
					Book b = new Book(title,author);
					return bookRepo.save(b);
				});
				String FullQuote = "";
				for(String quoteText : quoteList){
					FullQuote += quoteText;
				}

				LocalDateTime saveData= processDateLine(dateLine);
				Long page = processPageLine(dateLine);
				Quote q = new Quote(FullQuote,book,saveData,page);
				System.out.println(dateLine);
				System.out.println("dodano cytat");
				quoteRepo.save(q);

			}
		}
		catch (IOException e) {
			return new ResponseEntity<>("Couldnt open file",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Data saved to Data Base",HttpStatus.OK);
	}

	@Override
	public BookResponse FindAllBooks() {
		List<Book> books = bookRepo.findAll();
		List<BookDTO> response = books.stream().map(book -> modelMapper.map(book,BookDTO.class)).toList();
		BookResponse bookResponse = new BookResponse();
		bookResponse.setBooks(response);
		return bookResponse;
	}

	@Override
	public BookDTO addBook(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO,Book.class);
		Book savedBook = bookRepo.save(book);
		BookDTO bookDTOResponse = modelMapper.map(savedBook, BookDTO.class);
		return bookDTOResponse;

	}
	
	@Override
	@Transactional
	public BookDTO addTagToBook(String tagName, Long bookId) throws BadRequestException{
		System.out.println("chuj");
		Tag foundTag = tagRepository.findTagByTagName(tagName)
		.orElseThrow(() -> new BadRequestException("Tag with name " + tagName + " doesn't exists"));

		
		Book foundBook = bookRepo.findBookByBookId(bookId)
		.orElseThrow(() -> new BadRequestException("Book with Id " + bookId + " doesn't exists"));

		
		foundBook.addTag(foundTag);
		// foundTag.addBook(foundBook); 
		System.out.println("chuj2");
		
        Book responseBook = bookRepo.save(foundBook);
		System.out.println("chuj3");

		return modelMapper.map(responseBook,BookDTO.class);
	}

	
}
