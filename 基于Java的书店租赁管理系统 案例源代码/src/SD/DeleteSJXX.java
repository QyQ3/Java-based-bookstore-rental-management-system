package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteSJXX extends JFrame implements ActionListener {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JLabel TipLabel = new JLabel("������Ҫɾ������ţ�", JLabel.CENTER);
    JTextField bookDeleteTextField = new JTextField(40);
    JButton yesBtn, exitBtn;
    JPanel panel1 = new JPanel();
    public DeleteSJXX() {
        super("ɾ���鼮��Ϣ");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(TipLabel, BorderLayout.NORTH);
        c.add(bookDeleteTextField, BorderLayout.CENTER);
        yesBtn = new JButton("ȷ��");
        exitBtn = new JButton("�˳�");
        yesBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        panel1.add(yesBtn);
        panel1.add(exitBtn);
        c.add(panel1, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) {
            db.closeConnection();
            this.dispose();
        }
        else if (e.getSource() == yesBtn) {
            try {
                String strSQL = "select * from SJXX where Bnum=" + bookDeleteTextField.getText().trim();
                if (bookDeleteTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "��������ţ�");
                } else if (!db.getResult(strSQL).first()) {
                    JOptionPane.showMessageDialog(null, "�����û����Ҫɾ�����飡");
                } else {
                    strSQL = "select * from ZLXX where Bnum=" + bookDeleteTextField.getText().trim() + "and Hdate is null";
                    if (db.getResult(strSQL).first()) {
                        JOptionPane.showMessageDialog(null, "���黹�л�Աû�л���\n���ڻ����ܴ������ɾ����");
                    } else {
                        strSQL = "{call delb (" + bookDeleteTextField.getText().trim() + ")}";
                        if (db.updateSql(strSQL) > 0) {
                            JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
                            db.closeConnection();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
                            db.closeConnection();
                            this.dispose();
                        }
                    }
                }
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

