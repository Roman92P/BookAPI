package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.config.AppConfig;
import pl.coderslab.dao.BookService;
import pl.coderslab.entity.Book;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(@Qualifier("toFile") BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @RequestMapping("/{id}")
    public Book getBookById(@PathVariable  long id) {
        return bookService.get(id);
    }
    @PostMapping("")
    public void addNewBook( @RequestBody Book book){
        bookService.addBook(book);
    }
    @PutMapping("")
    public void updateBook(@RequestBody Book book){
        bookService.updateBooks(book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }
}
