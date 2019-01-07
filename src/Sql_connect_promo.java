import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Sql_connect_promo {
    public static String[] connect(String procode) {
    String code = "";
    String dis  = "";
    String type = "";
    Connection conn = null;
    Statement st = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/?useSSL=false", "root", "123456");
        st = conn.createStatement();
        ResultSet rs =
                st.executeQuery("SELECT * FROM test.test_promotion where promo_code = " +"'"+ procode+"'");
        while (rs.next()) {
            code = rs.getString(1);
            dis = rs.getString(2);
            type = rs.getString(3);
            System.out.println
                    ("promo code is : " + code + " discount is : " + dis + "code type is " + type);
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
    return new String[]{code,dis,type};
}
}
