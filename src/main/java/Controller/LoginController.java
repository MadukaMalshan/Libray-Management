package Controller;

import DBConnection.DBConnection;
import Model.Login;
import Services.LoginServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController implements LoginServices {
    public static LoginController instance;
    public  static LoginController getInstance(){


        return  instance==null? instance=new LoginController():instance;
    }






    @Override
    public Login getUser(String user_name, String password) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
        preparedStatement.setString(1,user_name);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            Login login=new Login(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)


            );
            System.out.println(login);
            return login;

        }


        return null;
    }
}
