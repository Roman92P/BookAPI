package pl.coderslab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Book in your book list")
public class BookNotFoundException extends RuntimeException {

}
