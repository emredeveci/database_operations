import javax.xml.transform.Result;
import java.sql.*;

public class DBConnection {
    public static final String DB_URL = "jdbc:mysql://localhost/";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "mysql";
    public static final String DATABASE_NAME = "Company";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            //connect to the main MYSQL server
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            //create a database
            String databaseCreation = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(databaseCreation);

            //connect to the newly created database
            connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            //create the table string and
            String tableCreation = "CREATE TABLE Employees ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Name VARCHAR(250), "
                    + "Position VARCHAR(100), "
                    + "Salary DECIMAL(10,2)"
                    + ")";

            statement.executeUpdate(tableCreation);

            //manually enter 5 different employees into the database
            String employee = "INSERT INTO Employees (Name, Position, Salary) VALUES ('Harry Potter', 'Auror', 45000.00)";
            String employee2 = "INSERT INTO Employees (Name, Position, Salary) VALUES ('Ron Weasley', 'Auror', 45000.00)";
            String employee3 = "INSERT INTO Employees (Name, Position, Salary) VALUES ('Hermione Granger', 'Minister of Magic', 120000.00)";
            String employee4 = "INSERT INTO Employees (Name, Position, Salary) VALUES ('Neville Longbottom', 'Herbology Professor', 40000.00)";
            String employee5 = "INSERT INTO Employees (Name, Position, Salary) VALUES ('Ginny Weasley', 'Quidditch Commentator', 55000.00)";

            statement.executeUpdate(employee);
            statement.executeUpdate(employee2);
            statement.executeUpdate(employee3);
            statement.executeUpdate(employee4);
            statement.executeUpdate(employee5);

            //browse through the Employees table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees");
            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("Position: " + resultSet.getString("Position"));
                System.out.println("Salary: " + resultSet.getBigDecimal("Salary"));
                System.out.println("========================");
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
