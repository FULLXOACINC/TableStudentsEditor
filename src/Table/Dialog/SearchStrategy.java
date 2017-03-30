package Table.Dialog;

import Window.Student;

import java.util.List;

/**
 * Created by alex on 29.3.17.
 */
public interface SearchStrategy {


    List<Student> execute(List<Student> students, Dialog dialog);

}
