package SD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListSRXX extends JFrame {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JTable table = null;
    DefaultTableModel defaultModel = null;
    public ListSRXX() {
        super("������Ϣ");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        String[] name = {"�����","���޺�","��Ա��","���","����","����"};
        String[][] data = new String[0][0];
        defaultModel = new DefaultTableModel(data, name);
        table = new JTable(defaultModel);
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));
        JScrollPane s = new JScrollPane(table);
        c.add(s);
        try {
            String strSql = "select * from SRXX";
            rs = db.getResult(strSql);
            while (rs.next()) {
                Vector insertRow = new Vector();
                insertRow.addElement(rs.getString(1));
                insertRow.addElement(rs.getString(2));
                insertRow.addElement(rs.getString(3));
                insertRow.addElement(rs.getString(4));
                insertRow.addElement(rs.getString(5));
                insertRow.addElement(rs.getString(6));
                defaultModel.addRow(insertRow);
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
