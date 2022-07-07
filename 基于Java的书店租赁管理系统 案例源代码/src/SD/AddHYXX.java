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
    public AddHYXX() { //��������
        super("��ӻ�Ա");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //���label
        HnameLabel = new JLabel("����", JLabel.CENTER);
        sexLabel = new JLabel("�Ա�", JLabel.CENTER);
        zhanghuLabel = new JLabel("��ֵ���", JLabel.CENTER);
        numLabel = new JLabel("�绰����", JLabel.CENTER);
        //���TextField
        HnameTextField = new JTextField(20);
        zhanghuTextField = new JTextField(20);
        numTextField = new JTextField(20);
        //���ComboBox
        sexComboBox = new JComboBox();
        sexComboBox.addItem("��");
        sexComboBox.addItem("Ů");
        //���Button
        addBtn = new JButton("���");
        cancelBtn = new JButton("ȡ��");
        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        //������� ��Ϣ��д��
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
        //�·���ť��
        panel2 = new JPanel();
        panel2.add(addBtn);
        panel2.add(cancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
        setSize(250, 100);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) { //ȡ����ť
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == addBtn) { //��Ӱ�ť
            try {
                String strSQL = "select * from HYXX where num='" + numTextField.getText().trim() + "'"; //�жϵ绰��sql���
                if (HnameTextField.getText().trim().equals("")) { //�ж������Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
                } else if (zhanghuTextField.getText().trim().equals("")) { //�жϳ�ֵ����Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��ֵ����Ϊ�գ�");
                } else if (numTextField.getText().trim().equals("")) { //�жϵ绰�����Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "�绰���벻��Ϊ�գ�");
                } else {
                    if (db.getResult(strSQL).first()) { //�жϵ绰�Ƿ�ע���
                        JOptionPane.showMessageDialog(null, "�˵绰�����Ѿ���ע�ᣬ���������룡");
                    } else {
                        //���� ��ӻ�Ա��Ϣ �洢�������
                        strSQL = "{call addh ('" +
                                HnameTextField.getText().trim() + "','" +
                                sexComboBox.getSelectedItem() + "'," +
                                zhanghuTextField.getText().trim() + ",'" +
                                numTextField.getText().trim() + "')}";
                        if (db.updateSql(strSQL) > 0) { //�ж��Ƿ�ɹ�ִ��
                            //rs = db.getResult(strSQL);
                            JOptionPane.showMessageDialog(null, "����û��ɹ���");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "����û�ʧ�ܣ�");
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
