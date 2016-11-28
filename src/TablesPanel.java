import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 19/11/2016.
 */
public class TablesPanel extends JPanel {
    int lastIndex = 0;
    public TablesPanel(){
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        setVisible(true);
    }
    public void refreshTable(User user){
        if(lastIndex <= user.getTablesOfLists().size())
            for (int i = lastIndex; i < user.getTablesOfLists().size(); i++) {
                TableButton newButton = new TableButton(user.getTablesOfLists().get(i),user);
                add(newButton);
                if(user.getTablesOfLists().get(i).equals(user.getCurrentTable()))
                    newButton.setBorder(BorderFactory.createLineBorder(Color.blue));

                add(Box.createRigidArea(new Dimension(0,7)));
            }

        lastIndex = user.getTablesOfLists().size();
        revalidate();
        repaint();
        for (int i = 0; i < 2*user.getTablesOfLists().size() ; i = i+2) {
            if(user.getTablesOfLists().get(i/2).equals(user.getCurrentTable())) {
                ((TableButton) getComponent(i)).setBorder(BorderFactory.createLineBorder(Color.blue));
                ((TableButton) getComponent(i)).setName(user.getCurrentTable().getNameOfTable());
            }
            else
                ((TableButton)getComponent(i)).setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }
}
