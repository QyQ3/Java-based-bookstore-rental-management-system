package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteSJXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JLabel TipLabel = new JLabel("请输入要删除的书号：", JLabel.CENTER);
    JTextField bookDeleteTextField = new JTextField(40);
    JButton yesBtn, exitBtn;
    JPanel panel1 = new JPanel();
    public DeleteSJXX() {
        super("删除书籍信息");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(TipLabel, BorderLayout.NORTH);
        c.add(bookDeleteTextField, BorderLayout.CENTER);
        yesBtn = new JButton("确定");
        exitBtn = new JButton("退出");
        yesBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        panel1.add(yesBtn);
        panel1.add(exitBtn);
        c.add(panel1, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) {
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == yesBtn) {
            try {
                String strSQL = "select * from SJXX where Bnum=" + bookDeleteTextField.getText().trim();
                if (bookDeleteTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入书号！");
                } else if (!db.getResult(strSQL).first()) {
                    JOptionPane.showMessageDialog(null, "书库里没有你要删除的书！");
                } else {
                    strSQL = "select * from ZLXX where Bnum=" + bookDeleteTextField.getText().trim() + "and Hdate is null";
                    if (db.getResult(strSQL).first()) {
                        JOptionPane.showMessageDialog(null, "此书还有会员没有还！\n现在还不能从书库中删除！");
                    } else {
                        strSQL = "{call delb (" + bookDeleteTextField.getText().trim() + ")}";
                        if (db.updateSql(strSQL) > 0) {
                            JOptionPane.showMessageDialog(null, "删除成功！");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败！");
                            db.closeConnection();
                            this.dispose();
                        }
                    }
                }
            }
            catch (SQLException sqle) {
                System.out.println(sqle.toString());
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
}

