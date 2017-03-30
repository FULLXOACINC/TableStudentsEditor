package Table.Dialog;

import Window.SocialWork;
import Window.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 30.3.17.
 */
public class NameAndSocialWorkSearch implements SearchStrategy {

    @Override
    public List<Student> execute(List<Student> students, Dialog dialog) {
        String minCount = dialog.getMinCount();
        String maxCount = dialog.getMaxCount();
        List<Student> searchStudent= new ArrayList<Student>();
        for(Student student:students)
            if(Find.correctName(dialog.getLastName(),student.getLastName()) ||Find.findSocialWorkBitweenMinAndMax(dialog.getSocialWork(),student.getSocialWork(), minCount, maxCount))
                searchStudent.add(student);
        return searchStudent;
    }


}
