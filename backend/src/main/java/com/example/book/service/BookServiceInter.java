package com.example.book.service;

import java.util.List;



import com.example.book.model.Book;


public interface BookServiceInter {
	public List<Book> getAll();

	public Book get(int id);

	public  Book create( Book book);

	public  Book update( Book  book);

	public boolean delete(int id);

}
