package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModfiySJXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2, panel3;
    JLabel TipLabel = new JLabel("输入书号点击查询，将调出此书相关信息");
    JLabel BnumLabel, bookLabel, BnameLabel, chubansheLabel, jiageLabel, kucunLabel, BbeizhuLabel;
    JTextField BnumTextField, bookTextField, BnameTextField, chubansheTextField, jiageTextField, kucunTextField, BbeizhuTextField;
    Container c;
    JButton clearBtn, selBtn, updateBtn, exitBtn;
    public ModfiySJXX() {
        super("修改书籍信息");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //提示信息
        panel3 = new JPanel();
        panel3.add(TipLabel);
        c.add(panel3, BorderLayout.NORTH);
        //添加Label
        BnumLabel= new JLabel("书号", JLabel.CENTER);
        bookLabel = new JLabel("书名", JLabel.CENTER);
        BnameLabel = new JLabel("作者", JLabel.CENTER);
        chubansheLabel = new JLabel("出版社", JLabel.CENTER);
        jiageLabel = new JLabel("价格", JLabel.CENTER);
        kucunLabel = new JLabel("库存量", JLabel.CENTER);
        BbeizhuLabel = new JLabel("备注", JLabel.CENTER);
        //添加TextField
        BnumTextField = new JTextField(25);
        bookTextField = new JTextField(25);
        BnameTextField = new JTextField(25);
        chubansheTextField = new JTextField(25);
        jiageTextField = new JTextField(25);
        kucunTextField = new JTextField(25);
        BbeizhuTextField = new JTextField(25);
        //添加Button
        clearBtn = new JButton("清空");
        selBtn = new JButton("查询");
        updateBtn = new JButton("更新");
        exitBtn = new JButton("退出");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        updateBtn.setEnabled(false);
        //填充容器 信息处理区
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(7, 2));
        panel1.add(BnumLabel);
        panel1.add(BnumTextField);
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
        panel2.setLayout(new GridLayout(1, 4));
        panel2.add(clearBtn);
        panel2.add(selBtn);
        panel2.add(updateBtn);
        panel2.add(exitBtn);
        c.add(panel1, BorderLayout.CENTER);
        c.add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) { //退出按钮
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == clearBtn) { //清空按钮
            BnumTextField.setText("");
            bookTextField.setText("");
            BnameTextField.setText("");
            chubansheTextField.setText("");
            jiageTextField.setText("");
            kucunTextField.setText("");
            BbeizhuTextField.setText("");
            updateBtn.setEnabled(false);
        }
        else if (e.getSource() == selBtn) { //查询按钮
            try {
                String strSQL = "select * from SJXX where Bnum=" + BnumTextField.getText().trim();
                if (BnumTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入书号！");
                }
                else if (!db.getResult(strSQL).first()) {
                    JOptionPane.showMessageDialog(null, "库中没有此书！");
                }
                else {
                    rs = db.getResult(strSQL);
                    rs.first();
                    bookTextField.setText(rs.getString(2).trim());
                    BnameTextField.setText(rs.getString(3).trim());
                    chubansheTextField.setText(rs.getString(4).trim());
                    jiageTextField.setText(rs.getString(5).trim());
                    kucunTextField.setText(rs.getString(6).trim());
                    BbeizhuTextField.setText(rs.getString(7).trim());
                    updateBtn.setEnabled(true);
                }
            }
            catch (NullPointerException upe) {
                System.out.println(upe.toString());
            }
            catch (SQLException sqle) {
                System.out.println(sqle.toString());
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        else if (e.getSource() == updateBtn) {
            try {
                if (BnumTextField.getText().trim().equals("")) { //判断书号是否为空
                    JOptionPane.showMessageDialog(null, "书号不能为空！");
                } else if (bookTextField.getText().trim().equals("")) { //判断书名是否为空
                    JOptionPane.showMessageDialog(null, "书名不能为空！");
                } else if (BnameTextField.getText().trim().equals("")) { //判断作者是否为空
                    JOptionPane.showMessageDialog(null, "作者不能为空！");
                } else if (chubansheTextField.getText().trim().equals("")) { //判断出版社是否为空
                    JOptionPane.showMessageDialog(null, "出版社不能为空！");
                } else if (jiageTextField.getText().trim().equals("")) { //判断价格是否为空
                    JOptionPane.showMessageDialog(null, "价格不能为空！");
                } else if (kucunTextField.getText().trim().equals("")) { //判断库存是否为空
                    JOptionPane.showMessageDialog(null, "库存不能为空！");
                } else {
                    String strSQL = "{call modb (" +
                            BnumTextField.getText().trim() + ",'" +
                            bookTextField.getText().trim() + "','" +
                            BnameTextField.getText().trim() + "','" +
                            chubansheTextField.getText().trim() + "'," +
                            jiageTextField.getText().trim() + "," +
                            kucunTextField.getText().trim() + ",'" +
                            BbeizhuTextField.getText().trim() + "')}";
                    if (db.updateSql(strSQL) > 0) {
                        //rs = db.getResult(strSQL);
                        JOptionPane.showMessageDialog(null, "更新书籍信息成功！");
                        db.closeConnection();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "更新书籍信息失败！");
                        db.closeConnection();
                        this.dispose();
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
}


