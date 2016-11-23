
import javax.swing.*;
import java.awt.*;



/**
 * Created by Administrator on 20/11/2016.
 */
public class Toolbar extends JToolBar {
    private JButton addTableButton;
    private JButton addListButton;
    private JButton addJobButton;
    private JButton viewDoneJobs;
    private JButton viewUserLogs;

    public JButton getAddTableButton() {
        return addTableButton;
    }

    public void setAddTableButton(JButton addTableButton) {
        this.addTableButton = addTableButton;
    }

    public JButton getAddListButton() {
        return addListButton;
    }

    public void setAddListButton(JButton addListButton) {
        this.addListButton = addListButton;
    }

    public JButton getAddJobButton() {
        return addJobButton;
    }

    public void setAddJobButton(JButton addJobButton) {
        this.addJobButton = addJobButton;
    }

    public JButton getViewDoneJobs() {
        return viewDoneJobs;
    }

    public void setViewDoneJobs(JButton viewDoneJobs) {
        this.viewDoneJobs = viewDoneJobs;
    }

    public JButton getViewUserLogs() {
        return viewUserLogs;
    }

    public void setViewUserLogs(JButton viewUserLogs) {
        this.viewUserLogs = viewUserLogs;
    }

    public Toolbar(){
        addTableButton = new JButton("Add A Table");

        addListButton = new JButton("Add a List");
        add(Box.createRigidArea(new Dimension(0,0)));
        addJobButton = new JButton("Add new Job");
        viewDoneJobs = new JButton("View done jobs");
        viewUserLogs = new JButton("View user logs");
        add(addTableButton);
        add(Box.createRigidArea(new Dimension(50,0)));
        add(viewDoneJobs);
        add(Box.createRigidArea(new Dimension(50,0)));
        add(viewUserLogs);
        add(Box.createRigidArea(new Dimension(50,0)));
        add(addListButton);
        add(Box.createRigidArea(new Dimension(50,0)));
        add(addJobButton);

        setVisible(true);
        setMaximumSize(new Dimension(450,60));
    }
}
