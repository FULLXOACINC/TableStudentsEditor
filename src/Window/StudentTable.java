package Window;

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
    private int currentPage = 1;
    private int studentOnPage = 5;


    public StudentTable(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        tableModel = new TableModel();
        makePanel();
    }

    public void makePanel() {
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
        AddComponent.add(table, "ФИО", 0, 0, 1, 3);
        AddComponent.add(table, "Группа", 1, 0, 1, 3);
        AddComponent.add(table, "Общественная работа", 2, 0, tableModel.SEMESTER_NUMBER * 2, 1);
        for (int i = 0, x = 2; i < tableModel.SEMESTER_NUMBER; i++, x++) {
            AddComponent.add(table, (i+1)+" сем.", x, 2, 1, 1);
        }
        int firstStudentOnPage = studentOnPage * (currentPage - 1);
        int lineInHeaderTable = 3;
        for (int y = lineInHeaderTable, student = firstStudentOnPage;
             y < studentOnPage + lineInHeaderTable && student < students.size();
             y++, student++) {
            countStudent++;
            for (int i = 0; i < tableModel.SEMESTER_NUMBER+2 ; i++) {
                String write = getFieldForStudent(students.get(student), i);
                AddComponent.add(table, write, i, y, 1, 1);
            }
        }
        AddComponent.add(table, "Страница:"+currentPage+"/"+getNumberMaxPage()+" Студентов на странице:"+countStudent+" Всего студентов:"+students.size(), 0, studentOnPage + lineInHeaderTable, tableModel.SEMESTER_NUMBER * 2, 3);
        return table;
    }

    private JToolBar makeToolsPanel() {

        JToolBar panel = new JToolBar();
        panel.add(AddComponent.makeButton(new JButton(), "first.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstPage();
            }
        }));

        panel.add(AddComponent.makeButton(new JButton(), "last.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lastPage();
            }
        }));
        panel.add(AddComponent.makeButton(new JButton(), "prev.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prevPage();
            }
        }));
        panel.add(AddComponent.makeButton(new JButton(), "next.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextPage();
            }
        }));
        String[] size = {"5","10","50"};
        JComboBox sizeBox = new JComboBox(size);
        sizeBox.setSelectedIndex(Arrays.asList(size).indexOf(Integer.toString(studentOnPage)));
        sizeBox.setMaximumSize(new Dimension(70,100));
        sizeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String change = (String) cb.getSelectedItem();
                int size=Integer.parseInt(change);
                firstPage();
                setStudentOnPage(size);
                updateComponent();
            }
        });

        panel.add(sizeBox);
        return panel;
    }

    public String getFieldForStudent(Student student, int i) {
        if (i == 0) return student.getLastName() + " " + student.getFirstName() + " " + student.getFatherName();
        else if (i == 1) return student.getGroupNumber();
        else {
            return student.getSocialWork().get(i-2);
        }
    }

    public void nextPage(){
        if (hasNextPage()) {
            currentPage++;
            updateComponent();
        }
    }

    private boolean hasNextPage() {
        return tableModel.getStudents().size() > studentOnPage * (currentPage - 1) + studentOnPage;
    }

    public void prevPage(){
        if (currentPage > 1) {
            currentPage--;
            updateComponent();
        }
    }

    public void firstPage(){
        if (currentPage > 1) {
            currentPage = 1;
            updateComponent();
        }
    }

    public void lastPage(){
        if (currentPage != getNumberMaxPage()){
            currentPage = getNumberMaxPage();
            updateComponent();
        }
    }

    private int getNumberMaxPage() {
        return (int)((tableModel.getStudents().size() - 1)/ studentOnPage) + 1;
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

    public void setStudentOnPage(int studentOnPage) {
        this.studentOnPage = studentOnPage;
    }

}
