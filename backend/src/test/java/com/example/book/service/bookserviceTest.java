package com.example.book.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.book.model.Book;
import com.example.book.model.BookType;
import com.example.book.repo.Bookrepo;
//import static org.junit.jupiter.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.usingRecursiveComparison;
//@ExtendWith(MockitoExtension.class)
//public class bookserviceTest {
//	
////	BookServiceimpl is name of u want to test of service whih is not interface class
//	
//	@InjectMocks
//	private BookServiceimpl service;
//	
////	Bookrepo repo is recall from @Autowired of BookServiceimpl
//	
//	@InjectMocks
//	private Bookrepo repo;
//	
//	@Test
//	void should_return_all_books() {}
//	
//	List<Book> mockData = new ArrayList<>();
//	Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 19.99, 
//			BookType.romance,LocalDate.now() );
//	mockData.add(book1);
//	when(repo.findAll()).thenReturn(mockData);
//}

@ExtendWith(MockitoExtension.class)
public class bookserviceTest {

	// Inject the mock repository into the service
	@InjectMocks
	private BookServiceimpl service;

	// Create a mock repository
	@Mock
	private Bookrepo repo;

	@Test
	void should_return_all_books() {
		// Prepare the mock repository
		List<Book> mockData = new ArrayList<>();
		Book book1 = new Book(1, "Book 1", "Author 1", 10, BookType.romance, LocalDate.now(), LocalDateTime.now(),
				LocalDateTime.now());
		Book book2 = new Book(2, "Book 2", "Author 2", 20, BookType.romance, LocalDate.now(), LocalDateTime.now(),
				LocalDateTime.now());
		mockData.add(book1);
		mockData.add(book2);
		when(repo.findAll()).thenReturn(mockData);

		// Call the service method
		List<Book> actual = service.getAll();

		// Verify the results
		assertThat(actual).hasSize(2);
		assertThat(actual).contains(book1);
		assertThat(actual).contains(book2);

		verify(repo, times(1)).findAll();
	}

	@Test
	void should_get_one_book() {
		// Prepare Repo Mock
		Book mockData = new Book(1, "Mock Book", "Mock Author", 100, BookType.romance, LocalDate.now(),
				LocalDateTime.now(), LocalDateTime.now());
		when(repo.findById(any(Integer.class))).thenReturn(Optional.of(mockData));

		// Call Service Method
		Book actual = service.get(1);

		// Test
		assertThat(actual).usingRecursiveComparison().isEqualTo(mockData);
		verify(repo, times(1)).findById(any(Integer.class));

	}

	@Test
	void should_create_one_book() {

		// Prepare Repo Mock
		Book mockData = new Book(1, "The Alchemist", "Paulo Coelho", 20, BookType.romance, LocalDate.now(),
				LocalDateTime.now(), LocalDateTime.now());
		when(repo.save(any(Book.class))).thenReturn(mockData);

		// Call Service Method
		Book actual = service.create(mockData);

		// Test
		assertThat(actual).usingRecursiveComparison().isEqualTo(mockData);

		verify(repo, times(1)).save(any(Book.class));

	};

	@Test
	void should_update_one_book() {
		// Prepare Repo Mock
		Book mockData = new Book(1, "Book 1", "Author 1", 100, BookType.romance, LocalDate.now(), LocalDateTime.now(),
				LocalDateTime.now());
		when(repo.save(any(Book.class))).thenReturn(mockData);

		// Call Service Method
		Book actual = service.update(mockData);

		// Test
//        assertThat(actual).usingRecursiveComparison().isEqualTo(mockData);
//
//        verify(repo, times(1)).save(any(Book.class));
//    }
		assertThat(actual).usingRecursiveComparison().isEqualTo(mockData);

		verify(repo, times(1)).save(any(Book.class));

	};

	@Test
	void should_delete_book() {
		// Prepare Repo Mock
		Book mockData = new Book(1, "Book 1", "Author 1", 10, BookType.romance, LocalDate.now(), LocalDateTime.now(),
				LocalDateTime.now());
		when(repo.findById(1)).thenReturn(Optional.of(mockData));

		// Call Service Method
		boolean result = service.delete(1);

		// Test
		assertThat(result);

		verify(repo, times(1)).deleteById(1);
	}

}
