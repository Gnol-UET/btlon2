import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 19/11/2016.
 */
public class Table2 extends JPanel {
    int lastIndex = 0;
    public Table2(){
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        setVisible(true);
    }
    public void refreshTable(User2 user){
        if(lastIndex <= user.getUserList().size())
            for (int i = lastIndex; i < user.getUserList().size(); i++) {
                TableButton newButton = new TableButton(user.getUserList().get(i),user);
                add(newButton);
                if(user.getUserList().get(i).equals(user.getCurrentTable()))
                    newButton.setBorder(BorderFactory.createLineBorder(Color.blue));

                add(Box.createRigidArea(new Dimension(0,7)));
            }

        lastIndex = user.getUserList().size();
        revalidate();
        repaint();
        for (int i = 0; i < 2*user.getUserList().size() ; i = i+2) {
            if(user.getUserList().get(i/2).equals(user.getCurrentTable())) {
                ((TableButton) getComponent(i)).setBorder(BorderFactory.createLineBorder(Color.blue));
                ((TableButton) getComponent(i)).setName(user.getCurrentTable().getNameOfTable());
            }
            else
                ((TableButton)getComponent(i)).setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }
}
