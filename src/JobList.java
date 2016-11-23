import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * List of job in a table
 */
public class JobList implements Serializable{
    static int numberOfList = 0;
    private List<Job> list ;
    private String name;
    private int index;
    public JobList(){
        numberOfList ++;
        name = "DefaultList" + numberOfList;
        list = new ArrayList<>();
        index = numberOfList;
    }
    public void addJobToCurrentList(Job job){
        list.add(job);
    }

    public List<Job> getList() {
        return list;
    }

    public void setList(List<Job> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
