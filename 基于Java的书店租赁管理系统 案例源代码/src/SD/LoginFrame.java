package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFrame extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    JPanel panel1, panel2;
    JLabel userLabel, passwordLabel;
    JTextField userTextField;
    JPasswordField passwordTextField;
    JButton yesBtn, cancelBtn;
    Container c;
    ResultSet rs;
    static String  username,passwordSTR;
    static Statement stmt;
    public LoginFrame() {
        super("程序登录");
        //添加Label
        userLabel = new JLabel("账户", JLabel.CENTER);
        passwordLabel = new JLabel("密码", JLabel.CENTER);
        //添加TextField
        userTextField = new JTextField(20);
        passwordTextField = new JPasswordField(20);
        //添加Button
        yesBtn = new JButton("确定");
        cancelBtn = new JButton("取消");
        yesBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        //填充容器
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 2));
        panel2 = new JPanel();
        c = getContentPane();
        c.setLayout(new BorderLayout());
        panel1.add(userLabel);
        panel1.add(userTextField);
        panel1.add(passwordLabel);
        panel1.add(passwordTextField);
        userTextField.setText("AYQ");
        c.add(panel1, BorderLayout.CENTER);
        panel2.add(yesBtn);
        panel2.add(cancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
        setSize(240, 140);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) {
            this.dispose();
        } else {
            char[] password = passwordTextField.getPassword();
            username = userTextField.getText().trim();
            passwordSTR = new String(password);
            if (userTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不可为空!");
                return;
            }
            if (passwordSTR.equals("")) {
                JOptionPane.showMessageDialog(null, "密码不可为空!");
                return;
            }
            String strSQL;
            strSQL = "select * from GLYXX where account='" + username + "'and Gpassword='" + passwordSTR + "'";
            rs = db.getResult(strSQL);
            boolean isExist = false;
            try {
                isExist = rs.first();
            } catch (SQLException sqle) {
                System.out.println(sqle.toString());
            }
            if (!isExist) {
                JOptionPane.showMessageDialog(null, "账户或密码输入错误!");

            } else {
                try {
                    rs.first();
                    JOptionPane.showMessageDialog(null, "登陆成功");
                    this.dispose();
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setSize(500,400);
                    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                    mainFrame.setLocation( (d.width - mainFrame.getSize().width) / 2,
                            (d.height - mainFrame.getSize().height) / 2);
                    mainFrame.show();
                } catch (SQLException sqle2) {
                    System.out.println(sqle2.toString());
                }
            }
        }
    }
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        LoginFrame loginframe = new LoginFrame();
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        loginframe.setLocation( (d.width - loginframe.getSize().width) / 2,
                (d.height - loginframe.getSize().height) / 2);
        loginframe.pack();
        loginframe.show();
    }
}
