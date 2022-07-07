package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyHYXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    JPanel panel1, panel2;
    Container c;
    JLabel HnumLabel, HnameLabel, sexLabel, zhanghuLabel, numLabel;
    JTextField HnumTextField, HnameTextField, sexTextField, zhanghuTextField, numTextField;
    JButton UpdateBtn, CancelBtn, clearBtn, selBtn;
    public ModifyHYXX() { //��������
        super("�޸Ļ�Ա��Ϣ");
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
        UpdateBtn = new JButton("����");
        CancelBtn = new JButton("ȡ��");
        clearBtn.addActionListener(this);
        selBtn.addActionListener(this);
        UpdateBtn.addActionListener(this);
        CancelBtn.addActionListener(this);
        UpdateBtn.setEnabled(false);
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
        //�·���ť��
        panel2 = new JPanel();
        panel2.add(clearBtn);
        panel2.add(selBtn);
        panel2.add(UpdateBtn);
        panel2.add(CancelBtn);
        c.add(panel2, BorderLayout.SOUTH);
        setSize(300, 300);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CancelBtn) { //ȡ����ť
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == clearBtn) { //��հ�ť
            HnumTextField.setText("");
            HnameTextField.setText("");
            sexTextField.setText("");
            zhanghuTextField.setText("");
            numTextField.setText("");
            UpdateBtn.setEnabled(false);
        }
        else if (e.getSource() == selBtn) { //��ѯ��ť
            try {
                String strSQL = "select * from HYXX where Hnum=" + HnumTextField.getText().trim(); //���һ�Ա��Ϣsql���
                if (HnumTextField.getText().trim().equals("")) { //�ж��Ƿ������Ա��
                    JOptionPane.showMessageDialog(null, "�������Ա�ţ�");
                }
                else if (!db.getResult(strSQL).first()) { //�ж��Ƿ��д˻�Ա
                    JOptionPane.showMessageDialog(null, "û�д˻�Ա�����������룡");
                }
                else { //����ѯ������Ϣ�ŵ��ı���
                    rs = db.getResult(strSQL);
                    rs.first();
                    HnameTextField.setText(rs.getString(2).trim());
                    sexTextField.setText(rs.getString(3).trim());
                    zhanghuTextField.setText(rs.getString(4).trim());
                    numTextField.setText(rs.getString(6).trim());
                    UpdateBtn.setEnabled(true);
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
        else if (e.getSource() == UpdateBtn) { //���°�ť
            //���ô洢����
            try {
                String strSQL = "select * from HYXX where num='" + numTextField.getText().trim() + "'"; //�жϵ绰��sql���
                if (HnameTextField.getText().trim().equals("")) { //�ж������Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
                } else if (sexTextField.getText().trim().equals("")) { //�ж��Ա��Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "�Ա���Ϊ�գ�");
                } else if (zhanghuTextField.getText().trim().equals("")) { //�ж��˻�����Ϊ��
                    JOptionPane.showMessageDialog(null, "�˻�����Ϊ�գ�");
                } else if (numTextField.getText().trim().equals("")) { //�жϵ绰�����Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "�绰���벻��Ϊ�գ�");
                } else {
                    if (db.getResult(strSQL).first()) { //�жϵ绰�Ƿ�ע���
                        JOptionPane.showMessageDialog(null, "�˵绰�����Ѿ���ע�ᣬ���������룡");
                    } else {
                        strSQL = "{call modh (" +
                                HnumTextField.getText().trim() + ",'" +
                                HnameTextField.getText().trim() + "','" +
                                sexTextField.getText().trim() + "'," +
                                zhanghuTextField.getText().trim() + ",'" +
                                numTextField.getText().trim() + "')}";
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
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

    }
}

