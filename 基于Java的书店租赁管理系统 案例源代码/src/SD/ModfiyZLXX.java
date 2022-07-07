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
    JLabel TipLabel = new JLabel("������ŵ����ѯ�����������������Ϣ");
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
        super("�޸�������Ϣ");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //��ʾ��Ϣ
        panel3 = new JPanel();
        panel3.add(TipLabel);
        c.add(panel3, BorderLayout.NORTH);
        //���Label
        ZnumLabel = new JLabel("���޺�", JLabel.CENTER);
        HnumLabel = new JLabel("��Ա��", JLabel.CENTER);
        HnameLabel = new JLabel("����", JLabel.CENTER);
        BnumLabel= new JLabel("���", JLabel.CENTER);
        bookLabel = new JLabel("����", JLabel.CENTER);
        BnameLabel = new JLabel("����", JLabel.CENTER);
        chubansheLabel = new JLabel("������", JLabel.CENTER);
        jiageLabel = new JLabel("�۸�", JLabel.CENTER);
        ZbeizhuLabel = new JLabel("��ע", JLabel.CENTER);
        //���TextField
        ZnumTextField = new JTextField(25);
        HnumTextField = new JTextField(25);
        HnameTextField = new JTextField(25);
        BnumTextField = new JTextField(25);
        bookTextField = new JTextField(25);
        BnameTextField = new JTextField(25);
        chubansheTextField = new JTextField(25);
        jiageTextField = new JTextField(25);
        ZbeizhuTextField = new JTextField(25);
        //���Button
        clearBtn = new JButton("���");
        selBtn = new JButton("��ѯ");
        deleteBtn = new JButton("ɾ��");
        exitBtn = new JButton("�˳�");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        deleteBtn.setEnabled(false);
        //������� ��Ϣ������
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
        //��ť��
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
        if (e.getSource() == exitBtn) { //�˳���ť
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == clearBtn) { //��հ�ť
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
        else if (e.getSource() == selBtn) { //��ѯ��ť
            try {
                String strSQL = "select * from ZLXX where Znum=" + ZnumTextField.getText().trim();
                if (ZnumTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "���������޺ţ�");
                }
                else if (!db.getResult(strSQL).first()) {
                    JOptionPane.showMessageDialog(null, "û�д�������Ϣ��");
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
                if (ZnumTextField.getText().trim().equals("")) { //�ж����޺��Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "���޺Ų���Ϊ�գ�");
                } else if (BnumTextField.getText().trim().equals("")) { //�ж������Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��Ų���Ϊ�գ�");
                } else {
                    String strSQL = "{call dbook (" +
                            ZnumTextField.getText().trim() + "," +
                            BnumTextField.getText().trim() + ")}";
                    if (db.updateSql(strSQL) > 0) {
                        //rs = db.getResult(strSQL);
                        JOptionPane.showMessageDialog(null, "�ɹ��޸���Ϣ��");
                        db.closeConnection();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "�޸���Ϣʧ�ܣ�");
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

