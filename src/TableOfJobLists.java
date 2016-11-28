

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
    private List<JobList> listsOfJobs;

    public JobList addList(String name){
        JobList newList = new JobList();
        if(!name.equals(""))
            newList.setName(name);
        listsOfJobs.add(newList);
        return  newList;
    }
    public void addJobToCurrentTable(int listIndex, Job job) {
        listsOfJobs.get(listIndex).addJobToCurrentList(job);
    }

    public List<JobList> getListsOfJobs() {
        return listsOfJobs;
    }

    public void setListsOfJobs(List<JobList> listsOfJobs) {
        this.listsOfJobs = listsOfJobs;
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
        listsOfJobs = new ArrayList<>();
        index = numberOfTable;
    }
    public TableOfJobLists(TableOfJobLists table){
        this.listsOfJobs = table.getListsOfJobs();
        this.nameOfTable = table.getNameOfTable();
        this.index = table.getIndex();
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

