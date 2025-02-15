package Services;

import Model.Login;

import java.sql.SQLException;
import java.util.List;

public interface RegisterService {
    List<Login>getAlluser() throws SQLException;
    Boolean addUser(Login login);
}
