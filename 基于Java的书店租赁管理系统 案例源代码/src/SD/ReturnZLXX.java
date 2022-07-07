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
        super("�鼮����");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //���Label
        HnumLabel = new JLabel("��Ա��", JLabel.CENTER);
        BnumLabel = new JLabel("���", JLabel.CENTER);
        bookLabel = new JLabel("����", JLabel.CENTER);
        ZnumLabel = new JLabel("���޺�", JLabel.CENTER);
        //���TextField
        HnumTextField = new JTextField(20);
        BnumTextField = new JTextField(20);
        ZnumTextField = new JTextField(20);
        //��ѯ���� ���ComboBox
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
        //������� ��Ϣ������
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
        //��ť��
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        yesBtn = new JButton("ȷ��");
        cancelBtn = new JButton("ȡ��");
        yesBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        panel2.add(yesBtn);
        panel2.add(cancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) { //ȡ����ť
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == yesBtn) { //ȷ����ť
            if (HnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "�������Ա�ţ�");
            }
            else if (BnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "��������ţ�");
            }
            else if (ZnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "���������޺ţ�");
            }
            else if (bookComboBox.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "û�г�����飡");
            }
            else {
                try {
                    String strSQL = "select * from ZLXX where Znum=" +
                                    ZnumTextField.getText().trim() + " and Hnum=" +
                                    HnumTextField.getText().trim() + " and Bnum=" +
                                    BnumTextField.getText().trim() + " and book='" +
                                    bookComboBox.getSelectedItem() + "'";
                    if(!db.getResult(strSQL).first()){
                        JOptionPane.showMessageDialog(null, "��Աû�н�����飡");
                    } else {
                        strSQL = "{call hbook (" +
                                ZnumTextField.getText().trim() + "," +
                                BnumTextField.getText().trim() + ")}";
                        if (db.updateSql(strSQL) > 0) {
                            //rs = db.getResult(strSQL);
                            JOptionPane.showMessageDialog(null, "������ɣ�");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
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
