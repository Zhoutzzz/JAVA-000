package ztz;

import javax.sql.RowSet;
import java.sql.*;

/**
 * @author ztz
 * @description TODO
 * @date 2020/11/18 19:22
 */
public class JdbcDemo {

    Connection conn = null;
    String url = "jdbc://localhost:3306/jdbc_demo";
    String user = "root";
    String pwd = "root";

    public void go() throws SQLException {

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM test");
            RowSet resultSet = (RowSet) ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
