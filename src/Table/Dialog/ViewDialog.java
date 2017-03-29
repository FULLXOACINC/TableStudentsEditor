package Table.Dialog;

import Table.AddComponent;
import Window.*;
import Table.Model.TableModel;
import Table.StudentTable;

import javax.swing.*;
//import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 16.3.17.
 */
public class ViewDialog implements SearchStrategy{


//    private TableModel tableModel;
    private StudentTable searchStudentTable;

    @Override
    public void execute(List<Student> searchStudent, Frame frame) {
            if (searchStudentTable != null)
                frame.remove(searchStudentTable);
            searchStudentTable = new StudentTable();
            for (Student student: searchStudent)
                    searchStudentTable.getTableModel().getStudents().add(student);

            searchStudentTable.updateComponent();
            frame.add(searchStudentTable, BorderLayout.CENTER);
            frame.setSize(new Dimension(850,600));
            frame.revalidate();
            frame.repaint();

    }
}
