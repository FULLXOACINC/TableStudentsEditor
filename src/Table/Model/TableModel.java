package Table.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 15.3.17.
 */
public class TableModel {
    public final int SEMESTER_NUMBER = 10;
    private List<Student> students;

    public TableModel() {
        students = new ArrayList<Student>();
    }

    public List<Student> getStudents() {
        return students;
    }
}