package Table.Dialog;

import Table.Dialog.SearchStrategyPackage.SearchContext;
import Table.Model.TableModel;
import Table.StudentTable;
import Window.Student;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by alex on 29.3.17.
 */
public class SearchDialog {

    private final Dialog dialog;
    private TableModel tableModel;
    private JFrame frame;
    private StudentTable searchStudentTable;

    public SearchDialog(TableModel tableModel) {
        this.tableModel = tableModel;
        dialog = new Dialog("Поиск студентов", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        frame = dialog.getFrame();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    private void searchStudent() {
        if (!dialog.getLastName().equals("")) {
            if (searchStudentTable != null)
                frame.remove(searchStudentTable);
            List<Student> searchStudent = new SearchContext(dialog.getSearchContext()).executeSearchStrategy(tableModel.getStudents());
            searchStudentTable = new StudentTable();
            searchStudentTable.getTableModel().getStudents().addAll(searchStudent);
            searchStudentTable.updateComponent();
            frame.add(searchStudentTable, BorderLayout.CENTER);
            frame.setSize(new Dimension(850, 600));
            frame.revalidate();
            frame.repaint();

        } else {
            JOptionPane.showMessageDialog
                    (null, "Информация не корректна", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


}
