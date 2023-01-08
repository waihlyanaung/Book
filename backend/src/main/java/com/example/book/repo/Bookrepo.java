package com.example.book.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.Book;

public interface Bookrepo extends JpaRepository<Book, Integer>{

}
