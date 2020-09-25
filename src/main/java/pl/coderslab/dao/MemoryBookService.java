package pl.coderslab.dao;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MemoryBookService {

    private List<Book> localLibrary = Arrays.asList(
            new Book(1l, "978-83-283-5779-2",
                    "Java. Podstawy. Wydanie XI",
                    "Cay S. Horstmann", "Helion", "programing"),
            new Book(2l, "978-83-283-6841-5",
                    "Java Full Stack Developer. Kurs video. Tworzenie aplikacji internetowych od podstaw",
                    "Marcin Berendt", "Videopoint", "programing"),
            new Book(3l, "978-83-283-5607-8", "Spring w akcji. Wydanie V ",
                    "Craig Walls", "Helion", "programing"),
            new Book(4L, "9788324631766", "Thinking in Java",
                    "Bruce Eckel", "Helion", "programming"));

    public List<Book> getLocalLibrary() {
        return localLibrary;
    }
    public Optional<Book> getBookById(long id){
        return localLibrary.stream().filter(s->s.getId().equals(id)).findFirst();
    }
    public void addBooks(Book book){
        localLibrary.add(book);
    }

}
