package Table.Dialog.SearchStrategyPackage;

import Table.Dialog.Dialog;
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
        for(Student student:students){
            boolean correctNameAndSocialWork =Find.correctName(dialog.getLastName(),student.getLastName()) || Find.findSocialWorkBitweenMinAndMax(dialog.getSocialWork(),student.getSocialWork(), minCount, maxCount);
            if(correctNameAndSocialWork)
                searchStudent.add(student);
        }

        return searchStudent;
    }


}
