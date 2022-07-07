package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddHYXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JPanel panel1, panel2;
    JLabel HnameLabel, sexLabel, zhanghuLabel, numLabel;
    JTextField HnameTextField, zhanghuTextField, numTextField;
    JComboBox sexComboBox;
    JButton addBtn, cancelBtn;
    public AddHYXX() { //构建界面
        super("添加会员");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //添加label
        HnameLabel = new JLabel("姓名", JLabel.CENTER);
        sexLabel = new JLabel("性别", JLabel.CENTER);
        zhanghuLabel = new JLabel("充值金额", JLabel.CENTER);
        numLabel = new JLabel("电话号码", JLabel.CENTER);
        //添加TextField
        HnameTextField = new JTextField(20);
        zhanghuTextField = new JTextField(20);
        numTextField = new JTextField(20);
        //添加ComboBox
        sexComboBox = new JComboBox();
        sexComboBox.addItem("男");
        sexComboBox.addItem("女");
        //添加Button
        addBtn = new JButton("添加");
        cancelBtn = new JButton("取消");
        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        //填充容器 信息填写区
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4, 2));
        panel1.add(HnameLabel);
        panel1.add(HnameTextField);
        panel1.add(sexLabel);
        panel1.add(sexComboBox);
        panel1.add(zhanghuLabel);
        panel1.add(zhanghuTextField);
        panel1.add(numLabel);
        panel1.add(numTextField);
        c.add(panel1, BorderLayout.CENTER);
        //下方按钮区
        panel2 = new JPanel();
        panel2.add(addBtn);
        panel2.add(cancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
        setSize(250, 100);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) { //取消按钮
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == addBtn) { //添加按钮
            try {
                String strSQL = "select * from HYXX where num='" + numTextField.getText().trim() + "'"; //判断电话号sql语句
                if (HnameTextField.getText().trim().equals("")) { //判断姓名是否为空
                    JOptionPane.showMessageDialog(null, "姓名不能为空！");
                } else if (zhanghuTextField.getText().trim().equals("")) { //判断充值金额是否为空
                    JOptionPane.showMessageDialog(null, "充值金额不能为空！");
                } else if (numTextField.getText().trim().equals("")) { //判断电话号码是否为空
                    JOptionPane.showMessageDialog(null, "电话号码不能为空！");
                } else {
                    if (db.getResult(strSQL).first()) { //判断电话是否被注册过
                        JOptionPane.showMessageDialog(null, "此电话号码已经被注册，请重新输入！");
                    } else {
                        //调用 添加会员信息 存储过程语句
                        strSQL = "{call addh ('" +
                                HnameTextField.getText().trim() + "','" +
                                sexComboBox.getSelectedItem() + "'," +
                                zhanghuTextField.getText().trim() + ",'" +
                                numTextField.getText().trim() + "')}";
                        if (db.updateSql(strSQL) > 0) { //判断是否成功执行
                            //rs = db.getResult(strSQL);
                            JOptionPane.showMessageDialog(null, "添加用户成功！");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "添加用户失败！");
                            db.closeConnection();
                            this.dispose();
                        }
                    }
                }
            } catch (SQLException sqle) {
                System.out.println(sqle.toString());
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
