package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class ConnectDB {

        private static Connection conn;
        private static final String DATABASE_NAME = "products";
        private static final String DATABASE_USER = "root";
        private static final String DATABASE_PASSWORD = "123456";
        private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        private static final String TIME_ZONE = "useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";

        public static Connection getConnect() throws SQLException {
            try {
                Class.forName(JDBC_DRIVER);
                if(conn == null || conn.isClosed()) {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?user=" + DATABASE_USER + "&password=" + DATABASE_PASSWORD + "&" + TIME_ZONE);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return conn;
        }
}
