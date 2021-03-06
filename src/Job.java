import java.io.Serializable;
import java.util.Date;

/**
 * A job for user to do : It has description, repeat type, start time and end time ( If it's limited job - type)
 * ,priority , status and exact time of the day(Daily or Weekly giaoDien.maNguonChinh.Job)
 */
public class Job implements Serializable {
    public String[] priorityDetail = {"low", "average", "high", "highest"};

    private String description;
    private String name;
    private String repeat;//daily,weekly ,once
    private Date startTime; // start time
    private Date endTime; // end time
    private int priority; // low ,average,high,highest - 0 ->3
    private String status; // to-do , doing ,done
    private Date hour;
    /**
     * default constructor
     */
    public Job() {
        startTime = null;
        endTime = null;
        description = "No description";
        repeat = "once";
        priority = 0;
        status = "To do";
        hour = null;
        name = "default";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getPriorityDetail(int i) {
        return priorityDetail[i];
    }
}
