import java.sql.*;
public class JDBCExample{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/College";
        String user = "root";
        String password = "Rajan@123$";

        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            while(rs.next()){
                System.out.println(rs.getString("column1") + " " + rs.getString("column2"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}