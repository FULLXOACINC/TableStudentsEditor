package Table.Dialog;


import Table.AddComponent;
import Table.Model.Student;
import Table.StudentTable;
import Table.Model.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


/**
 * Created by alex on 15.3.17.
 */
public class AddDialog {

        private final String LAST_NAME = "Фамилия";
        private final String FIRST_NAME = "Имя";
        private final String FATHER_NAME = "Отчество";
        private final String GROUP = "Группа";
        private final String SEMESTR = " сем.";
        private final String ADD = "Добавить";
        private StudentTable studentTable;
        private TableModel tableModel;
        private Map<String, JTextField> field = new HashMap<String, JTextField>();


        public AddDialog(StudentTable mainTable) {
            this.studentTable = mainTable;
            tableModel = mainTable.getTableModel();
            JFrame frame = createFrame();
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        }

        private JFrame createFrame(){
            JFrame frame = new JFrame("Добавить студента");
            JPanel jPanelID = new JPanel();
            jPanelID.setLayout(new GridBagLayout());
            JLabel labelText = new JLabel();
            AddComponent.add(jPanelID,labelText, 0, 0, 2, 1);
            String[] labelString = {LAST_NAME, FIRST_NAME, FATHER_NAME, GROUP};
            for (int field = 0; field < labelString.length; field++) {
                labelText = new JLabel(labelString[field]);
                AddComponent.add(jPanelID, labelText, 0, field + 1, 1, 1);
                JTextField jtfField = new JTextField(30);
                this.field.put(labelString[field],jtfField);
                AddComponent.add(jPanelID, jtfField, 1, field + 1, 1, 1);
            }
            for (int semestr=0; semestr < tableModel.SEMESTER_NUMBER;semestr++){
                labelText = new JLabel((semestr+1)+SEMESTR);
                AddComponent.add(jPanelID, labelText, 0, labelString.length+semestr + 2, 1, 1);
                JTextField jtfField = new JTextField(30);
                field.put((semestr+1)+SEMESTR,jtfField);
                AddComponent.add(jPanelID, jtfField, 1, labelString.length+semestr + 2, 1, 1);
            }
            frame.add(jPanelID, BorderLayout.NORTH);
            frame.add(jPanelID, BorderLayout.NORTH);
            JButton okButton = new JButton(ADD);
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
                List<String> socialwork = new ArrayList<String>();
                for (int index=1;index<=tableModel.SEMESTER_NUMBER;index++) {
                    if(getTextID(index+SEMESTR).equals(""))
                        socialwork.add("-");
                    else
                        socialwork.add(getTextID(index+SEMESTR));
                }
                tableModel.getStudents().add(new Student(getTextID(LAST_NAME),
                        getTextID(FIRST_NAME),
                        getTextID(FATHER_NAME),
                        getTextID(GROUP),
                        socialwork));
                studentTable.updateComponent();
            } else {
                JOptionPane.showMessageDialog
                        (null, "Информация не корректна", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }


    private boolean isAllCorrect() {
        return !(isNotCorrectText(LAST_NAME) || isNotCorrectText(FIRST_NAME) || isNotCorrectText(GROUP));
    }

    private String getTextID(String key) {
        return field.get(key).getText();
    }

    private boolean isNotCorrectText(String key) {
        return (field.get(key).getText().equals("") ||
                (field.get(key).getText().length() > 0 && field.get(key).getText().charAt(0) == ' '));
    }



}
