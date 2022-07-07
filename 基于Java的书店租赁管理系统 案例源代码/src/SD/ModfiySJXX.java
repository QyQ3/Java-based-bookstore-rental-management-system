package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModfiySJXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2, panel3;
    JLabel TipLabel = new JLabel("������ŵ����ѯ�����������������Ϣ");
    JLabel BnumLabel, bookLabel, BnameLabel, chubansheLabel, jiageLabel, kucunLabel, BbeizhuLabel;
    JTextField BnumTextField, bookTextField, BnameTextField, chubansheTextField, jiageTextField, kucunTextField, BbeizhuTextField;
    Container c;
    JButton clearBtn, selBtn, updateBtn, exitBtn;
    public ModfiySJXX() {
        super("�޸��鼮��Ϣ");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //��ʾ��Ϣ
        panel3 = new JPanel();
        panel3.add(TipLabel);
        c.add(panel3, BorderLayout.NORTH);
        //���Label
        BnumLabel= new JLabel("���", JLabel.CENTER);
        bookLabel = new JLabel("����", JLabel.CENTER);
        BnameLabel = new JLabel("����", JLabel.CENTER);
        chubansheLabel = new JLabel("������", JLabel.CENTER);
        jiageLabel = new JLabel("�۸�", JLabel.CENTER);
        kucunLabel = new JLabel("�����", JLabel.CENTER);
        BbeizhuLabel = new JLabel("��ע", JLabel.CENTER);
        //���TextField
        BnumTextField = new JTextField(25);
        bookTextField = new JTextField(25);
        BnameTextField = new JTextField(25);
        chubansheTextField = new JTextField(25);
        jiageTextField = new JTextField(25);
        kucunTextField = new JTextField(25);
        BbeizhuTextField = new JTextField(25);
        //���Button
        clearBtn = new JButton("���");
        selBtn = new JButton("��ѯ");
        updateBtn = new JButton("����");
        exitBtn = new JButton("�˳�");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        updateBtn.setEnabled(false);
        //������� ��Ϣ������
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(7, 2));
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
        panel1.add(kucunLabel);
        panel1.add(kucunTextField);
        panel1.add(BbeizhuLabel);
        panel1.add(BbeizhuTextField);
        //��ť��
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 4));
        panel2.add(clearBtn);
        panel2.add(selBtn);
        panel2.add(updateBtn);
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
            BnumTextField.setText("");
            bookTextField.setText("");
            BnameTextField.setText("");
            chubansheTextField.setText("");
            jiageTextField.setText("");
            kucunTextField.setText("");
            BbeizhuTextField.setText("");
            updateBtn.setEnabled(false);
        }
        else if (e.getSource() == selBtn) { //��ѯ��ť
            try {
                String strSQL = "select * from SJXX where Bnum=" + BnumTextField.getText().trim();
                if (BnumTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "��������ţ�");
                }
                else if (!db.getResult(strSQL).first()) {
                    JOptionPane.showMessageDialog(null, "����û�д��飡");
                }
                else {
                    rs = db.getResult(strSQL);
                    rs.first();
                    bookTextField.setText(rs.getString(2).trim());
                    BnameTextField.setText(rs.getString(3).trim());
                    chubansheTextField.setText(rs.getString(4).trim());
                    jiageTextField.setText(rs.getString(5).trim());
                    kucunTextField.setText(rs.getString(6).trim());
                    BbeizhuTextField.setText(rs.getString(7).trim());
                    updateBtn.setEnabled(true);
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
        else if (e.getSource() == updateBtn) {
            try {
                if (BnumTextField.getText().trim().equals("")) { //�ж�����Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��Ų���Ϊ�գ�");
                } else if (bookTextField.getText().trim().equals("")) { //�ж������Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
                } else if (BnameTextField.getText().trim().equals("")) { //�ж������Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "���߲���Ϊ�գ�");
                } else if (chubansheTextField.getText().trim().equals("")) { //�жϳ������Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "�����粻��Ϊ�գ�");
                } else if (jiageTextField.getText().trim().equals("")) { //�жϼ۸��Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "�۸���Ϊ�գ�");
                } else if (kucunTextField.getText().trim().equals("")) { //�жϿ���Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��治��Ϊ�գ�");
                } else {
                    String strSQL = "{call modb (" +
                            BnumTextField.getText().trim() + ",'" +
                            bookTextField.getText().trim() + "','" +
                            BnameTextField.getText().trim() + "','" +
                            chubansheTextField.getText().trim() + "'," +
                            jiageTextField.getText().trim() + "," +
                            kucunTextField.getText().trim() + ",'" +
                            BbeizhuTextField.getText().trim() + "')}";
                    if (db.updateSql(strSQL) > 0) {
                        //rs = db.getResult(strSQL);
                        JOptionPane.showMessageDialog(null, "�����鼮��Ϣ�ɹ���");
                        db.closeConnection();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "�����鼮��Ϣʧ�ܣ�");
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


