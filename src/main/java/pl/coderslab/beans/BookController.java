package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.MemoryBookService;
import pl.coderslab.entity.Book;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    MemoryBookService memoryBookService;

    @Autowired
    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping("")
    public List<Book> getAllBooks(){
        return memoryBookService.getLocalLibrary();
    }

    @RequestMapping("/{id}")
    public Object getSingleBook(@PathVariable long id){
        Optional<Book> bookById = memoryBookService.getBookById(id);
        if(bookById.isPresent()){
            return bookById.get();
        }else {
            return "No such book";
        }
    }
//    @PostMapping()
//    public String newBook ( @RequestBody Book book){
//        memoryBookService.addBooks(book);
//        return "succes add!";
//    }
}
