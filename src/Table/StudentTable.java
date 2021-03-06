package Table;

import Window.Student;
import Table.Model.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Arrays;
import java.util.List;
/**
 * Created by alex on 15.3.17.
 */
public class StudentTable extends JComponent{

    private TableModel tableModel;
    private JScrollPane scrollTable;

    public StudentTable(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        tableModel = new TableModel();
        makePanel();
    }

    private void makePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(makeTable(), BorderLayout.NORTH);
        scrollTable = new JScrollPane(tablePanel);
        scrollTable.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                updateScrollTable();
            }
        });
        add(scrollTable);
        add(makeToolsPanel());
    }

    private JPanel makeTable(){
        JPanel table = new JPanel();
        table.setLayout(new GridBagLayout());
        List<Student> students = tableModel.getStudents();
        int countStudent=0;
        add(table, "ФИО", 0, 0, 1, 3);
        add(table, "Группа", 1, 0, 1, 3);
        add(table, "Общественная работа", 2, 0, tableModel.SEMESTER_NUMBER * 2, 1);
        for (int i = 0, x = 2; i < tableModel.SEMESTER_NUMBER; i++, x++) {
            add(table, (i+1)+" сем.", x, 2, 1, 1);
        }
        int firstStudentOnPage = tableModel.getStudentOnPage() * (tableModel.getCurrentPage()- 1);
        int lineInHeaderTable = 3;
        for (int y = lineInHeaderTable, student = firstStudentOnPage;
             y < tableModel.getStudentOnPage() + lineInHeaderTable && student < students.size();
             y++, student++) {
            countStudent++;
            for (int i = 0; i < tableModel.SEMESTER_NUMBER+2 ; i++) {
                String write = getFieldForStudent(students.get(student), i);
                add(table, write, i, y, 1, 1);
            }
        }
        add(table, "Страница:"+tableModel.getCurrentPage()+"/"+tableModel.getNumberMaxPage()+" Студентов на странице:"+countStudent+" Всего студентов:"+students.size(), 0, tableModel.getStudentOnPage() + lineInHeaderTable, tableModel.SEMESTER_NUMBER * 2, 3);
        return table;
    }

    private JToolBar makeToolsPanel() {

        JToolBar panel = new JToolBar();
        panel.add(AddComponent.makeButton(new JButton(), "first.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.firstPage();
                updateComponent();
            }
        }));

        panel.add(AddComponent.makeButton(new JButton(), "last.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.lastPage();
                updateComponent();
            }
        }));
        panel.add(AddComponent.makeButton(new JButton(), "prev.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.prevPage();
                updateComponent();
            }
        }));
        panel.add(AddComponent.makeButton(new JButton(), "next.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.nextPage();
                updateComponent();
            }
        }));
        String[] studentsOnPage = {"5","10","50"};
        JComboBox sizeBox = new JComboBox(studentsOnPage);
        sizeBox.setSelectedIndex(Arrays.asList(studentsOnPage).indexOf(Integer.toString(tableModel.getStudentOnPage())));
        sizeBox.setMaximumSize(new Dimension(70,100));
        sizeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String change = (String) cb.getSelectedItem();
                int size=Integer.parseInt(change);
                tableModel.firstPage();
                tableModel.setStudentOnPage(size);
                updateComponent();
            }
        });

        panel.add(sizeBox);
        return panel;
    }

    private String getFieldForStudent(Student student, int i) {
        if (i == 0) return student.getLastName() + " " + student.getFirstName() + " " + student.getFatherName();
        else if (i == 1) return student.getGroupNumber();
        else {
            return student.getSocialWork().get(i-2).getWork();
        }
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void updateComponent(){
        removeAll();
        makePanel();
        revalidate();
        repaint();
    }

    private void updateScrollTable() {
        scrollTable.revalidate();
        scrollTable.repaint();
    }

    private void add(JPanel panel, String nameLabel,int gridX, int gridY, int gridWidth, int gridHeight) {
        JLabel label = new JLabel(nameLabel);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints gridBagConstraints = new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, 1.0, 1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label, gridBagConstraints);
    }

}
