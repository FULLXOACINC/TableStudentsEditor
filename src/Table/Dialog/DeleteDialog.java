package Table.Dialog;

import Table.AddComponent;
import Window.*;
import Table.StudentTable;
import Table.Model.TableModel;

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

    private TableModel tableModel;
    private JFrame frame;
    private Dialog dialog;
    private StudentTable mainTable;

    public DeleteDialog(StudentTable studentTable) {
        this.mainTable = studentTable;
        tableModel = studentTable.getTableModel();
        dialog = new Dialog("Удаление студентов", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        frame = dialog.getFrame();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    private void deleteStudent() {
        List<Student> searchStudent = new SearchContext(dialog.getSearchContext()).executeSearchStrategy(tableModel.getStudents(), dialog);
        tableModel.getStudents().removeAll(searchStudent);
        mainTable.updateComponent();
        if (searchStudent.size() > 0) {
            JOptionPane.showMessageDialog
                    (null, "Удалено " + searchStudent.size() + " студентов", "Информация", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog
                    (null, "Студент не найден", "Внимание", JOptionPane.WARNING_MESSAGE);
        }

    }

//    private boolean compliesTemplate(Student student) {
//        if (!lastName.getText().equals(student.getLastName())) return false;
//        if (!isTextEmpty(group.getText()) && !group.getText().equals(student.getGroupNumber())) return false;
//        if (!isTextEmpty(socialWork.getText()) && !findSocialWork(socialWork.getText(),student.getSocialWork()) && minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-")) return false;
//        if (!isTextEmpty(socialWork.getText()) && !findSocialWorkBitweenMinAndMax(socialWork.getText(),student.getSocialWork())) return false;
//        return true;
//    }
//
//    private boolean findSocialWorkBitweenMinAndMax(String searchSocialWork, List<SocialWork> student) {
//        if(minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-"))
//            return true;
//
//        int min=0;
//        if(!minCount.getSelectedItem().equals("-"))
//            min=Integer.parseInt((String)minCount.getSelectedItem());
//        int max=0;
//        if(!maxCount.getSelectedItem().equals("-"))
//            max=Integer.parseInt((String)maxCount.getSelectedItem());
//
//        int count=0;
//        for (SocialWork elOfSocialWork:student) {
//            if(elOfSocialWork.getWork().equals(searchSocialWork))
//                count++;
//        }
//        if(count>=min&&count<=max)
//            return true;
//        return false;
//    }
//
//    private boolean findSocialWork(String searchSocialWork, List<SocialWork> student) {
//        for (SocialWork elOfSocialWork:student) {
//            if(elOfSocialWork.getWork().equals(searchSocialWork))
//                return true;
//        }
//        return false;
//    }
//


}

