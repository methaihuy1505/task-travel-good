package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        // 1. Thông tin kết nối
        String url = "jdbc:mysql://localhost:3306/travel_good_db";
        String user = "root";
        String pass = "androissKZ123@";

        try {
            // 2. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 3. Trả về kết nối
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Lỗi kết nối DB: " + e.getMessage());
            return null;
        }
    }
}