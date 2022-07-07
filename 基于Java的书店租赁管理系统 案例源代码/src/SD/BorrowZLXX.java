package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowZLXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    Container c;
    JLabel HnumLabel, HnameLabel, BnumLabel, bookLabel, ZbeizhuLabel;
    JTextField HnumTextField, HnameTextField, BnumTextField, ZbeizhuTextField;
    JButton clearBtn, yesBtn, cancelBtn;
    JComboBox bookComboBox = new JComboBox();
    public BorrowZLXX() {
        super("����鼮");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //���Label
        HnumLabel = new JLabel("��Ա��", JLabel.CENTER);
        HnameLabel = new JLabel("����", JLabel.CENTER);
        BnumLabel = new JLabel("���", JLabel.CENTER);
        bookLabel = new JLabel("����", JLabel.CENTER);
        ZbeizhuLabel = new JLabel("��ע", JLabel.CENTER);
        //���TextField
        HnumTextField = new JTextField(25);
        HnameTextField = new JTextField(25);
        BnumTextField = new JTextField(25);
        ZbeizhuTextField = new JTextField(25);
        //��ѯ���� ���ComboBox
        try {
            String strSQL =
                    "select book from SJXX where kucun > 0";
            rs = db.getResult(strSQL);
            while (rs.next()) {
                bookComboBox.addItem(rs.getString(1).trim());
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
        panel1.setLayout(new GridLayout(5, 2));
        panel1.add(HnumLabel);
        panel1.add(HnumTextField);
        panel1.add(HnameLabel);
        panel1.add(HnameTextField);
        panel1.add(BnumLabel);
        panel1.add(BnumTextField);
        panel1.add(bookLabel);
        panel1.add(bookComboBox);
        panel1.add(ZbeizhuLabel);
        panel1.add(ZbeizhuTextField);
        c.add(panel1, BorderLayout.CENTER);
        //��ť��
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
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
        } else if (e.getSource() == yesBtn) { //ȷ����ť
            if (HnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "�������Ա��!");
            } else if(HnameTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "�������Ա������");
            } else if(BnumTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "��������ţ�");
            } else if (bookComboBox.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "�Բ������������û���飬\n�����ڲ��ܽ���!");
            }
            else {
                try {
                    String strSQL = "select * from HYXX where Hnum=" +
                                    HnumTextField.getText().trim() + "and Hname='" +
                                    HnameTextField.getText().trim() + "'";
                    if(!db.getResult(strSQL).first()){
                        JOptionPane.showMessageDialog(null, "δ�鵽�˻�Ա��Ϣ��");
                    } else {
                        strSQL = "select * from SJXX where Bnum=" +
                                BnumTextField.getText().trim() + "and book='" +
                                bookComboBox.getSelectedItem() + "'";
                        if (!db.getResult(strSQL).first()) {
                            JOptionPane.showMessageDialog(null, "�����д����");
                        } else {
                            strSQL = "{call jbook (" +
                                    HnumTextField.getText().trim() + ",'" +
                                    HnameTextField.getText().trim() + "'," +
                                    BnumTextField.getText().trim() + ",'" +
                                    bookComboBox.getSelectedItem() + "','" +
                                    ZbeizhuTextField.getText().trim() + "')}";
                            if (db.updateSql(strSQL) > 0) { //�ж��Ƿ�ɹ�ִ��
                                //rs = db.getResult(strSQL);
                                JOptionPane.showMessageDialog(null, "���ĳɹ���");
                                db.closeConnection();
                                this.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
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
}

