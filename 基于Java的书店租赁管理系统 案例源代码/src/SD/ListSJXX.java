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
        super("�鼮��Ϣ");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //���label
        BnumLabel = new JLabel("��ţ�", JLabel.CENTER);
        bookLabel = new JLabel("������", JLabel.CENTER);
        BnameLabel = new JLabel("���ߣ�", JLabel.CENTER);
        chubansheLabel = new JLabel("�����磺", JLabel.CENTER);
        //���TextField
        BnumTextField = new JTextField(15);
        bookTextField = new JTextField(15);
        BnameTextField = new JTextField(15);
        chubansheTextField = new JTextField(15);
        //���Button
        searchBtn = new JButton("��ѯ");
        searchBtn.addActionListener(this);
        exitBtn = new JButton("�˳�");
        exitBtn.addActionListener(this);
        //�������
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
        String[] name = {"���","����", "����","������", "�۸�", "���", "��ע"};
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
        if (e.getSource() == searchBtn) { //��ѯ��ť
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
                //��ʼ�����
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
        else if (e.getSource() == exitBtn) { //ȡ����ť
            db.closeConnection();
            this.dispose();
        }
    }
}

