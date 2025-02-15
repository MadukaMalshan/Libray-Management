package Controller;

import DBConnection.DBConnection;
import Model.Returned_Book;
import Services.ReturnBooksService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnBooksController implements ReturnBooksService {
    public static ReturnBooksController instance;

    public ReturnBooksController getInstance( ){

        if (instance==null){
            instance=new ReturnBooksController();
        }


      return instance;
    }

    Connection connection =DBConnection.getInstance().getConnection();


    @Override
    public boolean addBooks(Returned_Book returnBook) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO returnsbooks VALUES(?,?,?,?,?,?)");

            preparedStatement.setString(1,returnBook.getReturnid());
            preparedStatement.setString(2,returnBook.getBookid());
            preparedStatement.setString(3,returnBook.getBookid());
            preparedStatement.setString(4,returnBook.getReturn_date());
            preparedStatement.setString (5, String.valueOf(Double.parseDouble(String.valueOf(returnBook.getFindAmount()))));
            preparedStatement.setString(6,returnBook.getReturn_status());


            if (preparedStatement.executeUpdate()>0){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

