package Services;

import Model.Borrowed_Books;

import java.util.List;

public interface BorrowedBookService {
    boolean addBooks (Borrowed_Books borrowedBooks);
    List<Borrowed_Books> getAll();
    public Borrowed_Books searchMember(String browdbooks);




}
