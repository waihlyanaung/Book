package com.example.book.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import java.awt.PageAttributes.MediaType;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.book.model.Book;
import com.example.book.model.BookType;
import com.example.book.service.BookServiceimpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class Bookcontrollertest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
private BookServiceimpl serviceimpl;
	
	
	@Test
	void api_getAllBooks_should_return_books() throws Exception {
	    // Prepare Service Mock
	    List<Book> mockData = new ArrayList<>();
	    Book book1 = new Book(
	        1, "The Great Gatsby", "F. Scott Fitzgerald", 10, BookType.romance,
	        LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()
	    );
	    Book book2 = new Book(
	        2, "Moby-Dick", "Herman Melville", 12, BookType.romance,
	        LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()
	    );
	    mockData.add(book1);
	    mockData.add(book2);
	    when(serviceimpl.getAll()).thenReturn(mockData);

	    // Call API And Test
	    mockMvc.perform(get("/book")).andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(
	            content().contentTypeCompatibleWith(
	                MediaType.APPLICATION_JSON
	            )
	        ).andExpect(jsonPath("$", hasSize(2)))
	        .andExpect(jsonPath("$[0].name", is(book1.getName())))
	        .andExpect(jsonPath("$[0].price", is(book1.getPrice())))
	        .andExpect(jsonPath("$[1].name", is(book2.getName())))
	        .andExpect(jsonPath("$[1].price", is(book2.getPrice())));
	}
	
	@Test
	void api_getBook_should_return_one_book() throws Exception {

	    // Prepare Service Mock
	    Book mockData = new Book(
	        1, "The Great Gatsby", "F. Scott Fitzgerald", 15, BookType.romance,
	        LocalDate.of(1925, 4, 10),
	        LocalDateTime.of(2022, 12, 8, 12, 0),
	        LocalDateTime.of(2022, 12, 8, 12, 0)
	    );
	    when(serviceimpl.get(1)).thenReturn(mockData);

	    // Call API And Test
	    mockMvc.perform(get("/book/1")).andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(
	            content().contentTypeCompatibleWith(
	                MediaType.APPLICATION_JSON
	            )
	        ).andExpect(jsonPath("$.name", is(mockData.getName())))
	        .andExpect(jsonPath("$.author", is(mockData.getAuthor())))
	        .andExpect(jsonPath("$.price", is(mockData.getPrice())))
	        .andExpect(jsonPath("$.type", is(mockData.getType().name())))
	        .andExpect(jsonPath("$.publishDate", is(mockData.getPublisheDate())))
	        .andExpect(jsonPath("$.createDateTime", is(mockData.getCreateDateTime())))
	        .andExpect(jsonPath("$.updateAt", is(mockData.getUpDateAt())));

	}
	@Test
	void api_createBook_should_return_created_book() throws Exception {

	    // Prepare Book Data
	    Book bookData = new Book(
	        1, "Book 1", "Author 1", 100, BookType.romance, LocalDate.now(),
	        LocalDateTime.now(), LocalDateTime.now()
	    );

	    // Prepare Service Mock
	    when(serviceimpl.create(bookData)).thenReturn(bookData);

	    // Call API And Test
	    mockMvc.perform(
	        post("/book")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(asJsonString(bookData))
	    ).andDo(print())
	        .andExpect(status().isCreated())
	        .andExpect(
	            content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
	        )
	        .andExpect(jsonPath("$.name", is(bookData.getName())))
	        .andExpect(jsonPath("$.author", is(bookData.getAuthor())))
	        .andExpect(jsonPath("$.price", is(bookData.getPrice())))
	        .andExpect(jsonPath("$.type", is(bookData.getType().toString())))
	        .andExpect(
	            jsonPath("$.publishDate", is(bookData.getPublisheDate().toString()))
	        )
	        .andExpect(
	            jsonPath("$.createDateTime", is(bookData.getCreateDateTime().toString()))
	        )
	        .andExpect(
	            jsonPath("$.updateAt", is(bookData.getUpDateAt().toString()))
	        );
	}

	// Method to convert Book object to JSON string
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}



}
