package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AddSJXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    JLabel bookLabel, BnameLabel, chubansheLabel, jiageLabel, kucunLabel, BbeizhuLabel;
    JTextField bookTextField, BnameTextField, chubansheTextField, jiageTextField, kucunTextField, BbeizhuTextField;
    Container c;
    JButton clearBtn, addBtn, exitBtn;
    public AddSJXX() {
        super("����鼮");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //���Label
        bookLabel = new JLabel("����", JLabel.CENTER);
        BnameLabel = new JLabel("����", JLabel.CENTER);
        chubansheLabel = new JLabel("������", JLabel.CENTER);
        jiageLabel = new JLabel("�۸�", JLabel.CENTER);
        kucunLabel = new JLabel("�����", JLabel.CENTER);
        BbeizhuLabel = new JLabel("��ע", JLabel.CENTER);
        //���TextField
        bookTextField = new JTextField(25);
        BnameTextField = new JTextField(25);
        chubansheTextField = new JTextField(25);
        jiageTextField = new JTextField(25);
        kucunTextField = new JTextField(25);
        BbeizhuTextField = new JTextField(25);
        //���Button
        addBtn = new JButton("���");
        addBtn.addActionListener(this);
        exitBtn = new JButton("�˳�");
        exitBtn.addActionListener(this);
        //������� ��Ϣ������
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(6, 2));
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
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(addBtn);
        panel2.add(exitBtn);
        c.add(panel1, BorderLayout.CENTER);
        c.add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) { //�˳���ť
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == addBtn) { //��Ӱ�ť
            if (bookTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
            }
            else if (BnameLabel.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "���߲���Ϊ�գ�");
            }
            else if (chubansheTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "�����粻��Ϊ�գ�");
            }
            else if (jiageTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "�۸���Ϊ�գ�");
            }
            else if (kucunTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "��治��Ϊ�գ�");
            }
            else {
                try {
                    String strSQL = "{call addb ('"+
                            bookTextField.getText().trim() + "','" +
                            BnameTextField.getText().trim() + "','" +
                            chubansheTextField.getText().trim() + "','" +
                            jiageTextField.getText().trim() + "','" +
                            kucunTextField.getText().trim() + "','" +
                            BbeizhuTextField.getText().trim() + "')}";
                    if (db.updateSql(strSQL) > 0) { //�ж��Ƿ�ɹ�ִ��
                        //rs = db.getResult(strSQL);
                        JOptionPane.showMessageDialog(null, "����鼮�ɹ���");
                        db.closeConnection();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "����鼮ʧ�ܣ�");
                        db.closeConnection();
                        this.dispose();
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }

        }
    }
}

