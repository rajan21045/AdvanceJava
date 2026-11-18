import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC CRUD example (MySQL).
 *
 * Setup:
 *   1. Install MySQL and create the database:   CREATE DATABASE testdb;
 *   2. Download the MySQL Connector/J jar (mysql-connector-j-x.x.x.jar).
 *   3. Change URL, USER and PASSWORD below.
 *
 * Compile:  javac JDBCProgram.java
 * Run:      java -cp .:mysql-connector-j-x.x.x.jar JDBCProgram      (Linux/macOS)
 *           java -cp .;mysql-connector-j-x.x.x.jar JDBCProgram      (Windows)
 */
public class JDBCProgram {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        // try-with-resources closes the connection automatically
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to database.");

            createTable(conn);
            insertStudent(conn, "Aarav Sharma", "Computer Science", 3.8);
            insertStudent(conn, "Sita Thapa", "Mathematics", 3.5);
            insertStudent(conn, "Ram Gurung", "Physics", 3.2);

            System.out.println("\nAfter insert:");
            showStudents(conn);

            updateGpa(conn, "Ram Gurung", 3.6);
            System.out.println("\nAfter update:");
            showStudents(conn);

            deleteStudent(conn, "Sita Thapa");
            System.out.println("\nAfter delete:");
            showStudents(conn);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState() + ", Error code: " + e.getErrorCode());
        }
    }

    // CREATE TABLE
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "course VARCHAR(100), "
                + "gpa DOUBLE)";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            st.executeUpdate("DELETE FROM students"); // start clean for the demo
        }
    }

    // INSERT (PreparedStatement protects against SQL injection)
    private static void insertStudent(Connection conn, String name, String course, double gpa)
            throws SQLException {
        String sql = "INSERT INTO students (name, course, gpa) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, gpa);
            ps.executeUpdate();
        }
    }

    // SELECT
    private static void showStudents(Connection conn) throws SQLException {
        String sql = "SELECT id, name, course, gpa FROM students ORDER BY id";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.printf("%-4s %-15s %-18s %s%n", "ID", "Name", "Course", "GPA");
            while (rs.next()) {
                System.out.printf("%-4d %-15s %-18s %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getDouble("gpa"));
            }
        }
    }

    // UPDATE
    private static void updateGpa(Connection conn, String name, double newGpa) throws SQLException {
        String sql = "UPDATE students SET gpa = ? WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newGpa);
            ps.setString(2, name);
            int rows = ps.executeUpdate();
            System.out.println("\nRows updated: " + rows);
        }
    }

    // DELETE
    private static void deleteStudent(Connection conn, String name) throws SQLException {
        String sql = "DELETE FROM students WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            int rows = ps.executeUpdate();
            System.out.println("\nRows deleted: " + rows);
        }
    }
}