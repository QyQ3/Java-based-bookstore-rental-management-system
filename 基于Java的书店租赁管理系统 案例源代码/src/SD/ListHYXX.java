package SD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListHYXX extends JFrame {
    DataBaseManager db = new DataBaseManager();
    ResultSet rs;
    Container c;
    JTable table = null;
    DefaultTableModel defaultModel = null;
    public ListHYXX() {
        super("会员信息");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        String[] name = {"会员号","姓名","性别","账户余额","登记日期","电话号码"};
        String[][] data = new String[0][0];
        defaultModel = new DefaultTableModel(data, name);
        table = new JTable(defaultModel);
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));
        JScrollPane s = new JScrollPane(table);
        c.add(s);
        try {
            String strSql = "select * from HYXX";
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
