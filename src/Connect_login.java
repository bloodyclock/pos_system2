import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Connect_login {
    public static String[] connect(String log_id,String pass) {
    String id = "";
    String name = "";
    String lastname = "";
    Connection conn = null;
    Statement st = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/?useSSL=false", "root", "90129012");
        st = conn.createStatement();
        ResultSet rs =
                st.executeQuery("SELECT * FROM test.test_staff where staff_id = " + "'"+log_id+"'&& Staff_password = '"+pass+"'");
        while (rs.next()) {
            id = rs.getString(1);
            name = rs.getString(3);
            lastname = rs.getString(4);
            System.out.println
                    ("id: " + id + " name: " + name + " lastname: " + lastname);
        }
    } catch (ClassNotFoundException e) {
        System.out.println("Data not found");
    } catch (SQLException e) {
        System.out.println("Data not found");
    } finally {
        //finally block used to close resources
        try {
            if (st != null)
                st.close();
        } catch (SQLException se2) {
        }// nothing we can do
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    return new String[]{id, name, lastname};
}
}
