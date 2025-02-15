package Controller;

import DBConnection.DBConnection;
import Model.Borrowed_Books;

import Model.Members;
import Services.BorrowedBookService;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowedController implements BorrowedBookService {
    public  static BorrowedController instance;

    public BorrowedController getInstance(){
        if (instance==null){
            instance=new BorrowedController();
        }
        return instance;
    }



    @Override
    public boolean addBooks(Borrowed_Books borrowedBooks) {
        Connection connection= DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO BorrowedBooks(borrow_id, book_id, member_id, staff_id, borrow_date, is_borrowed) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, borrowedBooks.getBrrowedbokksid());
            preparedStatement.setString(2, borrowedBooks.getBookID());
            preparedStatement.setString(3, borrowedBooks.getMemberid());
            preparedStatement.setString(4, borrowedBooks.getStaffid());
            preparedStatement.setString(5, borrowedBooks.getBrooedate());
            preparedStatement.setString(6, borrowedBooks.getIsBrowwed());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public List<Borrowed_Books> getAll() {
        Connection connection=DBConnection.getInstance().getConnection();

        ArrayList<Borrowed_Books> brrowedbooks= new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(" Select * from borrowedbooks");

            while (resultSet.next()){
                brrowedbooks.add(
                        new Borrowed_Books(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        )
                );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brrowedbooks;

    }

    @Override
    public Borrowed_Books searchMember(String browdbooks) {
        Connection connection=DBConnection.getInstance().getConnection();
        Members searchMemberObject = null;


        try {

            PreparedStatement preparedStatement =connection.prepareStatement("SELECT * FROM borrowedbooks WHERE borrow_id = ?");
            preparedStatement.setString(1, browdbooks);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {


                return new Borrowed_Books(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                // return searchMemberObject;

                // return null;
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Not Sucsses Full").show();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
