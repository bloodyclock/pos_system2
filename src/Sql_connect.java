//Connect database to show product
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Sql_connect {

    public static String[] connect(String pro_id) {
        String id = "";
        String name = "";
        String price = "";
        Connection conn = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/?useSSL=false", "root", "123456");
             st = conn.createStatement();
            ResultSet rs =
                    st.executeQuery("SELECT * FROM test.test_table where Product_id = " + pro_id);
            while (rs.next()) {
                id = rs.getString(1);
                name = rs.getString(2);
                price = rs.getString(3);
                System.out.println
                        ("product id: " + id + " product name: " + name + " price " + price);
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
        return new String[]{id, name, price};
    }
}
