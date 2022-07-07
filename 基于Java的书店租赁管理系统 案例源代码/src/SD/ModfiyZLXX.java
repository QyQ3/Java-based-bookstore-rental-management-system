package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModfiyZLXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JLabel TipLabel = new JLabel("输入书号点击查询，将调出此书相关信息");
    JLabel ZnumLabel,
           HnumLabel, HnameLabel,
           BnumLabel, bookLabel, BnameLabel,chubansheLabel,
           jiageLabel,ZbeizhuLabel;
    JTextField ZnumTextField,
               HnumTextField, HnameTextField,
               BnumTextField, bookTextField, BnameTextField, chubansheTextField,
               jiageTextField, ZbeizhuTextField;
    JButton selBtn, exitBtn, clearBtn, deleteBtn;
    JPanel panel1, panel2, panel3;
    public ModfiyZLXX() {
        super("修改租赁信息");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //提示信息
        panel3 = new JPanel();
        panel3.add(TipLabel);
        c.add(panel3, BorderLayout.NORTH);
        //添加Label
        ZnumLabel = new JLabel("租赁号", JLabel.CENTER);
        HnumLabel = new JLabel("会员号", JLabel.CENTER);
        HnameLabel = new JLabel("姓名", JLabel.CENTER);
        BnumLabel= new JLabel("书号", JLabel.CENTER);
        bookLabel = new JLabel("书名", JLabel.CENTER);
        BnameLabel = new JLabel("作者", JLabel.CENTER);
        chubansheLabel = new JLabel("出版社", JLabel.CENTER);
        jiageLabel = new JLabel("价格", JLabel.CENTER);
        ZbeizhuLabel = new JLabel("备注", JLabel.CENTER);
        //添加TextField
        ZnumTextField = new JTextField(25);
        HnumTextField = new JTextField(25);
        HnameTextField = new JTextField(25);
        BnumTextField = new JTextField(25);
        bookTextField = new JTextField(25);
        BnameTextField = new JTextField(25);
        chubansheTextField = new JTextField(25);
        jiageTextField = new JTextField(25);
        ZbeizhuTextField = new JTextField(25);
        //添加Button
        clearBtn = new JButton("清空");
        selBtn = new JButton("查询");
        deleteBtn = new JButton("删除");
        exitBtn = new JButton("退出");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        deleteBtn.setEnabled(false);
        //填充容器 信息处理区
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(9, 2));
        panel1.add(ZnumLabel);
        panel1.add(ZnumTextField);
        panel1.add(HnumLabel);
        panel1.add(HnumTextField);
        panel1.add(HnameLabel);
        panel1.add(HnameTextField);
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
        panel1.add(ZbeizhuLabel);
        panel1.add(ZbeizhuTextField);
        //按钮区
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 4));
        panel2.add(clearBtn);
        panel2.add(selBtn);
        panel2.add(deleteBtn);
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
            ZnumTextField.setText("");
            HnumTextField.setText("");
            HnameTextField.setText("");
            BnumTextField.setText("");
            bookTextField.setText("");
            BnameTextField.setText("");
            chubansheTextField.setText("");
            jiageTextField.setText("");
            ZbeizhuTextField.setText("");
            deleteBtn.setEnabled(false);
        }
        else if (e.getSource() == selBtn) { //查询按钮
            try {
                String strSQL = "select * from ZLXX where Znum=" + ZnumTextField.getText().trim();
                if (ZnumTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入租赁号！");
                }
                else if (!db.getResult(strSQL).first()) {
                    JOptionPane.showMessageDialog(null, "没有此租赁信息！");
                }
                else {
                    rs = db.getResult(strSQL);
                    rs.first();
                    HnumTextField.setText(rs.getString(2).trim());
                    HnameTextField.setText(rs.getString(3).trim());
                    BnumTextField.setText(rs.getString(4).trim());
                    bookTextField.setText(rs.getString(5).trim());
                    BnameTextField.setText(rs.getString(6).trim());
                    chubansheTextField.setText(rs.getString(7).trim());
                    jiageTextField.setText(rs.getString(10).trim());
                    ZbeizhuTextField.setText(rs.getString(11).trim());
                    deleteBtn.setEnabled(true);
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
        else if (e.getSource() == deleteBtn) {
            try {
                if (ZnumTextField.getText().trim().equals("")) { //判断租赁号是否为空
                    JOptionPane.showMessageDialog(null, "租赁号不能为空！");
                } else if (BnumTextField.getText().trim().equals("")) { //判断书名是否为空
                    JOptionPane.showMessageDialog(null, "书号不能为空！");
                } else {
                    String strSQL = "{call dbook (" +
                            ZnumTextField.getText().trim() + "," +
                            BnumTextField.getText().trim() + ")}";
                    if (db.updateSql(strSQL) > 0) {
                        //rs = db.getResult(strSQL);
                        JOptionPane.showMessageDialog(null, "成功修改信息！");
                        db.closeConnection();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "修改信息失败！");
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

