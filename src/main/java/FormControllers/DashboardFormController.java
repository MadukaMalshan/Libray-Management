package FormControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    public AnchorPane Ancorpain_Dashbord;

    public void btn_member_Action(ActionEvent actionEvent) throws IOException {
        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Member.fxml"))));
        stage.setTitle("Member Form");
        stage.show();
    }

    public void btn_Addbooks_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AddBooks.fxml"))));
        stage.setTitle("Add Form");
        stage.show();
    }

    public void btn_Staff_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/StaffForm.fxml"))));
        stage.show();

    }

    public void Borrowed_Bokkss_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/TakeBooks.fxml"))));
        stage.setTitle("Borrowed Books");
        stage.show();
    }

    public void btn_Return_book_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ReturnBooks.fxml"))));
        stage.setTitle("Return Books");
        stage.show();
    }

    public void btn_Logout_Action(ActionEvent actionEvent) {
        Stage stage=(Stage)Ancorpain_Dashbord.getScene().getWindow();
        stage.close();
        stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Login.fxml"))));
            stage.setTitle("Login Form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_Report_OnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) Ancorpain_Dashbord.getScene().getWindow();
        stage.close();
        stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Report.fxml"))));
        stage.show();

    }
}
