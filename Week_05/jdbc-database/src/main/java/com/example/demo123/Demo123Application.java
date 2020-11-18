package com.example.demo123;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
public class Demo123Application {

    @Autowired
    DataSource dataSource;

    static Savepoint savepoint = null;
    static Connection connection = null;

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext run = SpringApplication.run(Demo123Application.class, args);
        Demo123Application bean = run.getBean(Demo123Application.class);
        try {
            connection = bean.dataSource.getConnection();
            System.out.println(connection);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM test");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ":" + resultSet.getString("tname"));
            }

            SourceJdbc();

            JdbcTxEnhance();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }


	static String url = "jdbc:mysql://localhost:3306/jdbc_demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
	static String user = "root";
	static String pwd = "root";

    private static void SourceJdbc() throws SQLException {
        try {
            connection = ApplicationContextInfo.getApplicationContext().getBean(DataSource.class).getConnection();
            System.out.println(connection);

//			connection = DriverManager.getConnection(url, user, pwd);
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ":" + resultSet.getString("tname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static void JdbcTxEnhance() throws SQLException {
        try {
            connection = ApplicationContextInfo.getApplicationContext().getBean(DataSource.class).getConnection();
            System.out.println(connection);

//			connection = DriverManager.getConnection(url, user, pwd);
            connection.setAutoCommit(false);

            PreparedStatement statement1 = connection.prepareStatement("INSERT INTO test VALUES (?, ?)");
            for (int i = 1; i < 10001; i++) {
                statement1.setInt(1, i);
                statement1.setString(2, "mother fuck" + i);
                statement1.addBatch();
                if (i % 200 == 0) {
                    statement1.executeBatch();
                    statement1.clearBatch();
                }
            }
            statement1.executeBatch();
            savepoint = connection.setSavepoint();

//            int u = 1 / 0;

            PreparedStatement statement = connection.prepareStatement("UPDATE test SET tname = ? WHERE id = ?");
            statement.setString(1, "what fuck8");
            statement.setInt(2, 1);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (savepoint == null) {
				connection.rollback();
            } else {
				connection.rollback(savepoint);
			}
			connection.commit();
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }


}
