package Table.Dialog;

import Window.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 31.3.17.
 */
public class NameAndSocialWorkAndGroupSearch implements SearchStrategy {
    @Override
    public List<Student> execute(List<Student> students, Dialog dialog) {
        String minCount = dialog.getMinCount();
        String maxCount = dialog.getMaxCount();
        List<Student> searchStudent= new ArrayList<Student>();
        for(Student student:students)
            if(Find.correctName(dialog.getLastName(),student.getLastName())||Find.correctGroup(dialog.getGroup(),student.getGroupNumber())||Find.findSocialWorkBitweenMinAndMax(dialog.getSocialWork(),student.getSocialWork(), minCount, maxCount))
                searchStudent.add(student);
        return searchStudent;
    }
}
