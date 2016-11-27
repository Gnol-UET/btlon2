import org.jdesktop.swingx.HorizontalLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
        ImageIcon delIcon = null;
        ImageIcon renameIcon = null;
        try {
            BufferedImage delimage = ImageIO.read(getClass().getResourceAsStream("/Res/trash.png"));
            BufferedImage renameimage = ImageIO.read(getClass().getResourceAsStream("/Res/edit.png"));
            delIcon = new ImageIcon(delimage.getScaledInstance(20,20,Image.SCALE_SMOOTH));
            renameIcon = new ImageIcon(renameimage.getScaledInstance(20,20,Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        delButton = new JButton(delIcon);
        renameButton = new JButton(renameIcon);
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
        //Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border line = BorderFactory.createLineBorder(Color.BLACK, 1, true);
        setBorder(line);
        setMaximumSize(new Dimension(300, 40));
        setVisible(true);

        ActionListener selectTable = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setCurrentTable(table);
                if(user.getCurrentTable().getToDoList().size() == 0)
                {
                    user.setCurrentList(null);
                }
                else {
                    user.setCurrentList(user.getCurrentTable().getToDoList().get(0));
                }
                MainGui.refreshFrame();

            }
        };
        nameButton.addActionListener(selectTable);
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) JOptionPane.showInputDialog("Enter Name for New Table", user.getCurrentTable().getNameOfTable());
                if (s != null)
                    if (!s.equals(""))
                        user.getCurrentTable().setNameOfTable(s);

                MainGui.refreshFrame();
            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
