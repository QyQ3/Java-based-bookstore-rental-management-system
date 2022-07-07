package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AddSJXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    JLabel bookLabel, BnameLabel, chubansheLabel, jiageLabel, kucunLabel, BbeizhuLabel;
    JTextField bookTextField, BnameTextField, chubansheTextField, jiageTextField, kucunTextField, BbeizhuTextField;
    Container c;
    JButton clearBtn, addBtn, exitBtn;
    public AddSJXX() {
        super("添加书籍");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //添加Label
        bookLabel = new JLabel("书名", JLabel.CENTER);
        BnameLabel = new JLabel("作者", JLabel.CENTER);
        chubansheLabel = new JLabel("出版社", JLabel.CENTER);
        jiageLabel = new JLabel("价格", JLabel.CENTER);
        kucunLabel = new JLabel("库存量", JLabel.CENTER);
        BbeizhuLabel = new JLabel("备注", JLabel.CENTER);
        //添加TextField
        bookTextField = new JTextField(25);
        BnameTextField = new JTextField(25);
        chubansheTextField = new JTextField(25);
        jiageTextField = new JTextField(25);
        kucunTextField = new JTextField(25);
        BbeizhuTextField = new JTextField(25);
        //添加Button
        addBtn = new JButton("添加");
        addBtn.addActionListener(this);
        exitBtn = new JButton("退出");
        exitBtn.addActionListener(this);
        //填充容器 信息处理区
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(6, 2));
        panel1.add(bookLabel);
        panel1.add(bookTextField);
        panel1.add(BnameLabel);
        panel1.add(BnameTextField);
        panel1.add(chubansheLabel);
        panel1.add(chubansheTextField);
        panel1.add(jiageLabel);
        panel1.add(jiageTextField);
        panel1.add(kucunLabel);
        panel1.add(kucunTextField);
        panel1.add(BbeizhuLabel);
        panel1.add(BbeizhuTextField);
        //按钮区
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(addBtn);
        panel2.add(exitBtn);
        c.add(panel1, BorderLayout.CENTER);
        c.add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) { //退出按钮
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == addBtn) { //添加按钮
            if (bookTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "书名不能为空！");
            }
            else if (BnameLabel.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "作者不能为空！");
            }
            else if (chubansheTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "出版社不能为空！");
            }
            else if (jiageTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "价格不能为空！");
            }
            else if (kucunTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "库存不能为空！");
            }
            else {
                try {
                    String strSQL = "{call addb ('"+
                            bookTextField.getText().trim() + "','" +
                            BnameTextField.getText().trim() + "','" +
                            chubansheTextField.getText().trim() + "','" +
                            jiageTextField.getText().trim() + "','" +
                            kucunTextField.getText().trim() + "','" +
                            BbeizhuTextField.getText().trim() + "')}";
                    if (db.updateSql(strSQL) > 0) { //判断是否成功执行
                        //rs = db.getResult(strSQL);
                        JOptionPane.showMessageDialog(null, "添加书籍成功！");
                        db.closeConnection();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "添加书籍失败！");
                        db.closeConnection();
                        this.dispose();
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }

        }
    }
}

