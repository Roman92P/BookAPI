package pl.coderslab.dao;

import pl.coderslab.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Object getBookByid(long id);
    void addBook(Book book);
    void updateBooks(Book book);
    void deleteBook (long id);

}
