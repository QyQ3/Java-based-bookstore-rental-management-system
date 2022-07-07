package SD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListSJXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JPanel panel1, panel2, panel3;
    JLabel BnumLabel, bookLabel, BnameLabel, chubansheLabel;
    JTextField BnumTextField, bookTextField, BnameTextField, chubansheTextField;
    JButton searchBtn, exitBtn;
    JTable table = null;
    DefaultTableModel defaultModel = null;
    public ListSJXX() {
        super("书籍信息");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //添加label
        BnumLabel = new JLabel("书号：", JLabel.CENTER);
        bookLabel = new JLabel("书名：", JLabel.CENTER);
        BnameLabel = new JLabel("作者：", JLabel.CENTER);
        chubansheLabel = new JLabel("出版社：", JLabel.CENTER);
        //添加TextField
        BnumTextField = new JTextField(15);
        bookTextField = new JTextField(15);
        BnameTextField = new JTextField(15);
        chubansheTextField = new JTextField(15);
        //添加Button
        searchBtn = new JButton("查询");
        searchBtn.addActionListener(this);
        exitBtn = new JButton("退出");
        exitBtn.addActionListener(this);
        //填充容器
        panel1 = new JPanel();
        panel3 = new JPanel();
        panel1.add(BnumLabel);
        panel1.add(BnumTextField);
        panel1.add(bookLabel);
        panel1.add(bookTextField);
        panel1.add(BnameLabel);
        panel1.add(BnameTextField);
        panel1.add(chubansheLabel);
        panel1.add(chubansheTextField);
        panel3.add(searchBtn);
        panel3.add(exitBtn);
        String[] name = {"书号","书名", "作者","出版社", "价格", "库存", "备注"};
        String[][] data = new String[0][0];
        defaultModel = new DefaultTableModel(data, name);
        table = new JTable(defaultModel);
        table.setPreferredScrollableViewportSize(new Dimension(800, 120));
        JScrollPane s = new JScrollPane(table);
        panel2 = new JPanel();
        panel2.add(s);
        c.add(panel1, BorderLayout.NORTH);
        c.add(panel3, BorderLayout.CENTER);
        c.add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchBtn) { //查询按钮
            String strSQL = "select * from SJXX";
            String strSql = null;
            if (BnumTextField.getText().trim().equals("") &&
                    bookTextField.getText().trim().equals("") &&
                    BnameTextField.getText().trim().equals("") &&
                    chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL;
            }
            else if (BnumTextField.getText().trim().equals("") &&
                    bookTextField.getText().trim().equals("")  &&
                    BnameTextField.getText().trim().equals("")) {
                strSql = strSQL + " where chubanshe='" + chubansheTextField.getText().trim() + "'";
            }
            else if (BnumTextField.getText().trim().equals("") &&
                    bookTextField.getText().trim().equals("")  &&
                    chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bname='" + BnameTextField.getText().trim() + "'";
            }
            else if (BnumTextField.getText().trim().equals("") &&
                    BnameTextField.getText().trim().equals("")  &&
                    chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL + " where book='" + bookTextField.getText().trim() + "'";
            }
            else if (bookTextField.getText().trim().equals("") &&
                    BnameTextField.getText().trim().equals("")  &&
                    chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bnum=" + BnumTextField.getText().trim();
            }
            else if (BnumTextField.getText().trim().equals("") &&
                    bookTextField.getText().trim().equals("")) {
                strSql = strSQL + " where chubanshe='" +
                        chubansheTextField.getText().trim() + "' and Bname='" +
                        BnameTextField.getText().trim().equals("") + "'";
            }
            else if (BnumTextField.getText().trim().equals("") &&
                    BnameTextField.getText().trim().equals("")) {
                strSql = strSQL + " where chubanshe='" +
                        chubansheTextField.getText().trim() + "' and book='" +
                        bookTextField.getText().trim().equals("") + "'";
            }
            else if (bookTextField.getText().trim().equals("") &&
                    BnameTextField.getText().trim().equals("")) {
                strSql = strSQL + " where chubanshe='" +
                        chubansheTextField.getText().trim() + "' and Bnum=" +
                        BnumTextField.getText().trim().equals("");
            }
            else if (BnumTextField.getText().trim().equals("") &&
                    chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bname='" +
                        BnameTextField.getText().trim() + "' and book='" +
                        bookTextField.getText().trim().equals("") + "'";
            }
            else if (bookTextField.getText().trim().equals("") &&
                    chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bname='" +
                        BnameTextField.getText().trim() + "' and Bnum=" +
                        BnumTextField.getText().trim().equals("");
            }
            else if (BnameTextField.getText().trim().equals("") &&
                    chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL + " where book='" +
                        bookTextField.getText().trim() + "' and Bnum=" +
                        BnumTextField.getText().trim().equals("");
            }
            else if (BnumTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bname='" +
                        BnameTextField.getText().trim() + "' and book='" +
                        bookTextField.getText().trim().equals("") + "' and chubanshe='" +
                        chubansheTextField.getText().trim().equals("") + "'";
            }
            else if (BnameTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bnum=" +
                        BnumTextField.getText().trim() + " and book='" +
                        bookTextField.getText().trim().equals("") + "' and chubanshe='" +
                        chubansheTextField.getText().trim().equals("") + "'";
            }
            else if (bookTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bnum=" +
                        BnumTextField.getText().trim() + " and Bname='" +
                        BnameTextField.getText().trim().equals("") + "' and chubanshe='" +
                        chubansheTextField.getText().trim().equals("") + "'";
            }
            else if (chubansheTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bnum=" +
                        BnumTextField.getText().trim() + " and Bname='" +
                        BnameTextField.getText().trim().equals("") + "' and book='" +
                        bookTextField.getText().trim().equals("") + "'";
            }
            else {
                strSql = strSQL + " where Bnum=" +
                        BnumTextField.getText().trim() + " and Bname='" +
                        BnameTextField.getText().trim().equals("") + "' and book='" +
                        bookTextField.getText().trim().equals("") + "' and chubanshe='" +
                        chubansheTextField.getText().trim().equals("") + "'";
            }
            try {
                //初始化表格
                int rowCount = defaultModel.getRowCount() - 1;
                int j = rowCount;
                for (int i = 0; i <= rowCount; i++) {
                    defaultModel.removeRow(j);
                    defaultModel.setRowCount(j);
                    j = j - 1;
                }
                rs = db.getResult(strSql);
                while (rs.next()) {
                    Vector data = new Vector();
                    data.addElement(rs.getString(1));
                    data.addElement(rs.getString(2));
                    data.addElement(rs.getString(3));
                    data.addElement(rs.getString(4));
                    data.addElement(rs.getString(5));
                    data.addElement(rs.getString(6));
                    data.addElement(rs.getString(7));
                    defaultModel.addRow(data);
                }
                table.revalidate();
            }
            catch (SQLException sqle) {
                System.out.println(sqle.toString());
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        else if (e.getSource() == exitBtn) { //取消按钮
            db.closeConnection();
            this.dispose();
        }
    }
}

