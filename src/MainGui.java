import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 *
 */
public class MainGui extends JFrame {
    static User2 user;
    TrayIcon trayIcon;
    SystemTray tray;
    Table2 tablePanel;
    ListPanel listPanel;
    Toolbar toolbar;
    JMenu file;
    JMenuItem exit;

    public MainGui() {
        loadUser();
        setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        file = new JMenu("File");
        JMenu help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(help);
        exit = new JMenuItem("Exit");//add Menus
        setJMenuBar(menuBar);

        ActionListener exitButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
                user = new User2();
                user.saveToFile();
                loadUser();
                refreshFrame();
            }
        });
        file.add(saveButton);
        file.add(delete);
        file.add(exit);
        toolbar = new Toolbar();
        //toolbar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(toolbar, BorderLayout.NORTH); // add toolbar
        tablePanel = new Table2();
        tablePanel.refreshTable(user);
        JScrollPane jScrollBarForTable = new JScrollPane(tablePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollBarForTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(jScrollBarForTable, BorderLayout.WEST); // add table  with scrollbar
        listPanel = new ListPanel(user.getCurrentTable(), user);
        JScrollPane jScrollPaneForJob = new JScrollPane(listPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPaneForJob, BorderLayout.CENTER);
        jScrollPaneForJob.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setTray();
        setSize(700, 450);
        setTitle("My Reminder");
        setVisible(true);
        addListener();
        addLevelListener();
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\png\\calendar.png"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        MainGui newMain = new MainGui();
        user.getCurrentList();
        newMain.refreshFrame();


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
                addLevelListener();
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
                    inputJob.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            close[0] = true;
                        }
                    });
                    if (!inputJob.getJob().getDescription().equalsIgnoreCase("No description") && !inputJob.getJob().getDescription().equalsIgnoreCase("") && close[0] == false) {
                        user.getCurrentList().addJobToCurrentList(inputJob.getJob());
                    }
                    refreshFrame();
                } catch (NullPointerException ee) {
                    JOptionPane.showMessageDialog(null, "There is no list selected");
                }
            }
        };

        MouseListener mouse = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        toolbar.getAddJobButton().addActionListener(addJob);
        toolbar.getAddListButton().addActionListener(addList);
        toolbar.getAddTableButton().addActionListener(addTable);
    }

    public void addLevelListener() {

        for (int i = 0; i < tablePanel.getComponentCount(); i = i + 2) {
            TableButton tableButton = (TableButton) tablePanel.getComponent(i);
            ActionListener selectTable = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tableButton.getTable().equals(user.getCurrentTable()))
                        user.setCurrentTable(tableButton.getTable());
                    if (user.getCurrentTable().getToDoList().size() == 0)
                        user.setCurrentList(null);
                    else
                        user.getCurrentTable().getToDoList().get(0);
                    refreshFrame();

                }
            };
            tableButton.getRenameButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = (String) JOptionPane.showInputDialog("Enter Name for New Table", user.getCurrentTable().getNameOfTable());
                    if (s != null)
                        if (!s.equals(""))
                            user.getCurrentTable().setNameOfTable(s);

                    refreshFrame();
                }
            });
            tableButton.getDelButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // user.removeATable(user.getCurrentTable());
                    refreshFrame();
                }
            });
            tableButton.getNameButton().addActionListener(selectTable);
        }
    }

    public void loadUser() {
        File inFile = new File("new.admin.data");
        if (!inFile.exists()) {
            user = new User2();
            user.saveToFile();

        } else {
            try {

                user = User2.readFromFile();
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

            Image image = Toolkit.getDefaultToolkit().getImage("D:\\png\\calendar.png");
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
                    System.exit(0);
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

    public void refreshFrame() {
        tablePanel.refreshTable(user);
        listPanel.refreshTheList(user.getCurrentTable(), user);

    }
}
