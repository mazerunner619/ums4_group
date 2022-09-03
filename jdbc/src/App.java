import java.lang.Thread.State;
import java.sql.*;

public class App {
    Connection c;
    Statement s;

    App() {
        try {
            // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///umsproject", "root", "......");
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println("CLASS not FOUND!");

            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        ResultSet rs = app.s.executeQuery("select * from student");
        while (rs.next()) {
            System.out.println(rs.getString("f_name") + "\t" + rs.getDate("dob") + "\t" + rs.getString("phone") + "\t"
                    + rs.getString("email"));
        }
    }
}
