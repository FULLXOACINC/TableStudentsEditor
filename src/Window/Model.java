package Window;

import Table.Model.TableModel;

import java.util.List;

/**
 * Created by alex on 30.3.17.
 */
public class Model {

    private TableModel tableModel;

    public Model(TableModel tableModel) {
        this.tableModel=tableModel;
    }

    public void setStudentOnPage(int studentOnPage) {
        tableModel.setStudentOnPage(studentOnPage);
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void prevPage() {
        tableModel.prevPage();
    }

    public void nextPage() {
        tableModel.nextPage();
    }

    public void lastPage() {
        tableModel.lastPage();
    }

    public void firstPage() {
        tableModel.firstPage();
    }

    public List<Student> getStudents() {
        return tableModel.getStudents();
    }

    public int getSemestrNumber() {
        return tableModel.SEMESTER_NUMBER;
    }
}
