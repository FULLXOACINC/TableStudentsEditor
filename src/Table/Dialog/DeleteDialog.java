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
public class DeleteDialog implements SearchStrategy{


    private TableModel tableModel;
    private StudentTable searchStudentTable;

    @Override
    public void execute(List<Student> searchStudent,Frame frame) {
            if (searchStudentTable != null)
                frame.remove(searchStudentTable);
            searchStudentTable = new StudentTable();
            searchStudentTable.getTableModel().getStudents().clear();

        for (Student student: searchStudent)
            System.out.println(student.getFatherName());

            searchStudentTable.updateComponent();
            frame.add(searchStudentTable, BorderLayout.CENTER);
            frame.setSize(new Dimension(850,600));
            frame.revalidate();
            frame.repaint();

    }
}


