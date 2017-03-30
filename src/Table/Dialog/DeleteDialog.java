package Table.Dialog;

import Window.*;
import Table.StudentTable;
import Table.Model.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



}

