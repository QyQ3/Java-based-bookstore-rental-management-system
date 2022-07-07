package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowZLXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    Container c;
    JLabel HnumLabel, HnameLabel, BnumLabel, bookLabel, ZbeizhuLabel;
    JTextField HnumTextField, HnameTextField, BnumTextField, ZbeizhuTextField;
    JButton clearBtn, yesBtn, cancelBtn;
    JComboBox bookComboBox = new JComboBox();
    public BorrowZLXX() {
        super("借出书籍");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //添加Label
        HnumLabel = new JLabel("会员号", JLabel.CENTER);
        HnameLabel = new JLabel("姓名", JLabel.CENTER);
        BnumLabel = new JLabel("书号", JLabel.CENTER);
        bookLabel = new JLabel("书名", JLabel.CENTER);
        ZbeizhuLabel = new JLabel("备注", JLabel.CENTER);
        //添加TextField
        HnumTextField = new JTextField(25);
        HnameTextField = new JTextField(25);
        BnumTextField = new JTextField(25);
        ZbeizhuTextField = new JTextField(25);
        //查询书名 添加ComboBox
        try {
            String strSQL =
                    "select book from SJXX where kucun > 0";
            rs = db.getResult(strSQL);
            while (rs.next()) {
                bookComboBox.addItem(rs.getString(1).trim());
            }
        }
        catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        //填充容器 信息处理区
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 2));
        panel1.add(HnumLabel);
        panel1.add(HnumTextField);
        panel1.add(HnameLabel);
        panel1.add(HnameTextField);
        panel1.add(BnumLabel);
        panel1.add(BnumTextField);
        panel1.add(bookLabel);
        panel1.add(bookComboBox);
        panel1.add(ZbeizhuLabel);
        panel1.add(ZbeizhuTextField);
        c.add(panel1, BorderLayout.CENTER);
        //按钮区
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
        yesBtn = new JButton("确定");
        cancelBtn = new JButton("取消");
        yesBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        panel2.add(yesBtn);
        panel2.add(cancelBtn);
        c.add(panel2, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) { //取消按钮
            db.closeConnection();
            this.dispose();
        } else if (e.getSource() == yesBtn) { //确定按钮
            if (HnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入会员号!");
            } else if(HnameTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入会员姓名！");
            } else if(BnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入书号！");
            } else if (bookComboBox.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "对不起，现在书库里没有书，\n你现在不能借书!");
            }
            else {
                try {
                    String strSQL = "select * from HYXX where Hnum=" +
                                    HnumTextField.getText().trim() + "and Hname='" +
                                    HnameTextField.getText().trim() + "'";
                    if(!db.getResult(strSQL).first()){
                        JOptionPane.showMessageDialog(null, "未查到此会员信息！");
                    } else {
                        strSQL = "select * from SJXX where Bnum=" +
                                BnumTextField.getText().trim() + "and book='" +
                                bookComboBox.getSelectedItem() + "'";
                        if (!db.getResult(strSQL).first()) {
                            JOptionPane.showMessageDialog(null, "书号填写错误！");
                        } else {
                            strSQL = "{call jbook (" +
                                    HnumTextField.getText().trim() + ",'" +
                                    HnameTextField.getText().trim() + "'," +
                                    BnumTextField.getText().trim() + ",'" +
                                    bookComboBox.getSelectedItem() + "','" +
                                    ZbeizhuTextField.getText().trim() + "')}";
                            if (db.updateSql(strSQL) > 0) { //判断是否成功执行
                                //rs = db.getResult(strSQL);
                                JOptionPane.showMessageDialog(null, "借阅成功！");
                                db.closeConnection();
                                this.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "借阅失败！");
                                db.closeConnection();
                                this.dispose();
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }

    }
}

