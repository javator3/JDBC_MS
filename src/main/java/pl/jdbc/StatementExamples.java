package pl.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.sql.DriverManager.getConnection;

//import org.slf4j.Logger;

//import org.slf4j.LoggerFactory;

//private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

public class StatementExamples {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = getConnection("jdbc:mysql://localhost:3306/ksiegarnia?serverTimezone=UTC","root","tralali81");
        Connection connection = getConnection("jdbc:mysql://localhost:3306/ksiegarnia?serverTimezone=UTC&logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true","root","tralali81");
        //createTable(connection);
        //insert10newEmployee(connection);
        insert5newEmployee2(connection);
        //dropTable(connection);
        //deleteEmployee(connection,1000);
       // selectNameAndSalaryFromTable(connection);
       // selectNameAndSalaryFromTableOrderName(connection);
       // selectNameAndSalaryFromTableGreaterThen(connection);
        //selectNameAndSalaryFromTableLike(connection);
       // List<Employee> employees = selectNameAndSalaryFromTableReturnObjectList(connection);
       // System.out.println(employees);
    }

    static void createTable(Connection connection) throws SQLException {
           Statement statement = connection.createStatement();
           statement.execute("create table employee( \n" +
                     "id smallint not null auto_increment, \n" +
                     " name varchar(50), \n" +
                     " salary int,\n" +
                     " primary key (id)); ");
    }

    // napisze metode ktora usunie pracownikow ktorych zarobki sa mniejsze niz argument podany w metodzie
    static void deleteEmployee(Connection connection,Integer pensja) throws SQLException {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE salary < ?");
            ps.setInt(1, pensja);
            ps.executeUpdate();
        }



    static void insert5newEmployee(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        for (int i =0; i < 5; i++){
            statement.executeUpdate("INSERT INTO employee(name,salary) values ('name',1234)");
        }

    }

    static void insert5newEmployee2(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO employee(name,salary) values (?,?)");
        for(int i = 0; i <5; i++){
            ps.setString(1,"imie" + 1);
            ps.setInt(2, new Random().nextInt(2000));
            ps.executeUpdate();
        }
    }

    static void insert10newEmployee(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String name ="";
        Integer placa =0;
        Random generator = new Random();

        String formattedString;

        for (int i =0; i < 10; i++){
            placa = generator.nextInt(10)*1000;
            name = "Jan" + i;
            formattedString = String.format("INSERT INTO employee(name,salary) values ('name%s',1234)",i);
            statement.executeUpdate("INSERT INTO employee(name,salary) values ('" + name + "'," + placa + ")");
            statement.executeUpdate(formattedString);
        }
    }

    //SELECT
    static void selectNameAndSalaryFromTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name,salary FROM employee");
        while(resultSet.next()){
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            System.out.println(name+" " + salary);
        }
    }

    //SELECT - RETURN OBJECT
    static List<Employee> selectNameAndSalaryFromTableReturnObjectList(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name,salary,id FROM employee");
        List<Employee> result = new ArrayList<Employee>();

        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            Employee employee = new Employee(id,name,salary);
            result.add(employee);
        }
        return result;
    }

    //SELECT ORDER NAME
    static void selectNameAndSalaryFromTableOrderName(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(" SELECT name,salary FROM employee order by name asc;");
        while(resultSet.next()){
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            System.out.println(name+" " + salary);
        }
    }


    //SELECT
    static void selectNameAndSalaryFromTableGreaterThen(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(" SELECT name,salary FROM employee where salary > 1300;");
        while(resultSet.next()){
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            System.out.println(name+" " + salary);
        }
    }


    //SELECT
    static void selectNameAndSalaryFromTableLike(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(" SELECT name,salary FROM employee where name like 'A%';");
        while(resultSet.next()){
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            System.out.println(name+" " + salary);
        }
    }


    static void dropTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE employee2");
    }





}
