package Table.Dialog.SearchStrategyPackage;

import Table.Dialog.Dialog;
import Window.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 30.3.17.
 */
public class NameAndGroupSearch implements SearchStrategy {
    @Override
    public List<Student> execute(List<Student> students, Dialog dialog) {
        List<Student> searchStudent= new ArrayList<Student>();
        for(Student student:students)
            if(Find.correctName(dialog.getLastName(),student.getLastName()) || Find.correctGroup(dialog.getGroup(),student.getGroupNumber()))
                searchStudent.add(student);
        return searchStudent;
    }



}
