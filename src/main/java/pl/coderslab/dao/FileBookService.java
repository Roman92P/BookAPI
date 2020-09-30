package pl.coderslab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

@Component("toFile")
public class FileBookService implements BookService {

    FileService fileService;

    @Autowired
    public FileBookService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public List<Book> getAllBooks() {
        return fileService.getBooksList();
    }

    @Override
    public Book get(Long id) {
        return fileService.getBookFromFileById(id);
    }

    @Override
    public void addBook(Book book) {
        fileService.saveBook(book);
    }

    @Override
    public void updateBooks(Book book) {
        fileService.updateBookFromFile(book);
    }

    @Override
    public void deleteBook(long id) {
        fileService.deleteBookFromFile(id);
    }
}
