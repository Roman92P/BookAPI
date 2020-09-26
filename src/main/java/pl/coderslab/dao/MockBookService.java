package pl.coderslab.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;

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
    public Optional<Book> getBookByid(long id) {
        return books.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    @Override
    public void addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
    }

    @Override
    public void updateBooks(Book book) {
        Long id = book.getId();
        Optional<Book> bookById = getBookByid(id);
        if (bookById.isPresent()) {
            Book book1 = bookById.get();
            book1.setIsbn(book.getIsbn());
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPublisher(book.getPublisher());
            book1.setType(book.getType());
        }
    }

    @Override
    public void deleteBook(long id) {
        Optional<Book> firstBook = books.stream().filter(s -> s.getId().equals(id)).findFirst();
        firstBook.ifPresent(book -> books.remove(book));
    }

}
