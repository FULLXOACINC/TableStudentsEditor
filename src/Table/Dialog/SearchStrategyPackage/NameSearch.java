package Table.Dialog.SearchStrategyPackage;

import Table.Dialog.Dialog;
import Window.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 16.3.17.
 */
public class NameSearch implements SearchStrategy {
    @Override
    public List<Student> execute(List<Student> students, Dialog dialog) {
        List<Student> searchStudent= new ArrayList<Student>();
        for(Student student:students)
            if(Find.correctName(dialog.getLastName(),student.getLastName()))
                searchStudent.add(student);
        return searchStudent;
    }



}
