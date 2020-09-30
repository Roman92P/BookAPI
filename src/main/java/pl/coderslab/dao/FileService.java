package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;
import pl.coderslab.exceptions.AppendingObjectOutputStream;
import pl.coderslab.exceptions.BookNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FileService {

    private final String FILE_PATH = "/home/roman/WarsztatSpring/BookAPI/library.txt";


    public void saveBook(Book book){

        File file = new File(FILE_PATH);
        if (file.length() == 0) {
            file.delete();
        }

        if (Files.exists(Paths.get(FILE_PATH))) {
            List<Book> booksList = getBooksList();
            int size = booksList.size();
            Book book1 = booksList.get(size - 1);
            Long id = book1.getId();
            book.setId(id + 1);
            try {
                FileOutputStream fos = new FileOutputStream(FILE_PATH, true);
                AppendingObjectOutputStream appendingObjectOutputStream = new AppendingObjectOutputStream(fos);
                appendingObjectOutputStream.writeObject(book);
                fos.flush();
                appendingObjectOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            try {
                Files.createFile(Paths.get(FILE_PATH));
                FileOutputStream fos = new FileOutputStream(FILE_PATH);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                book.setId((long) 1);
                oos.writeObject(book);
                fos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Book> getBooksList(){
        List<Book> bookList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream oos = new ObjectInputStream(fis);
            while (true) {
                Object readObject = oos.readObject();
                if (readObject == null) break;
                Book readBook = (Book) readObject;
                bookList.add(readBook);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Book getBookFromFileById(long id)  {
        List<Book> booksList = getBooksList();
        Optional<Book> optionalBook = booksList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException();
        }
        return optionalBook.get();
    }

    public void deleteBookFromFile(long id) {
        List<Book> booksList = getBooksList();
        Optional<Book> optionalBook = booksList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException();
        }
        Book book = optionalBook.get();
        booksList.remove(booksList.indexOf(book));

        try {
            Files.delete(Paths.get(FILE_PATH));
            for ( Book newBook : booksList ){
                saveBook(newBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBookFromFile(Book book){
        long id = book.getId();
        Book bookFromFileById = getBookFromFileById(id);
        bookFromFileById.setIsbn(book.getIsbn());
        bookFromFileById.setType(book.getType());
        bookFromFileById.setPublisher(book.getPublisher());
        bookFromFileById.setAuthor(book.getAuthor());
        bookFromFileById.setTitle(book.getTitle());
        deleteBookFromFile(id);
        saveBook(book);
    }

}
