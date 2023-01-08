package com.example.book.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.book.model.Book;
import com.example.book.repo.Bookrepo;

@Service
public class BookServiceimpl implements BookServiceInter {
	
	@Autowired
	Bookrepo repo;

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Book get(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public Book create(Book book) {
		// TODO Auto-generated method stub
		book.setCreateDateTime(LocalDateTime.now());
		return repo.save(book);
	}

	@Override
	public Book update(Book book) {
//		Book findBook=repo.findById(book.getId()).orElse(null);
//		if(findBook==null) {
//			return null;
//		}
		book.setUpDateAt(LocalDateTime.now());
		return repo.save(book);

	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Book findBook=repo.findById(id).orElse(null);
		if(findBook==null) {
			return false;}
		repo.deleteById(id);
		return true;
	}

}
