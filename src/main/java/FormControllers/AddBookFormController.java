package FormControllers;



import Controller.BookController;
import DBConnection.DBConnection;
import Model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddBookFormController {
    public AnchorPane Anchor_Books;
    BookController bookController=new BookController();

    @FXML
    private TableColumn<?, ?> Colum_Lanvage;

    @FXML
    private TableColumn<?, ?> Colum_isbn;

    @FXML
    private TextField Txt_tiitle;

    @FXML
    private TableColumn<?, ?> colum_Author;

    @FXML
    private TableColumn<?, ?> colum_booksid;

    @FXML
    private TableColumn<?, ?> colum_tiittle;

    @FXML
    private TableView<Book> tabel_Books;

    @FXML
    private TextField txt_Author;

    @FXML
    private TextField txt_Bookid;

    @FXML
    private TextField txt_Isbn;

    @FXML
    private TextField txt_lanvage;

    @FXML
    void btn_Add_Action(ActionEvent event) {
        bookController.Addbook(new Book(
                txt_Bookid.getText(),
                Txt_tiitle.getText(),
                txt_Author.getText(),
                txt_Isbn.getText(),
                txt_lanvage.getText()
        ));

    }

    @FXML
    void btn_Delete_Action(ActionEvent event) {
        Connection connection= DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("Delete from Books  where BookID =?");

            preparedStatement.setString(1,txt_Bookid.getText());

            if (0 < preparedStatement.executeUpdate()){
                new Alert(Alert.AlertType.INFORMATION,"Sucsse Fully Delete").show();

            }else {
                new  Alert(Alert.AlertType.INFORMATION,"Not Sucsses Full Delete").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btn_Search_Action(ActionEvent event) {
        Connection connection=DBConnection.getInstance().getConnection();
        Book book = bookController.SearcBooks(txt_Bookid.getText());

        if (book != null) {
        }
            Txt_tiitle.setText(String.valueOf(book.getTiitle()));
            txt_Author.setText(String.valueOf(book.getAuthor()));
            txt_Isbn.setText(String.valueOf(book.getIsbn()));
            txt_lanvage.setText(String.valueOf(book.getLanvage()));


    }

    @FXML
    void btn_View_Action(ActionEvent event) {
        colum_booksid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        colum_tiittle.setCellValueFactory(new PropertyValueFactory<>("tiitle"));
        colum_Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Colum_isbn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        Colum_Lanvage.setCellValueFactory(new PropertyValueFactory<>("lanvage"));
        Lodtable();

    }

    @FXML
    void btn_update_Action(ActionEvent event) {
       boolean b=bookController.UpdateBooks(new Book(
               txt_Bookid.getText(),
               Txt_tiitle.getText(),
               txt_Author.getText(),
               txt_Isbn.getText(),
               txt_lanvage.getText()
       ));




    }
    public void Lodtable(){

        List<Book> books=bookController.getInstancce().getAll();
        System.out.println(books);
        ObservableList<Book> objects = FXCollections.observableArrayList();
        books.forEach(book -> objects.add(book));
        tabel_Books.setItems(objects);
    }

    public void btnBack_BooksManagement_OnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) Anchor_Books.getScene().getWindow();
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
