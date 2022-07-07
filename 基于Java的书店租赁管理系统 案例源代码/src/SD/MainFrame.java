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
        super("书店租赁管理系统");
        menuB = new JMenuBar();
        //会员信息管理菜单
        HYXXMenu = new JMenu("会员信息管理");
        addHYXXMenu = new JMenuItem("添加会员");
        modifyHYXXMenu= new JMenuItem("修改会员信息");
        deleteHYXXMenu = new JMenuItem("删除会员");
        HYXXMenu.add(addHYXXMenu);
        HYXXMenu.add(modifyHYXXMenu);
        HYXXMenu.add(deleteHYXXMenu);
        addHYXXMenu.addActionListener(this);
        modifyHYXXMenu.addActionListener(this);
        deleteHYXXMenu.addActionListener(this);
        menuB.add(HYXXMenu);
        //书籍信息管理菜单
        SJXXMenu = new JMenu("书籍信息管理");
        addSJXXMenu = new JMenuItem("添加书籍");
        modifySJXXMenu = new JMenuItem("修改书籍信息");
        deleteSJXXMenu = new JMenuItem("删除书籍信息");
        SJXXMenu.add(addSJXXMenu);
        SJXXMenu.add(modifySJXXMenu);
        SJXXMenu.add(deleteSJXXMenu);
        addSJXXMenu.addActionListener(this);
        modifySJXXMenu.addActionListener(this);
        deleteSJXXMenu.addActionListener(this);
        menuB.add(SJXXMenu);
        //租赁信息管理菜单
        ZLXXMenu = new JMenu("租赁信息管理");
        borrowBookMenu = new JMenuItem("借出书籍");
        returnBookMenu = new JMenuItem("还入书籍");
        modifyZLXXMenu = new JMenuItem("修改租赁信息");
        ZLXXMenu.add(borrowBookMenu);
        ZLXXMenu.add(returnBookMenu);
        ZLXXMenu.add(modifyZLXXMenu);
        borrowBookMenu.addActionListener(this);
        returnBookMenu.addActionListener(this);
        modifyZLXXMenu.addActionListener(this);
        menuB.add(ZLXXMenu);
        //信息查询管理菜单
        XXMenu = new JMenu("信息查询管理");
        HYXXListMenu = new JMenuItem("会员信息查询");
        SJXXListMenu = new JMenuItem("书籍信息查询");
        ZLXXListMenu = new JMenuItem("借阅信息查询");
        SRXXListMenu = new JMenuItem("收入信息查询");
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

    //设置每个菜单点击后出现的窗口和窗口显示的位置
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "添加会员") {
            AddHYXX UserAddFrame = new AddHYXX();
            Dimension FrameSize = UserAddFrame.getPreferredSize();
            Dimension MainFrameSize = getSize();
            Point loc = getLocation();
            UserAddFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 + loc.x,
                    (MainFrameSize.height - FrameSize.height) / 2 + loc.y);
            UserAddFrame.pack();
            UserAddFrame.show();
        }
        else if (e.getActionCommand() == "修改会员信息") {
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
        else if (e.getActionCommand() == "删除会员") {
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
        else if (e.getActionCommand() == "添加书籍") {
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
        else if (e.getActionCommand() == "修改书籍信息") {
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
        else if (e.getActionCommand() == "删除书籍信息") {
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
        else if (e.getActionCommand() == "借出书籍") {
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
        else if (e.getActionCommand() == "还入书籍") {
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
        else if (e.getActionCommand() == "修改租赁信息") {
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
        else if (e.getActionCommand() == "会员信息查询") {
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
        else if (e.getActionCommand() == "书籍信息查询") {
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
        else if (e.getActionCommand() == "借阅信息查询") {
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
        else if (e.getActionCommand() == "收入信息查询") {
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

