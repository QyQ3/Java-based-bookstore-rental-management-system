package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnZLXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    Container c;
    JLabel HnumLabel,BnumLabel, bookLabel, ZnumLabel;
    JTextField HnumTextField, BnumTextField, ZnumTextField;
    JButton yesBtn, cancelBtn;
    JComboBox bookComboBox = new JComboBox();
    public ReturnZLXX() {
        super("书籍还入");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //添加Label
        HnumLabel = new JLabel("会员号", JLabel.CENTER);
        BnumLabel = new JLabel("书号", JLabel.CENTER);
        bookLabel = new JLabel("书名", JLabel.CENTER);
        ZnumLabel = new JLabel("租赁号", JLabel.CENTER);
        //添加TextField
        HnumTextField = new JTextField(20);
        BnumTextField = new JTextField(20);
        ZnumTextField = new JTextField(20);
        //查询书名 添加ComboBox
        try {
            String strSQL = "select DISTINCT book from ZLXX where Hdate is null";
            rs = db.getResult(strSQL);
            while (rs.next()) {
                bookComboBox.addItem(rs.getString(1));
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
        panel1.setLayout(new GridLayout(4, 2));
        panel1.add(HnumLabel);
        panel1.add(HnumTextField);
        panel1.add(BnumLabel);
        panel1.add(BnumTextField);
        panel1.add(bookLabel);
        panel1.add(bookComboBox);
        panel1.add(ZnumLabel);
        panel1.add(ZnumTextField);
        c.add(panel1, BorderLayout.CENTER);
        //按钮区
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
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
        }
        else if (e.getSource() == yesBtn) { //确定按钮
            if (HnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入会员号！");
            }
            else if (BnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入书号！");
            }
            else if (ZnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入租赁号！");
            }
            else if (bookComboBox.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "没有出借过书！");
            }
            else {
                try {
                    String strSQL = "select * from ZLXX where Znum=" +
                                    ZnumTextField.getText().trim() + " and Hnum=" +
                                    HnumTextField.getText().trim() + " and Bnum=" +
                                    BnumTextField.getText().trim() + " and book='" +
                                    bookComboBox.getSelectedItem() + "'";
                    if(!db.getResult(strSQL).first()){
                        JOptionPane.showMessageDialog(null, "会员没有借过此书！");
                    } else {
                        strSQL = "{call hbook (" +
                                ZnumTextField.getText().trim() + "," +
                                BnumTextField.getText().trim() + ")}";
                        if (db.updateSql(strSQL) > 0) {
                            //rs = db.getResult(strSQL);
                            JOptionPane.showMessageDialog(null, "还书完成！");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "还书失败！");
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
}
