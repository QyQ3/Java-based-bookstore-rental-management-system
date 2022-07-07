package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyHYXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    Container c;
    JLabel HnumLabel, HnameLabel, sexLabel, zhanghuLabel, numLabel;
    JTextField HnumTextField, HnameTextField, sexTextField, zhanghuTextField, numTextField;
    JButton UpdateBtn, CancelBtn, clearBtn, selBtn;
    public ModifyHYXX() { //创建界面
        super("修改会员信息");
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
        UpdateBtn = new JButton("更新");
        CancelBtn = new JButton("取消");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        UpdateBtn.addActionListener(this);
        CancelBtn.addActionListener(this);
        UpdateBtn.setEnabled(false);
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
        //下方按钮区
        panel2 = new JPanel();
        panel2.add(clearBtn);
        panel2.add(selBtn);
        panel2.add(UpdateBtn);
        panel2.add(CancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
        setSize(300, 300);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CancelBtn) { //取消按钮
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == clearBtn) { //清空按钮
            HnumTextField.setText("");
            HnameTextField.setText("");
            sexTextField.setText("");
            zhanghuTextField.setText("");
            numTextField.setText("");
            UpdateBtn.setEnabled(false);
        }
        else if (e.getSource() == selBtn) { //查询按钮
            try {
                String strSQL = "select * from HYXX where Hnum=" + HnumTextField.getText().trim(); //查找会员信息sql语句
                if (HnumTextField.getText().trim().equals("")) { //判断是否输入会员号
                    JOptionPane.showMessageDialog(null, "请输入会员号！");
                }
                else if (!db.getResult(strSQL).first()) { //判断是否有此会员
                    JOptionPane.showMessageDialog(null, "没有此会员，请重新输入！");
                }
                else { //将查询到的信息放到文本框
                    rs = db.getResult(strSQL);
                    rs.first();
                    HnameTextField.setText(rs.getString(2).trim());
                    sexTextField.setText(rs.getString(3).trim());
                    zhanghuTextField.setText(rs.getString(4).trim());
                    numTextField.setText(rs.getString(6).trim());
                    UpdateBtn.setEnabled(true);
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
        else if (e.getSource() == UpdateBtn) { //更新按钮
            //调用存储过程
            try {
                String strSQL = "select * from HYXX where num='" + numTextField.getText().trim() + "'"; //判断电话号sql语句
                if (HnameTextField.getText().trim().equals("")) { //判断姓名是否为空
                    JOptionPane.showMessageDialog(null, "姓名不能为空！");
                } else if (sexTextField.getText().trim().equals("")) { //判断性别是否为空
                    JOptionPane.showMessageDialog(null, "性别不能为空！");
                } else if (zhanghuTextField.getText().trim().equals("")) { //判断账户余额否为空
                    JOptionPane.showMessageDialog(null, "账户余额不能为空！");
                } else if (numTextField.getText().trim().equals("")) { //判断电话号码是否为空
                    JOptionPane.showMessageDialog(null, "电话号码不能为空！");
                } else {
                    if (db.getResult(strSQL).first()) { //判断电话是否被注册过
                        JOptionPane.showMessageDialog(null, "此电话号码已经被注册，请重新输入！");
                    } else {
                        strSQL = "{call modh (" +
                                HnumTextField.getText().trim() + ",'" +
                                HnameTextField.getText().trim() + "','" +
                                sexTextField.getText().trim() + "'," +
                                zhanghuTextField.getText().trim() + ",'" +
                                numTextField.getText().trim() + "')}";
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
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

    }
}

