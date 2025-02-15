package FormControllers;

import Controller.LoginController;
import Model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txt_username;
    public TextField txt_password;

    public void Rejister_on_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Register.fxml"))));
        stage.setTitle("Rejister");
        stage.show();
    }



    public void btn_Login_Action(ActionEvent actionEvent) throws SQLException {
        Login User= LoginController.getInstance().getUser(txt_username.getText(),txt_password.getText());
        System.out.println(User);
        new Alert(Alert.AlertType.INFORMATION,"Sucsses Fully").show();
        if (User==null){
            new Alert(Alert.AlertType.INFORMATION,"Loging  Not Sucsses Fully").show();
            return;
        }
        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"))));
            stage.setTitle("DashBoard");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
