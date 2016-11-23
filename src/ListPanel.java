import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 19/11/2016.
 */
public class ListPanel extends JTabbedPane {
        static int count = 0;
        int sel;
        TableOfJobLists tabbedTable;
        User2 user;
    public ListPanel(){
        JXTaskPaneContainer task1 = new JXTaskPaneContainer();

        add("DefaultList1",task1);

    }
    public ListPanel(TableOfJobLists table,User2 currentuser){
        user = currentuser;
        for ( JobList jl: table.getToDoList())
              {
                  tabbedTable = table;
                  JXTaskPaneContainer task1 = new JXTaskPaneContainer();
                  add(jl.getName(),task1);
                  refreshTheJobs(task1,jl);
        }

    }
    public void refreshTheList(TableOfJobLists userCurrentTable,User2 currentuser) {
        removeAll();
        user = currentuser;
        tabbedTable = userCurrentTable;
        for (int i = 0; i < tabbedTable.getToDoList().size(); i++) {
            JXTaskPaneContainer newList = new JXTaskPaneContainer();
            //newList.setBackground(new Color(245, 255,68));
            add(tabbedTable.getToDoList().get(i).getName(),newList);
            refreshTheJobs(newList,tabbedTable.getToDoList().get(i));
        }
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {


                sel = getSelectedIndex();
                if(sel!= -1)
                    try {
                        user.setCurrentList(userCurrentTable.getToDoList().get(sel));
                    }
                    catch(Exception ee){
                        //JOptionPane.showMessageDialog(null,"No list error");
                }



            }
        });
        setSelectedIndex(sel);
        revalidate();
        repaint();

    }
    public void refreshTheJobs(JXTaskPaneContainer container,JobList list){
            //sort
        container.removeAll();
        for (int i = 0; i < list.getList().size(); i++) {
             container.add(new JobPanel2(list.getList().get(i)));
        }
    }

    public void addList(JobList jobList){
        count ++;
        JXTaskPaneContainer nextList = new JXTaskPaneContainer();
        if(jobList == null){
            add("DefaultList" + count,nextList);
        }
        else
            add(jobList.getName(),nextList);
    }
    public void addJob(Job job,JobList listJob) {
        JobPanel2 newJob = new JobPanel2(job);
        ((JXTaskPaneContainer) getComponent(indexOfTab(listJob.getName()))).add(newJob);
        revalidate();
        repaint();
    }
    public void removeTab(JobList listJob){
        remove(indexOfTab(listJob.getName()));
    }
    public void renameAList(JobList listJob,String newName){
        setToolTipTextAt(indexOfTab(listJob.getName()),newName);
    }

}
