package pl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DriverRegisterExample {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
       // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?serverTimezone=UTC","root","tralali81");
//        DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb")
    }


}


