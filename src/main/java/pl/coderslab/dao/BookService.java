package pl.coderslab.dao;

import pl.coderslab.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();
    Book get(Long id);
    void addBook(Book book);
    void updateBooks(Book book);
    void deleteBook (long id);

}
