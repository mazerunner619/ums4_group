
// import statement  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

public class Border implements ActionListener {
    JFrame FRAME;

    // constructor
    Border() {
        // creating a frame object
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension size = t.getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        FRAME = new JFrame();
        ImageIcon backImg = new ImageIcon(ClassLoader.getSystemResource("UMS_pics/004.jpg"));
        Image i1 = backImg.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon backgroundImage = new ImageIcon(i1);
        JLabel label = new JLabel(backgroundImage);
        label.setLayout(new FlowLayout());

        JButton S = new JButton("Student");
        S.addActionListener(this);
        label.add(S);

        JButton F = new JButton("Faculty");
        F.addActionListener(this);
        label.add(F);

        FRAME.setLayout(new BorderLayout());
        FRAME.add(label, BorderLayout.CENTER);
        FRAME.setBounds(0, 0, width, height);
        FRAME.setVisible(true);
    }

    public void actionPerformed(ActionEvent AE) {
        String command = AE.getActionCommand();
        System.out.println(command);
        try {
            if (command == "Student") {
                new Student();
                FRAME.dispose();
            }
            if (command == "Faculty") {
                new Teacher();
                FRAME.dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // main method
    public static void main(String argvs[]) {
        new Border();
    }
}