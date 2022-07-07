package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteHYXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    Container c;
    JLabel HnumLabel, HnameLabel, sexLabel, zhanghuLabel, numLabel;
    JTextField HnumTextField, HnameTextField, sexTextField, zhanghuTextField, numTextField;

    JButton selBtn, cancelBtn, clearBtn, deteleBtn;
    public DeleteHYXX() {
        super("ɾ����Ա");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //���Label
        HnumLabel = new JLabel("��Ա��", JLabel.CENTER);
        HnameLabel = new JLabel("����", JLabel.CENTER);
        sexLabel = new JLabel("�Ա�", JLabel.CENTER);
        zhanghuLabel = new JLabel("�˻����", JLabel.CENTER);
        numLabel = new JLabel("�绰����", JLabel.CENTER);
        //���TextField
        HnumTextField = new JTextField(10);
        HnameTextField = new JTextField(20);
        sexTextField = new JTextField(2);
        zhanghuTextField = new JTextField(20);
        numTextField =  new JTextField(20);
        //���Button
        clearBtn = new JButton("���");
        selBtn = new JButton("��ѯ");
        deteleBtn = new JButton("ɾ��");
        cancelBtn = new JButton("ȡ��");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        deteleBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        deteleBtn.setEnabled(false);
        //������� ��Ϣ������
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 2));
        panel1.add(HnumLabel);
        panel1.add(HnumTextField);
        panel1.add(HnameLabel);
        panel1.add(HnameTextField);
        panel1.add(sexLabel);
        panel1.add(sexTextField);
        panel1.add(zhanghuLabel);
        panel1.add(zhanghuTextField);
        panel1.add(numLabel);
        panel1.add(numTextField);
        c.add(panel1, BorderLayout.CENTER);
        //��ť��
        panel2 = new JPanel();
        panel2.add(clearBtn);
        panel2.add(selBtn);
        panel2.add(deteleBtn);
        panel2.add(cancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
        setSize(300, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) { //ȡ����ť
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == clearBtn) { //��հ�ť
            HnumTextField.setText("");
            HnameTextField.setText("");
            sexTextField.setText("");
            zhanghuTextField.setText("");
            numTextField.setText("");
            deteleBtn.setEnabled(false);
        }
        else if (e.getSource() == selBtn) { //��ѯ��ť
            try {
                String strSQL = "select * from HYXX where Hnum=" +
                        HnumTextField.getText().trim() + "AND num='" +
                        numTextField.getText().trim() + "'"; //���һ�Ա��Ϣsql���
                if (HnumTextField.getText().trim().equals("")) { //�ж��Ƿ������Ա��
                    JOptionPane.showMessageDialog(null, "�������Ա�ţ�");
                }
                else if (numTextField.getText().trim().equals("")) { //�ж��Ƿ�����绰��
                    JOptionPane.showMessageDialog(null, "������绰���룡");
                }
                else if (!db.getResult(strSQL).first()) { //�ж��Ƿ��д˻�Ա��
                    JOptionPane.showMessageDialog(null, "û�д˻�Ա�����������룡");
                }
                else { //����ѯ������Ϣ�ŵ��ı���
                    rs = db.getResult(strSQL);
                    rs.first();
                    HnameTextField.setText(rs.getString(2).trim());
                    sexTextField.setText(rs.getString(3).trim());
                    zhanghuTextField.setText(rs.getString(4).trim());
                    deteleBtn.setEnabled(true);
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
        else if (e.getSource() == deteleBtn) {
            try {
                String strSQL = "select * from HYXX where Hnum=" +
                        HnumTextField.getText().trim() + "AND num='" +
                        numTextField.getText().trim() + "'"; //���һ�Ա��Ϣsql���
                if (HnumTextField.getText().trim().equals("")) { //�ж��Ƿ������Ա��
                    JOptionPane.showMessageDialog(null, "�������Ա�ţ�");
                } else if (numTextField.getText().trim().equals("")) { //�ж��Ƿ�����绰��
                    JOptionPane.showMessageDialog(null, "������绰���룡");
                } else {
                    if (db.getResult(strSQL).first()) {
                        strSQL = "{call delh (" + HnumTextField.getText().trim() + ")}";
                        if (db.updateSql(strSQL) > 0) {
                            //rs = db.getResult(strSQL);
                            JOptionPane.showMessageDialog(null, "ɾ����Ա��Ϣ�ɹ���");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "ɾ����Ա��Ϣʧ�ܣ�");
                            db.closeConnection();
                            this.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "û�д˻�Ա�����������룡");
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

    }
}
