/**
 * Created by Administrator on 20/11/2016.
 */
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import  javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InputJob extends JDialog{
    private Job job;
    public InputJob() {
        job = new Job();
        JTextField newName = new JTextField();
        JTextField newDescription = new JTextField();


        String[] status = {"To-do", "Doing", "Done"};
        String[] repeat = {"Once", "Daily", "Weekly"};

        //Các Combobox gồm tên, status, repeat, priority lấy từ mảng trên
        JComboBox newStatus = new JComboBox(status);
        JComboBox newRepeat = new JComboBox(repeat);
        JComboBox newPriority = new JComboBox(Job.priorityDetail);

        //Ngày bắt đầu, ngày kết thúc, làm bằng JdatePicker
        UtilDateModel model_1 = new UtilDateModel();
        JDatePanelImpl datePanel_1 = new JDatePanelImpl(model_1);
        JDatePickerImpl startTime = new JDatePickerImpl(datePanel_1);

        UtilDateModel model_2 = new UtilDateModel();
        JDatePanelImpl datePanel_2 = new JDatePanelImpl(model_2);
        JDatePickerImpl endTime = new JDatePickerImpl(datePanel_2);

        //Tạo form điền
        final JComponent[] inputs = new JComponent[]{
                new JLabel("New Name: "),
                newName,
                new JLabel("New Description: "),
                newDescription,
                new JLabel("New Repeat: "),
                newRepeat,
                new JLabel("New Status: "),
                newStatus,
                new JLabel("New Priority: "),
                newPriority,
                new JLabel("New Starttime: "),
                startTime,
                new JLabel("New Endtime: "),
                endTime,
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Edit a Job", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //Set tên, repeat, status, priority từ combobox
            job.setName(newName.getText());
            job.setDescription(newDescription.getText());
            job.setRepeat(newRepeat.getSelectedItem().toString());
            job.setStatus(newStatus.getSelectedItem().toString());
            job.setPriority(newPriority.getSelectedItem().toString());
            DateFormat df = new
                    SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy");
            //System.out.println(endTime.toString() + startTime.toString());
            //Set ngày bắt đầu phải dùng try catch
            try {
                    job.setStartTime(df.parse(startTime.getModel().getValue().toString()));
                    job.setEndTime(df.parse(endTime.getModel().getValue().toString()));
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(null,"Please change time location-format to United States");
            }

            //In thông tin job
            JOptionPane.showMessageDialog(null, "Description: " + job.getDescription()
                    + "\nPriority: " + job.getPriority()
                    + "\nStatus: " + job.getStatus()
                    + "\nRepeat: " + job.getRepeat()
                    + "\nStart time:" + job.getStartTime()
                    + "\nEnd time:" + job.getEndTime()
            );

        }
    }
    public InputJob(Job job){

    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
