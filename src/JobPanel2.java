/**
 * Created by Administrator on 19/11/2016.
 */
import org.jdesktop.swingx.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JobPanel2 extends JXTaskPane{
    public JobPanel2(){
        super("Default Job");
    }
    public JobPanel2(Job job){

        super(job.getName() + "    :   " + job.getStatus());
        JLabel label1 = new JLabel("Start time : " + job.getStartTime());
        JLabel label2 = new JLabel("End time : " + job.getEndTime());
        JLabel label3 = new JLabel("Repeat :" + job.getRepeat().toString());
        JLabel label4 = new JLabel("Description : " +job.getDescription().toString());
        JLabel label5 = new JLabel("Priority : " +job.getPriorityDetail(job.getPriority()).toString());
        JLabel label6 = new JLabel("Time of day : " + job.getHour());
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        ActionListener editButtonL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ready to chance info");
            }
        };
        ActionListener complete = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                job.setStatus("Done");
                repaint();
                setTitle(job.getName() + "    :   " + job.getStatus() );
                label1.setText("Start time : " + " --:--");

            }
        };
        JButton editButton = new JButton("Edit");
        JButton completeButton = new JButton("Complete");
        editButton.addActionListener(editButtonL);
        completeButton.addActionListener(complete);
        setAutoscrolls(true);
        setCollapsed(true);
        add(editButton);
        add(completeButton);
        setVisible(true);
    }
}

