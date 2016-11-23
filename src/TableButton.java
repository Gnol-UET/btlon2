import org.jdesktop.swingx.HorizontalLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 19/11/2016.
 */
public class TableButton extends JPanel {
    private TableOfJobLists table;
    private User2 user;
    JButton delButton;
    JButton renameButton;

    public JButton getDelButton() {
        return delButton;
    }

    public void setDelButton(JButton delButton) {
        this.delButton = delButton;
    }

    public JButton getRenameButton() {
        return renameButton;
    }

    public void setRenameButton(JButton renameButton) {
        this.renameButton = renameButton;
    }

    public JButton getNameButton() {
        return nameButton;
    }
    public void setName(String s){
        nameButton.setText(s);
    }
    public void setNameButton(JButton nameButton) {
        this.nameButton = nameButton;
    }

    private JButton nameButton;

    public TableOfJobLists getTable() {
        return table;
    }

    public void setTable(TableOfJobLists table) {
        this.table = table;
    }

    public User2 getUser() {
        return user;
    }

    public void setUser(User2 user) {
        this.user = user;
    }

    public TableButton(TableOfJobLists table1, User2 currentUser) {
        user = currentUser;
        table = table1;
        nameButton = new JButton(table1.getNameOfTable());
        nameButton.setBorderPainted(false);
        nameButton.setContentAreaFilled(false);
        nameButton.setOpaque(false);
        ImageIcon delicon = new ImageIcon(new ImageIcon("D:\\png\\trash.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon renameicon = new ImageIcon(new ImageIcon("D:\\png\\edit.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        delButton = new JButton(delicon);
        renameButton = new JButton(renameicon);
        delButton.setBorderPainted(false);
        delButton.setContentAreaFilled(false);
        delButton.setFocusPainted(true);
        delButton.setOpaque(true);
        renameButton.setBorderPainted(false);
        renameButton.setContentAreaFilled(false);
        renameButton.setFocusPainted(true);
        renameButton.setOpaque(true);
        nameButton.setPreferredSize(new Dimension(120, 50));
        setLayout(new BorderLayout());
        add(nameButton, BorderLayout.WEST);
        add(renameButton, BorderLayout.CENTER);
        add(delButton, BorderLayout.EAST);
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border line = BorderFactory.createLineBorder(Color.BLACK, 1, true);
        setBorder(line);
        setMaximumSize(new Dimension(300, 40));
        setVisible(true);

        ActionListener selectTable = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!table.equals(user.getCurrentTable()))
                    user.setCurrentTable(table);


            }
        };
        nameButton.addActionListener(selectTable);

    }
}
