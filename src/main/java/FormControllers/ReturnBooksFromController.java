package FormControllers;

import Controller.BorrowedController;
import Controller.ReturnBooksController;
import Model.Borrowed_Books;
import Model.Returned_Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ReturnBooksFromController implements Initializable {


    public ComboBox combo_browid;
    public TextField txt_bookid;
    public TextField txt_Member_id;
    public TextField txt_Staff_id;
    public TextField txt_Broowed_date;
    public TextField is_Browwed;
    public Label lable_Date;
    public TextField txt_damage_or_No;
    public TextField txt_Amount;
    public TextField return_on_time;
    public TextField txt_returnid;
    public AnchorPane Anchor_Return;
    BorrowedController borrowedController =new BorrowedController();
    ReturnBooksController returnBooksControoler=new ReturnBooksController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        get_comboigd();
        LodDate();

    }

    public void get_comboigd() {
        ObservableList<String>get_Mmemberid=FXCollections.observableArrayList();
        for (Borrowed_Books borrowedBooks : borrowedController.getInstance().getAll()){
            get_Mmemberid.add(borrowedBooks.getBrrowedbokksid());
            combo_browid.setItems(get_Mmemberid);
        }


    }

    public void combo_Browedid_Action(ActionEvent actionEvent) {
        String selectid =(String) combo_browid.getSelectionModel().getSelectedItem();
        Borrowed_Books borrowedBooks = borrowedController.searchMember(selectid);

        txt_bookid.setText(borrowedBooks.getBookID());
        txt_Member_id.setText(borrowedBooks.getMemberid());
        txt_Staff_id.setText(borrowedBooks.getStaffid());
        txt_Broowed_date.setText(borrowedBooks.getBrooedate());
        is_Browwed.setText(borrowedBooks.getIsBrowwed());

    }

    private void LodDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        BreakIterator lblDate;
        lable_Date.setText(f.format(date));


    }


    public void btn_Action_Find(ActionEvent actionEvent) {
        try {
            // Validate selection first
            if (combo_browid.getSelectionModel().getSelectedItem() == null) {
                txt_damage_or_No.setText("No Borrow ID Selected");
                return;
            }

            String selectid = (String) combo_browid.getSelectionModel().getSelectedItem();
            Borrowed_Books borrowedBooks = borrowedController.searchMember(selectid);

            if (borrowedBooks == null) {
                txt_damage_or_No.setText("No Borrow Record Found");
                return;
            }


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date borrowedDate = dateFormat.parse(txt_Broowed_date.getText());
            Date returnDate = new Date();

            long diffInMillies = Math.abs(returnDate.getTime() - borrowedDate.getTime());
            long daysDiff = diffInMillies / (24 * 60 * 60 * 1000);

            double fine = 0;
            if (daysDiff > 10) {
                long daysOverdue = daysDiff - 10;
                fine = daysOverdue * 10;

               return_on_time.setText("Overdue: " + daysOverdue + " days");
                txt_Amount.setText(String.format("%.2f", fine));
                return_on_time.setText("Late Return");
            } else {
                return_on_time.setText("On Time");
                txt_Amount.setText("0.00");
                return_on_time.setText("Returned ");
            }
        } catch (Exception e) {
            return_on_time.setText("Error: " + e.getMessage());
            txt_Amount.setText("0.00");
            e.printStackTrace();
        }

        returnBooksControoler.addBooks(new Returned_Book(
                txt_returnid.getText(),
                (String) combo_browid.getValue(),
                txt_bookid.getText(),
                lable_Date.getText(),
               Double.valueOf(txt_Amount.getText()),
                return_on_time.getText()



        ));
    }

    public void btn_Back_Return_OnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) Anchor_Return.getScene().getWindow();
        stage.close();
        stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"))));
            stage.setTitle("Dash Board");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}