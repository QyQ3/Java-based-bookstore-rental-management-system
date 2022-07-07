package SD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    JPanel panel1;
    Container c;
    JMenuBar menuB;
    JMenu HYXXMenu, SJXXMenu, ZLXXMenu, XXMenu;
    JMenuItem addHYXXMenu, modifyHYXXMenu, deleteHYXXMenu,
            addSJXXMenu, modifySJXXMenu, deleteSJXXMenu,
            borrowBookMenu, returnBookMenu, modifyZLXXMenu,
            HYXXListMenu, SJXXListMenu, ZLXXListMenu, SRXXListMenu;
    public MainFrame() {
        super("������޹���ϵͳ");
        menuB = new JMenuBar();
        //��Ա��Ϣ����˵�
        HYXXMenu = new JMenu("��Ա��Ϣ����");
        addHYXXMenu = new JMenuItem("��ӻ�Ա");
        modifyHYXXMenu= new JMenuItem("�޸Ļ�Ա��Ϣ");
        deleteHYXXMenu = new JMenuItem("ɾ����Ա");
        HYXXMenu.add(addHYXXMenu);
        HYXXMenu.add(modifyHYXXMenu);
        HYXXMenu.add(deleteHYXXMenu);
        addHYXXMenu.addActionListener(this);
        modifyHYXXMenu.addActionListener(this);
        deleteHYXXMenu.addActionListener(this);
        menuB.add(HYXXMenu);
        //�鼮��Ϣ����˵�
        SJXXMenu = new JMenu("�鼮��Ϣ����");
        addSJXXMenu = new JMenuItem("����鼮");
        modifySJXXMenu = new JMenuItem("�޸��鼮��Ϣ");
        deleteSJXXMenu = new JMenuItem("ɾ���鼮��Ϣ");
        SJXXMenu.add(addSJXXMenu);
        SJXXMenu.add(modifySJXXMenu);
        SJXXMenu.add(deleteSJXXMenu);
        addSJXXMenu.addActionListener(this);
        modifySJXXMenu.addActionListener(this);
        deleteSJXXMenu.addActionListener(this);
        menuB.add(SJXXMenu);
        //������Ϣ����˵�
        ZLXXMenu = new JMenu("������Ϣ����");
        borrowBookMenu = new JMenuItem("����鼮");
        returnBookMenu = new JMenuItem("�����鼮");
        modifyZLXXMenu = new JMenuItem("�޸�������Ϣ");
        ZLXXMenu.add(borrowBookMenu);
        ZLXXMenu.add(returnBookMenu);
        ZLXXMenu.add(modifyZLXXMenu);
        borrowBookMenu.addActionListener(this);
        returnBookMenu.addActionListener(this);
        modifyZLXXMenu.addActionListener(this);
        menuB.add(ZLXXMenu);
        //��Ϣ��ѯ����˵�
        XXMenu = new JMenu("��Ϣ��ѯ����");
        HYXXListMenu = new JMenuItem("��Ա��Ϣ��ѯ");
        SJXXListMenu = new JMenuItem("�鼮��Ϣ��ѯ");
        ZLXXListMenu = new JMenuItem("������Ϣ��ѯ");
        SRXXListMenu = new JMenuItem("������Ϣ��ѯ");
        XXMenu.add(HYXXListMenu);
        XXMenu.add(SJXXListMenu);
        XXMenu.add(ZLXXListMenu);
        XXMenu.add(SRXXListMenu);
        HYXXListMenu.addActionListener(this);
        SJXXListMenu.addActionListener(this);
        ZLXXListMenu.addActionListener(this);
        SRXXListMenu.addActionListener(this);
        menuB.add(XXMenu);

        setJMenuBar(menuB);
        c = getContentPane();
        c.setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        c.add(panel1, BorderLayout.CENTER);
    }

    //����ÿ���˵��������ֵĴ��ںʹ�����ʾ��λ��
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "��ӻ�Ա") {
            AddHYXX UserAddFrame = new AddHYXX();
            Dimension FrameSize = UserAddFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            UserAddFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 + loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 + loc.y);
            UserAddFrame.pack();
            UserAddFrame.show();
        }
        else if (e.getActionCommand() == "�޸Ļ�Ա��Ϣ") {
            ModifyHYXX UserModifyFrame = new ModifyHYXX();
            Dimension FrameSize = UserModifyFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            UserModifyFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            UserModifyFrame.pack();
            UserModifyFrame.show();
        }
        else if (e.getActionCommand() == "ɾ����Ա") {
            DeleteHYXX UserDeleteFrame = new DeleteHYXX();
            Dimension FrameSize = UserDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            UserDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            UserDeleteFrame.pack();
            UserDeleteFrame.show();
        }
        else if (e.getActionCommand() == "����鼮") {
            AddSJXX BookAddFrame = new AddSJXX();
            Dimension FrameSize = BookAddFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookAddFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookAddFrame.pack();
            BookAddFrame.show();
        }
        else if (e.getActionCommand() == "�޸��鼮��Ϣ") {
            ModfiySJXX BookDeleteFrame = new ModfiySJXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "ɾ���鼮��Ϣ") {
            DeleteSJXX BookDeleteFrame = new DeleteSJXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "����鼮") {
            BorrowZLXX BookDeleteFrame = new BorrowZLXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "�����鼮") {
            ReturnZLXX BookDeleteFrame = new ReturnZLXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "�޸�������Ϣ") {
            ModfiyZLXX BookDeleteFrame = new ModfiyZLXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "��Ա��Ϣ��ѯ") {
            ListHYXX BookDeleteFrame = new ListHYXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "�鼮��Ϣ��ѯ") {
            ListSJXX BookDeleteFrame = new ListSJXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "������Ϣ��ѯ") {
            ListZLXX BookDeleteFrame = new ListZLXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
        else if (e.getActionCommand() == "������Ϣ��ѯ") {
            ListSRXX BookDeleteFrame = new ListSRXX();
            Dimension FrameSize = BookDeleteFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                            loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 +
                            loc.y);
            BookDeleteFrame.pack();
            BookDeleteFrame.show();
        }
    }
}

