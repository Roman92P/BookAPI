package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.BookService;
import pl.coderslab.entity.Book;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @RequestMapping("/{id}")
    public Book getBookById(@PathVariable  long id){
        return (Book) bookService.getBookByid(id);
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
