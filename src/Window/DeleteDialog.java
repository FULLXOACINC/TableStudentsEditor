package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by alex on 16.3.17.
 */
public class DeleteDialog {

    private final String LAST_NAME = "Фамилия:";
    private final String GROUP = "Группа:";
    private final String SOCIAL_WORK = "Общественная работа:";
    private final String CAUNT_OF_SOCIAL_WORK = "Каличество общественной работы:";
    private final String DELETE = "Удалить";
    private TableModel tableModel;
    private JTextField lastName;
    private JTextField group;
    private JComboBox minCount;
    private JComboBox maxCount;
    private JFrame frame;
    private JTextField socialWork;
    private StudentTable mainTable;

    public DeleteDialog(StudentTable studentTable) {
        this.mainTable =studentTable;
        tableModel = studentTable.getTableModel();
        frame = createFrame();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private JFrame createFrame(){
        JFrame frame = new JFrame("Удаление студентов");
        JLabel labelText = new JLabel();
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID,labelText, 0, 0, 3, 1);

        String[] labelString = {LAST_NAME, GROUP,SOCIAL_WORK,CAUNT_OF_SOCIAL_WORK};
        labelText = new JLabel(labelString[0]);
        AddComponent.add(jPanelID,labelText, 0, 1, 1, 1);

        lastName = new JTextField(30);
        AddComponent.add(jPanelID, lastName, 1, 1, 3, 1);
        labelText = new JLabel(labelString[1]);
        AddComponent.add(jPanelID, labelText, 0, 2, 1, 1);

        group = new JTextField(30);
        AddComponent.add(jPanelID, group, 1, 2, 3, 1);
        labelText = new JLabel(labelString[2]);
        AddComponent.add(jPanelID, labelText, 0, 3, 1, 1);

        socialWork = new JTextField(30);
        AddComponent.add(jPanelID, socialWork, 1, 3, 3, 1);
        String[] markString = {"-","1","2","3", "4", "5", "6", "7", "8", "9", "10"};
        labelText = new JLabel(labelString[3]);
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID,labelText, 0, 4, 1, 1);
        minCount = new JComboBox(markString);
        AddComponent.add(jPanelID, minCount, 1, 4, 1, 1);
        maxCount = new JComboBox(markString);
        AddComponent.add(jPanelID, maxCount, 2, 4, 1, 1);

        frame.add(jPanelID, BorderLayout.NORTH);
        JButton okButton = new JButton(DELETE);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        frame.add(okButton, BorderLayout.SOUTH);
        return frame;
    }

    private void deleteStudent(){
        if (isAllCorrect()){
            int counterStudent = 0;
            Iterator<Student> itr = tableModel.getStudents().iterator();
            while (itr.hasNext()) {
                Student student = itr.next();
                if (compliesTemplate(student)) {
                    itr.remove();
                    counterStudent++;
                }
            }
            mainTable.updateComponent();
            if (counterStudent > 0) {
                JOptionPane.showMessageDialog
                        (null, "Удалено " + counterStudent + " студентов", "Информация", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog
                        (null, "Студент не найден", "Внимание", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog
                    (null, "Информация не корректна", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean compliesTemplate(Student student) {
        if (!lastName.getText().equals(student.getLastName())) return false;
        if (!isTextEmpty(group.getText()) && !group.getText().equals(student.getGroupNumber())) return false;
        if (!isTextEmpty(socialWork.getText()) && !findSocialWork(socialWork.getText(),student.getSocialWork()) && minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-")) return false;
        if (!isTextEmpty(socialWork.getText()) && !findSocialWorkBitweenMinAndMax(socialWork.getText(),student.getSocialWork())) return false;
        return true;
    }

    private boolean findSocialWorkBitweenMinAndMax(String searchSocialWork, List<String> student) {
        if(minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-"))
            return true;

        int min=0;
        if(!minCount.getSelectedItem().equals("-"))
            min=Integer.parseInt((String)minCount.getSelectedItem());
        int max=0;
        if(!maxCount.getSelectedItem().equals("-"))
            max=Integer.parseInt((String)maxCount.getSelectedItem());

        int count=0;
        for (String elOfSocialWork:student) {
            if(elOfSocialWork.equals(searchSocialWork))
                count++;
        }
        if(count>=min&&count<=max)
            return true;
        return false;
    }

    private boolean findSocialWork(String searchSocialWork, List<String> student) {
        for (String elOfSocialWork:student) {
            if(elOfSocialWork.equals(searchSocialWork))
                return true;
        }
        return false;
    }

    private boolean isAllCorrect() {
        return !(isTextEmpty(lastName.getText()) || isNotCorrectText(lastName.getText()) || isNotCorrectText(group.getText()) || isNotCorrectText(socialWork.getText()) );
    }

    private boolean isTextEmpty(String text) {
        return text.equals("");
    }

    private boolean isNotCorrectText(String text) {
        return (text.length() > 0 && text.charAt(0) == ' ');
    }

}
