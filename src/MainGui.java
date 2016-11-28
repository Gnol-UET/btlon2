import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class MainGui extends JFrame {
    static User user;
    static TrayIcon trayIcon;
    static SystemTray tray;
    static TablesPanel tablesPanel;
    static ListsPanel listsPanel;
    static Toolbar toolbar;
    static JMenu file;
    static JMenuItem exit;
    static JPanel iconPanel;

    public void saveOnClose(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure to save user data?", "Really Closing?",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if(choice != JOptionPane.CANCEL_OPTION){
                    if(choice == JOptionPane.YES_OPTION)
                        user.saveToFile();
                    System.exit(0);
                }

            }
        });
    }

    public void intiPanel(){
        iconPanel = new JPanel();
        FlowLayout fl = new FlowLayout();
        iconPanel.setLayout(fl);
        BufferedImage image2 = null;
        try {
            image2 = ImageIO.read(getClass().getResourceAsStream("/Res/MainImage.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(image2.getScaledInstance(140,90,Image.SCALE_SMOOTH));
        JLabel iconLabel = new JLabel(icon);
        iconPanel.add(iconLabel);
        JTextField textField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        //searchButton.setBorder
        iconPanel.add(textField);
        iconPanel.add(searchButton);
        add(iconPanel,BorderLayout.NORTH);
    }

    public MainGui() {
        loadUser();
        saveOnClose();
        setLayout(new BorderLayout());
        intiPanel();
        JMenuBar menuBar = new JMenuBar();
        file = new JMenu("File");
        JMenu help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(help);
        JMenuItem doneJobs = new JMenuItem("View done jobs");
        doneJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame doneFrame = new JFrame();
                doneFrame.setTitle("Done Job");
                doneFrame.setBackground(Color.WHITE);
                JXTaskPaneContainer list = new JXTaskPaneContainer();
                JScrollPane scrolPane2 = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                doneFrame.getContentPane().add(scrolPane2);
                BufferedImage image3 = null;
                try {
                    image3 = ImageIO.read(getClass().getResourceAsStream("/Res/calendar-23.png"));
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                //ImageIcon icon3 = new ImageIcon(image3.getScaledInstance(150,90,Image.SCALE_SMOOTH));
                doneFrame.setIconImage(image3);
                for (int i = 0; i < user.getDoneList().size(); i++) {
                    JobPane doneJob = new JobPane(user.getDoneList().get(i),user);
                    list.add(doneJob);
                }
                doneFrame.setVisible(true);
                doneFrame.setSize(450,450);
                doneFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            }
        });
        help.add(doneJobs);
        exit = new JMenuItem("Exit");//add Menus
        setJMenuBar(menuBar);

        ActionListener exitButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure to save user data?", "Really Closing?",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (choice != JOptionPane.CANCEL_OPTION) {
                    if (choice == JOptionPane.YES_OPTION)
                        user.saveToFile();
                    System.exit(0);
                }
            }
        };
        exit.addActionListener(exitButton);
        JMenuItem saveButton = new JMenuItem("Save current user");
        ActionListener save = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.saveToFile();
            }
        };
        saveButton.addActionListener(save);
        JMenuItem delete = new JMenuItem("Clear all data");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = new User();
                user.saveToFile();
                loadUser();
                refreshFrame();
            }

        });
        file.add(saveButton);
        file.add(delete);
        file.add(exit);

        toolbar = new Toolbar();
        toolbar.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
        add(toolbar, BorderLayout.SOUTH); // add toolbar
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Res/calendar.png"));
            setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tablesPanel = new TablesPanel();
        tablesPanel.refreshTable(user);
        JScrollPane jScrollBarForTable = new JScrollPane(tablesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollBarForTable.setBorder(BorderFactory.createEmptyBorder());
        add(jScrollBarForTable, BorderLayout.WEST); // add table  with scrollbar
        listsPanel = new ListsPanel(user.getCurrentTable(), user);
        JScrollPane jScrollPaneForJob = new JScrollPane(listsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPaneForJob, BorderLayout.CENTER);
        jScrollPaneForJob.setBorder(BorderFactory.createEmptyBorder());
        setTray();
        setSize(850, 500);
        setTitle("My Reminder");
        setVisible(true);
        addListener();
        //addLevelListener();


        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    }

    public static void main(String[] args) {

        MainGui newMain = new MainGui();
        user.getCurrentList();


    }

    public void addListener() {
        ActionListener addTable = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) JOptionPane.showInputDialog("Enter Name for New Table");
                if (s != null)
                    if (!s.equals(""))
                        user.addNewTable(s);
                refreshFrame();

            }
        };
        ActionListener addList = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) JOptionPane.showInputDialog("Enter Name for New List");
                if (s != null)
                    if (!s.equals(""))
                        user.getCurrentTable().addList(s);
                refreshFrame();
            }
        };
        ActionListener addJob = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final boolean[] close = {false};
                    InputJob inputJob = new InputJob();

                    if (!inputJob.getJob().getDescription().equalsIgnoreCase("No description") && !inputJob.getJob().getDescription().equalsIgnoreCase("") && close[0] == false) {
                        user.getCurrentList().addJobToCurrentList(inputJob.getJob());
                    }
                    refreshFrame();
                } catch (NullPointerException ee) {
                    JOptionPane.showMessageDialog(null, "There is no list selected");
                }
            }
        };
        toolbar.getAddJobButton().addActionListener(addJob);
        toolbar.getAddListButton().addActionListener(addList);
        toolbar.getAddTableButton().addActionListener(addTable);
    }

    public void loadUser() {
        File inFile = new File("new.admin.data");
        if (!inFile.exists()) {
            user = new User();
            user.saveToFile();

        } else {
            try {

                user = User.readFromFile();
            } catch (Exception ee) {
                System.out.println(ee.getLocalizedMessage());
                JOptionPane.showMessageDialog(this, ee.getMessage());
            }
        }
    }

    public void setTray() {
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        if (SystemTray.isSupported()) {

            tray = SystemTray.getSystemTray();
            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Res/calendar.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    setExtendedState(JFrame.NORMAL);

                }
            };
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Restore");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int choice = JOptionPane.showConfirmDialog(null,
                            "Are you sure to save user data?", "Really Closing?",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(choice != JOptionPane.CANCEL_OPTION) {
                        if (choice == JOptionPane.YES_OPTION)
                            user.saveToFile();
                        System.exit(0);
                    }
                }
            });
            popup.add(defaultItem);
            trayIcon = new TrayIcon(image, "MyReminder", popup);
            trayIcon.setImageAutoSize(true);
        } else {

        }
        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == ICONIFIED) {
                    try {
                        tray.add(trayIcon);
                        setVisible(false);

                    } catch (AWTException ex) {

                    }
                }
                if (e.getNewState() == 7) {
                    try {
                        tray.add(trayIcon);
                        setVisible(false);

                    } catch (AWTException ex) {

                    }
                }
                if (e.getNewState() == MAXIMIZED_BOTH) {
                    tray.remove(trayIcon);
                    setVisible(true);

                }
                if (e.getNewState() == NORMAL) {
                    tray.remove(trayIcon);
                    setVisible(true);

                }
            }
        });
    }

    public static void refreshFrame() {
        tablesPanel.refreshTable(user);
        listsPanel.refreshTheList(user.getCurrentTable(), user);

    }
}
