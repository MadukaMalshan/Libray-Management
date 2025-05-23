package Controller;

import DBConnection.DBConnection;
import Model.Book;
import Services.BookService;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookController implements BookService {

    private  static BookService instancce;

    public BookService getInstancce(){
        if (instancce==null){
            return instancce=new BookController();
        }
        return instancce;
    }
    Connection connection= DBConnection.getInstance().getConnection();

    @Override
    public List<Book> getAll() {
        ArrayList<Book> books=new ArrayList<>();
        try {
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT *FROM books");
            while (resultSet.next()){
                books.add(
                        new Book(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)

                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public Book SearcBooks(String books) {
        Book searcbookobject = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE BookID=?");
            preparedStatement.setString(1,books ); // Fix incorrect variable name

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {  // If no book is found, return null
                return null;
            }

            // Corrected: Fetch data after ensuring a result exists
            searcbookobject = new Book(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return searcbookobject;
    }

    @Override
    public boolean UpdateBooks(Book book) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(" UPDATE books SET Title = ?, Author = ?, ISBN = ?, Language = ? WHERE BookID = ?");

            preparedStatement.setString(1, book.getTiitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setString(4, book.getLanvage());
            preparedStatement.setString(5, book.getBookid());
            new Alert(Alert.AlertType.INFORMATION,"Sucsse FUlly").show();
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Sucsses Full").show();
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean DeleteBooks(Book bookid) {
        return false;
    }

    @Override
    public void Addbook(Book book) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,book.getBookid());
            preparedStatement.setString(2,book.getTiitle());
            preparedStatement.setString(3,book.getAuthor());
            preparedStatement.setString(4,book.getIsbn());
            preparedStatement.setString(5,book.getLanvage());

            if (preparedStatement.executeUpdate()>0) new Alert(Alert.AlertType.INFORMATION,"Sucsess Full").show();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
