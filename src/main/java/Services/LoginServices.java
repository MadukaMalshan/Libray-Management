package Services;

import Model.Login;

import java.sql.SQLException;

public interface LoginServices {
Login getUser(String user_name ,String password) throws SQLException;
}
