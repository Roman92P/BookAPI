package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.BookService;
//import pl.coderslab.dao.MemoryBookService;
import pl.coderslab.entity.Book;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

//    MemoryBookService memoryBookService;
//
//    @Autowired
//    public BookController(MemoryBookService memoryBookService) {
//        this.memoryBookService = memoryBookService;
//    }
//
//
//    @RequestMapping("")
//    public List<Book> getAllBooks(){
//        return memoryBookService.getLocalLibrary();
//    }
//
//    @RequestMapping("/{id}")
//    public Object getSingleBook(@PathVariable long id){
//        Optional<Book> bookById = memoryBookService.getBookById(id);
//        if(bookById.isPresent()){
//            return bookById.get();
//        }else {
//            return "No such book";
//        }
//    }
//    @PostMapping("")
//    public void newBook(@RequestBody Book book) {
//        memoryBookService.addBooks(book);
//    }
//
//    @PutMapping("")
//    public void updateBook(@RequestBody Book bookForUpdate){
//        memoryBookService.editBook(bookForUpdate);
//    }
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable long id){  }

    BookService bookService;

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
