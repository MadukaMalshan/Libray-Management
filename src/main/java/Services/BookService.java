package Services;

import Model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book SearcBooks(String books);

    boolean UpdateBooks(Book book);

    boolean DeleteBooks(Book bookid);

    void  Addbook (Book book);

}
