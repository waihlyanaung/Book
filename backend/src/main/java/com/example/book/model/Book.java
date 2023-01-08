package com.example.book.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class Book {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	
	@Column(nullable =false )
	private String author;
	
	@Column(nullable = false)
	private int price;
	
	@Column(columnDefinition = "ENUM('romance', 'adventure','sciencefiction')")
	@Enumerated(EnumType.STRING)
	private BookType type;
	
	
	private LocalDate publisheDate;
	
	@Column(nullable = true)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime createDateTime;
	
	private LocalDateTime upDateAt;

	public Book() {}
	
	public Book(int id, String name, String author, int price, BookType type, LocalDate publisheDate,
			LocalDateTime createDateTime, LocalDateTime upDateAt) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.type = type;
		this.publisheDate = publisheDate;
		this.createDateTime = createDateTime;
		this.upDateAt = upDateAt;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public LocalDate getPublisheDate() {
		return publisheDate;
	}

	public void setPublisheDate(LocalDate publisheDate) {
		this.publisheDate = publisheDate;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpDateAt() {
		return upDateAt;
	}

	public void setUpDateAt(LocalDateTime upDateAt) {
		this.upDateAt = upDateAt;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", price=" + price + ", type=" + type
				+ ", publisheDate=" + publisheDate + ", createDateTime=" + createDateTime + ", upDateAt=" + upDateAt
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getAuthor()=" + getAuthor() + ", getPrice()="
				+ getPrice() + ", getType()=" + getType() + ", getPublisheDate()=" + getPublisheDate()
				+ ", getCreateDateTime()=" + getCreateDateTime() + ", getUpDateAt()=" + getUpDateAt() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
