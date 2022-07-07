package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteHYXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    Container c;
    JLabel HnumLabel, HnameLabel, sexLabel, zhanghuLabel, numLabel;
    JTextField HnumTextField, HnameTextField, sexTextField, zhanghuTextField, numTextField;

    JButton selBtn, cancelBtn, clearBtn, deteleBtn;
    public DeleteHYXX() {
        super("删除会员");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //添加Label
        HnumLabel = new JLabel("会员号", JLabel.CENTER);
        HnameLabel = new JLabel("姓名", JLabel.CENTER);
        sexLabel = new JLabel("性别", JLabel.CENTER);
        zhanghuLabel = new JLabel("账户余额", JLabel.CENTER);
        numLabel = new JLabel("电话号码", JLabel.CENTER);
        //添加TextField
        HnumTextField = new JTextField(10);
        HnameTextField = new JTextField(20);
        sexTextField = new JTextField(2);
        zhanghuTextField = new JTextField(20);
        numTextField =  new JTextField(20);
        //添加Button
        clearBtn = new JButton("清空");
        selBtn = new JButton("查询");
        deteleBtn = new JButton("删除");
        cancelBtn = new JButton("取消");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        deteleBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        deteleBtn.setEnabled(false);
        //填充容器 信息处理区
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 2));
        panel1.add(HnumLabel);
        panel1.add(HnumTextField);
        panel1.add(HnameLabel);
        panel1.add(HnameTextField);
        panel1.add(sexLabel);
        panel1.add(sexTextField);
        panel1.add(zhanghuLabel);
        panel1.add(zhanghuTextField);
        panel1.add(numLabel);
        panel1.add(numTextField);
        c.add(panel1, BorderLayout.CENTER);
        //按钮区
        panel2 = new JPanel();
        panel2.add(clearBtn);
        panel2.add(selBtn);
        panel2.add(deteleBtn);
        panel2.add(cancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
        setSize(300, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) { //取消按钮
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == clearBtn) { //清空按钮
            HnumTextField.setText("");
            HnameTextField.setText("");
            sexTextField.setText("");
            zhanghuTextField.setText("");
            numTextField.setText("");
            deteleBtn.setEnabled(false);
        }
        else if (e.getSource() == selBtn) { //查询按钮
            try {
                String strSQL = "select * from HYXX where Hnum=" +
                        HnumTextField.getText().trim() + "AND num='" +
                        numTextField.getText().trim() + "'"; //查找会员信息sql语句
                if (HnumTextField.getText().trim().equals("")) { //判断是否输入会员号
                    JOptionPane.showMessageDialog(null, "请输入会员号！");
                }
                else if (numTextField.getText().trim().equals("")) { //判断是否输入电话号
                    JOptionPane.showMessageDialog(null, "请输入电话号码！");
                }
                else if (!db.getResult(strSQL).first()) { //判断是否有此会员号
                    JOptionPane.showMessageDialog(null, "没有此会员，请重新输入！");
                }
                else { //将查询到的信息放到文本框
                    rs = db.getResult(strSQL);
                    rs.first();
                    HnameTextField.setText(rs.getString(2).trim());
                    sexTextField.setText(rs.getString(3).trim());
                    zhanghuTextField.setText(rs.getString(4).trim());
                    deteleBtn.setEnabled(true);
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
        else if (e.getSource() == deteleBtn) {
            try {
                String strSQL = "select * from HYXX where Hnum=" +
                        HnumTextField.getText().trim() + "AND num='" +
                        numTextField.getText().trim() + "'"; //查找会员信息sql语句
                if (HnumTextField.getText().trim().equals("")) { //判断是否输入会员号
                    JOptionPane.showMessageDialog(null, "请输入会员号！");
                } else if (numTextField.getText().trim().equals("")) { //判断是否输入电话号
                    JOptionPane.showMessageDialog(null, "请输入电话号码！");
                } else {
                    if (db.getResult(strSQL).first()) {
                        strSQL = "{call delh (" + HnumTextField.getText().trim() + ")}";
                        if (db.updateSql(strSQL) > 0) {
                            //rs = db.getResult(strSQL);
                            JOptionPane.showMessageDialog(null, "删除会员信息成功！");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "删除会员信息失败！");
                            db.closeConnection();
                            this.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "没有此会员，请重新输入！");
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

    }
}
