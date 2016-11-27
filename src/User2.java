import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vu Ngoc Quy - 10521480
 *         Lab 0
 */
public class User2 implements Serializable {
    public static List<String> userLogs = new ArrayList<>();
    private TableOfJobLists currentTable;
    private JobList currentList;
    private Job currentJob;
    private List<TableOfJobLists> userList;
    private List<Job> doneList;

    public List<Job> getDoneList() {
        return doneList;
    }

    public void setDoneList(List<Job> doneList) {
        this.doneList = doneList;
    }

    public User2() {
        userList = new ArrayList<>();
        userLogs.add("User logged in");
        TableOfJobLists newtable = addNewTable("DefaultTable");
        setCurrentTable(newtable);
        doneList = new ArrayList<>();

        setCurrentList(newtable.addList("Default List"));


    }

    public List<TableOfJobLists> getUserList() {
        return userList;
    }

    public void setUserList(List<TableOfJobLists> userList) {
        this.userList = userList;
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

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
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
        this.userList.get(tableIndex).setNameOfTable(newName);
    }

    public void removeATable(TableOfJobLists tableToRemove) {
        userList.remove(tableToRemove);
    }

    public TableOfJobLists addNewTable(String name) {
        TableOfJobLists newTable = new TableOfJobLists();
        userList.add(newTable);
        if(!name.equals(""))
            userList.get(userList.size()-1).setNameOfTable(name);
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

    public static User2 readFromFile() throws IOException, ClassNotFoundException {
        File inFile = new File("new.admin.data");
        //if (!inFile.exists()) {
          //  this.saveToFile();
        //}
        FileInputStream inStream = new FileInputStream(inFile);
        ObjectInputStream ObjInPutStream = new ObjectInputStream(inStream);
        User2 userAdmin = (User2) ObjInPutStream.readObject();
        ObjInPutStream.close();
        return userAdmin;
    }
}
