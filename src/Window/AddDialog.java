package Window;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by alex on 15.3.17.
 */
public class AddDialog {

        private static final String LAST_NAME = "Фамилия";
        private static final String FIRST_NAME = "Имя";
        private static final String MIDDLE_NAME = "Отчество";
        private static final String GROUP = "Группа";
        private static final String SEMESTR = " сем.";
        private StudentTable studentTable;
        private TableModel tableModel;
        private Map<String, JTextField> fieldID = new HashMap<String, JTextField>();


        public AddDialog(StudentTable studentTable) {
            this.studentTable = studentTable;
            tableModel = studentTable.getTableModel();
            JFrame frame = createFrame();
            frame.pack();
            frame.setLocationRelativeTo(studentTable);
            frame.setResizable(false);
            frame.setVisible(true);
        }

        private JFrame createFrame(){
            JFrame frame = new JFrame("Добавить студента");
            JPanel jPanelID = new JPanel();
            jPanelID.setLayout(new GridBagLayout());
            JLabel labelText = new JLabel("Добавить нового студента");
            labelText.setHorizontalAlignment(JLabel.CENTER);
            AddComponent.add(jPanelID,labelText, 0, 0, 2, 1);
            String[] labelString = {LAST_NAME, FIRST_NAME, MIDDLE_NAME, GROUP};
            for (int field = 0; field < labelString.length; field++) {
                labelText = new JLabel(labelString[field]);
                AddComponent.add(jPanelID, labelText, 0, field + 1, 1, 1);
                JTextField jtfField = new JTextField(30);
                fieldID.put(labelString[field],jtfField);
                AddComponent.add(jPanelID, jtfField, 1, field + 1, 1, 1);
            }
            for (int semestr=0; semestr < tableModel.SEMESTER_NUMBER;semestr++){
                labelText = new JLabel((semestr+1)+SEMESTR);
                AddComponent.add(jPanelID, labelText, 0, labelString.length+semestr + 2, 1, 1);
                JTextField jtfField = new JTextField(30);
                fieldID.put((semestr+1)+SEMESTR,jtfField);
                AddComponent.add(jPanelID, jtfField, 1, labelString.length+semestr + 2, 1, 1);
            }
            frame.add(jPanelID, BorderLayout.NORTH);
            frame.add(jPanelID, BorderLayout.NORTH);
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createNewStudent();
                }
            });
            frame.add(okButton, BorderLayout.SOUTH);
            return frame;
        }

        private void createNewStudent() {
            if (isAllCorrect()){
                java.util.List<String> socialwork = new ArrayList<String>();
                for (int index=1;index<=tableModel.SEMESTER_NUMBER;index++) {
                    if(getTextID(index+SEMESTR).equals(""))
                        socialwork.add("-");
                    else
                        socialwork.add(getTextID(index+SEMESTR));
                }
                tableModel.getStudents().add(new Student(getTextID(LAST_NAME),
                        getTextID(FIRST_NAME),
                        getTextID(MIDDLE_NAME),
                        getTextID(GROUP),
                        socialwork));
                studentTable.updateComponent();

            } else {
                JOptionPane.showMessageDialog
                        (null, "Информация не корректна", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }


    private boolean isAllCorrect() {
        return !(isNotCorrectID(LAST_NAME) && isNotCorrectID(FIRST_NAME) && isNotCorrectID(MIDDLE_NAME));
    }

    private String getTextID(String key) {
        return fieldID.get(key).getText();
    }

    private boolean isNotCorrectID(String key) {
        return ((fieldID.get(key).getText().equals("")) ||
                (fieldID.get(key).getText().length() > 0 && fieldID.get(key).getText().charAt(0) == ' '));
    }



}
