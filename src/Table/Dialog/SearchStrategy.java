package Table.Dialog;

import Window.Student;

import java.awt.*;
import java.util.List;

/**
 * Created by alex on 29.3.17.
 */
public interface SearchStrategy {

    void execute(List<Student> searchStudent,Frame frame);

}
