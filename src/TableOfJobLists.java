

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vu Ngoc Quy - 10521480
 *         Lab 0
 */
public class TableOfJobLists implements Serializable {
    static int numberOfTable = 0;
    private int index;
    private String nameOfTable;
    private List<JobList> toDoList;
    public JobList addList(String name){
        JobList newList = new JobList();
        if(!name.equals(""))
            newList.setName(name);
        toDoList.add(newList);
        return  newList;
    }
    public void addJobToCurrentTable(int listIndex, Job job) {
        toDoList.get(listIndex).addJobToCurrentList(job);
    }

    public List<JobList> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<JobList> toDoList) {
        this.toDoList = toDoList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TableOfJobLists() {
        numberOfTable++;
        nameOfTable = " DefaultTable" + numberOfTable;
        toDoList = new ArrayList<>();
        index = numberOfTable;
    }

    public String getNameOfTable() {
        return nameOfTable;
    }

    public void setNameOfTable(String nameOfTable) {
        this.nameOfTable = nameOfTable;
    }

    public boolean switchState(Job job, JobList jobList) {
        if (job.getStatus().equals("Todo")) {
            job.setStatus("Done");
            return true;
        } else
            return false;
    }
}

