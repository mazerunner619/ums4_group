
// import statement  
import java.awt.*;
import java.awt.event.*;
import java.lang.ProcessHandle.Info;
import java.nio.file.Files;
import java.util.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import com.mysql.cj.exceptions.FeatureNotAvailableException;

public class NewS implements ActionListener {
    Random random = new Random();
    JFrame FRAME;
    String INFO[] = new String[11];
    JLabel[] labels = new JLabel[12];
    JTextField[] fields = new JTextField[12];

    // constructor
    NewS() {
        // creating a frame object
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension size = t.getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        FRAME = new JFrame();
        ImageIcon backImg = new ImageIcon(ClassLoader.getSystemResource("UMS_pics/004.jpg"));
        Image i1 = backImg.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon backgroundImage = new ImageIcon(i1);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBounds(0, 0, 600, height);
        leftPanel.setBackground(Color.BLACK);

        JLabel pageLabel = new JLabel("Student Registration Page");
        pageLabel.setForeground(Color.WHITE);
        pageLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        JButton homePage = new JButton("Home");
        homePage.setSize(600, 300);
        homePage.addActionListener(this);
        JButton studentPage = new JButton("Student");
        studentPage.addActionListener(this);
        leftPanel.add(pageLabel);
        leftPanel.add(homePage);
        leftPanel.add(studentPage);

        JLabel label = new JLabel(backgroundImage);
        // label.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel form = new JPanel();
        form.setLayout(null);
        panel.add(form, BorderLayout.CENTER);
        int top = 100;

        float rn = 1 + (random.nextFloat() * 1000000000) % 1000000000;
        int rollno = (int) rn;
        String datas[] = { "First Name", "Last Name", "Date Of Birth", "Phone", "Email", "Branch", "Course", "Roll No.",
                "City", "Pincode", "Address", "Register By" };
        for (int i = 0; i < 6; i++) {
            labels[i] = new JLabel(datas[i]);

            if (i == 2)
                fields[2] = new JTextField("YYYY-MM-DD");
            else
                fields[i] = new JTextField();
            labels[i].setBounds(20, top, 100, 30);
            fields[i].setBounds(130, top, 200, 30);
            form.add(labels[i]);
            form.add(fields[i]);
            top += 40;
        }
        top = 100;
        for (int i = 6; i < 12; i++) {
            labels[i] = new JLabel(datas[i]);
            if (i == 7)
                fields[7] = new JTextField(Integer.toString(rollno));
            else
                fields[i] = new JTextField();
            labels[i].setBounds(390, top, 100, 30);
            fields[i].setBounds(490, top, 200, 30);
            form.add(labels[i]);
            form.add(fields[i]);
            top += 40;
        }

        JButton submit = new JButton("Submit");
        submit.setBounds(130, top + 40, 200, 30);
        submit.addActionListener(this);
        form.add(submit);
        panel.setBounds(600, 0, width - 600, height);
        // panel.setBackground(Color.WHITE);
        label.add(panel);
        label.add(leftPanel);
        FRAME.setTitle("New Student");
        FRAME.add(label);
        // FRAME.add(leftPanel);
        FRAME.setBounds(0, 0, width, height);
        FRAME.setVisible(true);
    }

    public void actionPerformed(ActionEvent AE) {
        String command = AE.getActionCommand();
        System.out.println(command);
        if (command == "Home") {
            new Border();
            FRAME.dispose();
        } else if (command == "Student") {
            try {
                new Student();
                FRAME.dispose();
            } catch (Exception e) {
                System.out.println(e);

            }

        } else if (command == "Submit") {
            for (int i = 0; i < 11; i++) {
                String info = fields[i].getText();
                if (info.equals("")) {
                    JOptionPane.showMessageDialog(FRAME, labels[i].getText() + " is missing !");
                    return;
                }
                System.out.println(fields[i].getText());
                INFO[i] = fields[i].getText();
            }
            for (int i = 0; i < 11; i++) {
                if (i == 2)
                    fields[i].setText("YYYY-MM-DD");
                else
                    fields[i].setText("");
            }
            // save to DB after validation
            try {
                App app = new App();
                String query = "insert into student values('" + INFO[0] + "','" + INFO[1] + "','" + INFO[2] + "','"
                        + INFO[3] + "','" + INFO[4] + "','" + INFO[5] + "','" + INFO[6] + "','" + INFO[7] + "','"
                        + INFO[8] + "','" + INFO[9] + "','" + INFO[10] + "')";
                app.s.executeUpdate(query);
                JOptionPane.showMessageDialog(FRAME, "Form Submitted Successfully");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // main method
    public static void main(String argvs[]) {
        new NewS();
    }
}