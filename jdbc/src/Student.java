import javax.smartcardio.CommandAPDU;
import javax.swing.*;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;

public class Student implements ActionListener {

    JFrame FRAME;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf;
    String columns[] = { "Name", "DOB", "Phone", "Email", "Branch", "Course", "Roll No.", "City", "Pincode",
            "Address" };

    Student() throws Exception {
        FRAME = new JFrame();
        FRAME.setLayout(new BorderLayout());
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension size = t.getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        App app = new App();
        int count = 0;
        ResultSet rs = app.s.executeQuery("select count(*) from student");
        if (rs.next()) {
            count = rs.getInt(1);
        }
        System.out.println("count " + count);
        String data[][] = new String[count][10];
        int i = 0;
        rs = app.s.executeQuery("select * from student");
        while (rs.next()) {
            data[i][0] = rs.getString(1) + " " + rs.getString(2);
            for (int j = 3; j <= 11; j++) {
                data[i][j - 2] = rs.getString(j);
            }
            i++;
        }

        jt = new JTable(data, columns);
        jsp = new JScrollPane(jt);
        JToolBar jtb = new JToolBar();
        JButton home = new JButton("Home");
        JButton add = new JButton("Add Student");
        JButton attend = new JButton("Attendance");

        JMenuBar jmb = new JMenuBar();
        JMenu searchBy = new JMenu("Search By");
        JMenuItem byName = new JMenuItem("Name");
        JMenuItem byRoll = new JMenuItem("Roll");
        JMenuItem byCity = new JMenuItem("City");
        JMenuItem byBranch = new JMenuItem("Branch");
        JMenuItem byCourse = new JMenuItem("Course");
        JMenuItem byEmail = new JMenuItem("Email");
        JMenuItem byPhone = new JMenuItem("Phone");

        byName.addActionListener(this);
        byBranch.addActionListener(this);
        byCity.addActionListener(this);
        byCourse.addActionListener(this);
        byRoll.addActionListener(this);
        byEmail.addActionListener(this);
        byPhone.addActionListener(this);

        searchBy.add(byName);
        searchBy.add(byRoll);
        searchBy.add(byCourse);
        searchBy.add(byBranch);
        searchBy.add(byCity);
        searchBy.add(byEmail);
        searchBy.add(byPhone);

        jmb.add(searchBy);

        JButton search = new JButton("Search");
        jtf = new JTextField("search...", 10);

        home.addActionListener(this);
        add.addActionListener(this);
        attend.addActionListener(this);
        search.addActionListener(this);

        jtb.add(home);
        jtb.addSeparator();
        jtb.add(add);
        jtb.addSeparator();
        jtb.add(attend);
        jtb.add(Box.createHorizontalGlue());
        jtb.add(jtf);
        jtb.add(jmb);
        FRAME.add(jtb, BorderLayout.NORTH);
        FRAME.add(jsp, BorderLayout.CENTER);
        FRAME.setSize(width, height);
        FRAME.setVisible(true);
    }

    public void actionPerformed(ActionEvent action) {
        String command = action.getActionCommand();
        System.out.println(command);
        if (command == "Home") {
            // FRAME.setVisible(false);
            FRAME.dispose();
            new Border();
        } else if (command == "Add Student") {
            FRAME.dispose();
            new NewS();
        } else if (command == "Attendance") {
        } else {
            String searchby = "";
            String searchFor = jtf.getText();
            jtf.setText("Loading...");
            if (command == "Name") {
                searchby = "concat(f_name,' ',l_name) = ";
            } else if (command == "Roll") {
                searchby = "roll = ";
            } else if (command == "Course") {
                searchby = "course = ";
            } else if (command == "Branch") {
                searchby = "branch = ";
            } else if (command == "Email") {
                searchby = "email = ";
            } else if (command == "Phone") {
                searchby = "phone = ";
            } else if (command == "City") {
                searchby = "city = ";
            }
            try {
                App app = new App();
                int count = 0;
                String query = "select * from student where " + searchby + " '" + searchFor + "'";
                ResultSet rs = app.s
                        .executeQuery("select count(*) from student where " + searchby + " '" + searchFor + "'");
                if (rs.next()) {
                    count = rs.getInt(1);
                }
                System.out.println("count " + count);
                String data[][] = new String[count][10];
                System.out.println("Query " + query);
                rs = app.s.executeQuery(query);
                int j = 0;

                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                    data[j][0] = rs.getString(1) + " " + rs.getString(2);
                    for (int i = 3; i <= 10; i++) {
                        System.out.println(rs.getString(i));
                        data[j][i - 2] = rs.getString(i);
                    }
                    j++;
                }
                FRAME.remove(jsp);
                jt = new JTable(data, columns);
                jsp = new JScrollPane(jt);
                FRAME.add(jsp, BorderLayout.CENTER);
                jtf.setText("Search...");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Student();
    }
}
