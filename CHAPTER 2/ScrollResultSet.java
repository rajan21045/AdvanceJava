import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Scrollable ResultSet example (MySQL).
 *
 * Uses the "students" table created by JDBCProgram.java
 * (columns: id, name, course, gpa). Run JDBCProgram first, or create the table yourself.
 *
 * Compile:  javac ScrollResultSet.java
 * Run:      java -cp .:mysql-connector-j-x.x.x.jar ScrollResultSet      (Linux/macOS)
 *           java -cp .;mysql-connector-j-x.x.x.jar ScrollResultSet      (Windows)
 */
public class ScrollResultSet {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // TYPE_SCROLL_INSENSITIVE : cursor can move forward AND backward,
            //                           the ResultSet does not see changes made by others.
            // CONCUR_READ_ONLY        : rows cannot be updated through the ResultSet.
            try (Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
                 ResultSet rs = st.executeQuery("SELECT id, name, course, gpa FROM students ORDER BY id")) {

                // Total rows: jump to the last row and read its row number
                rs.last();
                int total = rs.getRow();
                System.out.println("Total rows: " + total);

                // 1. Forward: beforeFirst() then next()
                System.out.println("\n--- Forward ---");
                rs.beforeFirst();
                while (rs.next()) {
                    printRow(rs);
                }

                // 2. Backward: afterLast() then previous()
                System.out.println("\n--- Backward ---");
                rs.afterLast();
                while (rs.previous()) {
                    printRow(rs);
                }

                // 3. Jump to specific rows
                System.out.println("\n--- first() ---");
                if (rs.first()) printRow(rs);

                System.out.println("\n--- last() ---");
                if (rs.last()) printRow(rs);

                System.out.println("\n--- absolute(2): the 2nd row ---");
                if (rs.absolute(2)) printRow(rs);

                System.out.println("\n--- absolute(-1): the last row, counting from the end ---");
                if (rs.absolute(-1)) printRow(rs);

                // 4. Relative movement from the current row
                System.out.println("\n--- first(), then relative(1): move one row forward ---");
                if (rs.first() && rs.relative(1)) printRow(rs);

                System.out.println("\n--- relative(-1): move one row back ---");
                if (rs.relative(-1)) printRow(rs);

                // 5. Position checks
                rs.first();
                System.out.println("\nisFirst() at first row  : " + rs.isFirst());
                rs.last();
                System.out.println("isLast() at last row    : " + rs.isLast());
                rs.afterLast();
                System.out.println("isAfterLast()           : " + rs.isAfterLast());
                rs.beforeFirst();
                System.out.println("isBeforeFirst()         : " + rs.isBeforeFirst());
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState() + ", Error code: " + e.getErrorCode());
        }
    }

    private static void printRow(ResultSet rs) throws SQLException {
        System.out.printf("Row %d -> id=%d, name=%s, course=%s, gpa=%.2f%n",
                rs.getRow(),
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("course"),
                rs.getDouble("gpa"));
    }
}