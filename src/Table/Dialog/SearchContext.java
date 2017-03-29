package Table.Dialog;

import Window.Student;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 29.3.17.
 */
public class SearchContext {
    private SearchStrategy searchStrategy;

    public SearchContext(SearchStrategy searchStrategy) {
        this.searchStrategy=searchStrategy;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }
    public void executeSearchStrategy(List<Student> searchStudent,Frame frame){
        searchStrategy.execute(searchStudent,frame);
    }
}
