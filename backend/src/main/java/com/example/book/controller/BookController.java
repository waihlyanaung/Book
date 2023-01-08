package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.Book;
import com.example.book.service.BookServiceInter;


@RestController
@RequestMapping("/api")
public class BookController {
	@Autowired
BookServiceInter service;
	
	@GetMapping("/book")
	public List<Book> getAll() {
		return service.getAll();
	}
	
	@GetMapping( "/book/{book_id}")
	Book get(@PathVariable("book_id")int bookID) {
		return service.get(bookID);
		
	}
	
	@PostMapping("/book")
	public Book create(@RequestBody Book book) {
	
		return service.create(book);
	}
	
	@PutMapping( "/book")
			ResponseEntity<?> update(@RequestBody Book book){
				Book updatedBook=service.update(book);
		if (updatedBook == null) {
			return ResponseEntity.badRequest().body("book cannot update");
		}

		return ResponseEntity.ok(updatedBook);
	}
	
	@DeleteMapping("/book")
	ResponseEntity<?> delete(@RequestBody Book book) {
		 boolean isDeleted = service.delete(book.getId());
		 if (isDeleted == false) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok().build();
	}
}
