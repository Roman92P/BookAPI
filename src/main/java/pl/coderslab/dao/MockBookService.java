package pl.coderslab.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;
import pl.coderslab.exceptions.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MockBookService implements BookService {
    public static final Logger log = LoggerFactory.getLogger(MockBookService.class);
    private List<Book> books;

    private static Long nextId = 4L;

    public MockBookService() {

        books = new ArrayList<>();

        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));

        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",

                "programming"));

        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",

                "programming"));

    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book get(Long id) {
        Optional<Book> optionalBook = books.stream().filter(s -> s.getId().equals(id)).findFirst();
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException();
        }
        return optionalBook.get();
    }


    @Override
    public void addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
    }

    @Override
    public void updateBooks(Book book) {
        Long id = book.getId();
        Optional<Book> first = books.stream().filter(s -> s.getId().equals(id)).findFirst();
        Book book1 = null;
        if (first.isPresent()) {
            book1 = first.get();
            book1.setIsbn(book.getIsbn());
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPublisher(book.getPublisher());
            book1.setType(book.getType());
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public void deleteBook(long id) {
        Optional<Book> firstBook = books.stream().filter(s -> s.getId().equals(id)).findFirst();
        if(firstBook.isPresent()){
            books.remove(firstBook.get());
        }else {
            throw new BookNotFoundException();
        }
    }

}
