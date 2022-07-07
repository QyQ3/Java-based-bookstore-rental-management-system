package SD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListZLXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JPanel panel1, panel2;
    JLabel BnumLabel, HnumLabel;
    JTextField BnumTextField, HnumTextField;
    JButton searchBtn, exitBtn;
    JTable table = null;
    DefaultTableModel defaultModel = null;
    public ListZLXX() {
        super("������Ϣ");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        BnumLabel = new JLabel("��ţ�", JLabel.CENTER);
        HnumLabel = new JLabel("��Ա�ţ�", JLabel.CENTER);
        BnumTextField = new JTextField(15);
        HnumTextField = new JTextField(15);
        searchBtn = new JButton("��ѯ");
        exitBtn = new JButton("�˳�");
        searchBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        panel1 = new JPanel();
        panel1.add(BnumLabel);
        panel1.add(BnumTextField);
        panel1.add(HnumLabel);
        panel1.add(HnumTextField);
        panel1.add(searchBtn);
        panel1.add(exitBtn);
        panel2 = new JPanel();
        String[] name = {
                "���޺�", "��Ա��", "����", "���", "����", "��������", "��������", "�۸�", "��ע"};
        String[][] data = new String[0][0];
        defaultModel = new DefaultTableModel(data, name);
        table = new JTable(defaultModel);
        table.setPreferredScrollableViewportSize(new Dimension(600, 120));
        JScrollPane s = new JScrollPane(table);
        panel2.add(s);
        c.add(panel1, BorderLayout.NORTH);
        c.add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) { //�˳���ť
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == searchBtn) { //��ѯ��ť

            String strSQL = "select * from XX";
            String strSql = null;
            if (BnumTextField.getText().trim().equals("") &&
                    HnumTextField.getText().trim().equals("")) {
                strSql = strSQL;
            }
            else if (BnumTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Hnum=" + HnumTextField.getText().trim();
            }
            else if (HnumTextField.getText().trim().equals("")) {
                strSql = strSQL + " where Bnum=" + BnumTextField.getText().trim();
            }
            else {
                strSql = strSQL + " where Username=" +
                        HnumTextField.getText().trim() + " and bookName=" +
                        BnumTextField.getText().trim();
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
                    data.addElement(rs.getString(8));
                    data.addElement(rs.getString(9));
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
    }
}

