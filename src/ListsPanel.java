import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Administrator on 19/11/2016.
 */
public class ListsPanel extends JTabbedPane {
        static int count = 0;
        int sel;
        TableOfJobLists tabbedTable;
        User user;
    public ListsPanel(){
        JXTaskPaneContainer task1 = new JXTaskPaneContainer();

        add("DefaultList1",task1);

    }
    public ListsPanel(TableOfJobLists table, User currentuser){
        user = currentuser;
        for ( JobList jl: table.getListsOfJobs())
              {
                  tabbedTable = table;
                  JXTaskPaneContainer task1 = new JXTaskPaneContainer();
                  add(jl.getName(),task1);
                  refreshTheJobs(task1,jl);
        }

    }
    public void refreshTheList(TableOfJobLists userCurrentTable, User currentuser) {
        removeAll();
        user = currentuser;
        tabbedTable = userCurrentTable;
        for (int i = 0; i < tabbedTable.getListsOfJobs().size(); i++) {
            JXTaskPaneContainer newList = new JXTaskPaneContainer();
            newList.setBackground(Color.white);
            //newList.setBackground(new Color(245, 255,68));
            add(tabbedTable.getListsOfJobs().get(i).getName(),newList);

            refreshTheJobs(newList,tabbedTable.getListsOfJobs().get(i));
        }
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {


                sel = getSelectedIndex();
                if(sel!= -1)
                    try {
                        user.setCurrentList(user.getCurrentTable().getListsOfJobs().get(sel));
                    }
                    catch(Exception ee){
                       // JOptionPane.showMessageDialog(null,"No list error");
                }



            }
        });
        //setSelectedIndex(sel);
        revalidate();
        repaint();

    }
    public void refreshTheJobs(JXTaskPaneContainer container,JobList list){
            //sort
        container.removeAll();
        for (int i = 0; i < list.getList().size(); i++) {
            container.add(new JobPane(list.getList().get(i),user));
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
        JobPane newJob = new JobPane(job,user);
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
