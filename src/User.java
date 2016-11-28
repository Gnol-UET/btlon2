import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vu Ngoc Quy - 10521480
 *         Lab 0
 */
public class User implements Serializable {
    public List<String> userLogs = new ArrayList<>();
    private TableOfJobLists currentTable;
    private JobList currentList;
    private List<TableOfJobLists> tablesOfLists;
    private List<Job> doneList;

    public List<Job> getDoneList() {
        return doneList;
    }

    public void setDoneList(List<Job> doneList) {
        this.doneList = doneList;
    }

    public User() {
        tablesOfLists = new ArrayList<>();
        userLogs.add("User logged in");
        TableOfJobLists newtable = addNewTable("DefaultTable");
        setCurrentTable(newtable);
        doneList = new ArrayList<>();

        setCurrentList(newtable.addList("Default List"));


    }
    public void copyATable(TableOfJobLists table){
        this.getTablesOfLists().add(new TableOfJobLists(table));
    }
    public void moveAJob(JobList sourceList, JobList desList,Job job){
        desList.addJobToCurrentList(job);
        sourceList.getList().remove(job);
    }
    public List<TableOfJobLists> getTablesOfLists() {
        return tablesOfLists;
    }

    public void setTablesOfLists(List<TableOfJobLists> tablesOfLists) {
        this.tablesOfLists = tablesOfLists;
    }

    public TableOfJobLists getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(TableOfJobLists currentTable) {
        this.currentTable = currentTable;
    }

    public JobList getCurrentList() {
        return currentList;
    }

    public void setCurrentList(JobList currentList) {
        this.currentList = currentList;
    }

    public void completeJob(Job job) {
        job.setStatus("Done");
        doneList.add(job);
        getCurrentList().getList().remove(job);
    }

    public void addNewJob(Job job) {
        currentList.addJobToCurrentList(job);
    }

    public void editTableName(int tableIndex, String newName) {
        this.tablesOfLists.get(tableIndex).setNameOfTable(newName);
    }

    public void removeATable(TableOfJobLists tableToRemove) {
        tablesOfLists.remove(tableToRemove);
    }

    public TableOfJobLists addNewTable(String name) {
        TableOfJobLists newTable = new TableOfJobLists();
        tablesOfLists.add(newTable);
        if(!name.equals(""))
            tablesOfLists.get(tablesOfLists.size()-1).setNameOfTable(name);
        return newTable;
    }


    public void saveToFile() {
        try {

            File inFile = new File("new.admin.data");
            if (!inFile.exists()) {

                inFile.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(inFile);
            ObjectOutputStream outStreamObj = new ObjectOutputStream(outStream);
            outStreamObj.writeObject(this);
            outStreamObj.close();
        } catch (Exception ee) {
            System.out.println("System is not Window " + ee.getMessage());

        }

    }

    public static User readFromFile() throws IOException, ClassNotFoundException {
        File inFile = new File("new.admin.data");
        //if (!inFile.exists()) {
          //  this.saveToFile();
        //}
        FileInputStream inStream = new FileInputStream(inFile);
        ObjectInputStream ObjInPutStream = new ObjectInputStream(inStream);
        User userAdmin = (User) ObjInPutStream.readObject();
        ObjInPutStream.close();
        return userAdmin;
    }
}
