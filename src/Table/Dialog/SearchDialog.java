package Table.Dialog;

import Table.Model.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by alex on 16.3.17.
 */
public class SearchDialog {

    private TableModel tableModel;
    private JTextField lastName;
    private JTextField group;
    private JComboBox minCount;
    private JComboBox maxCount;
    private final String LAST_NAME = "Фамилия:";
    private final String GROUP = "Группа:";
    private final String SOCIAL_WORK = "Общественная работа:";
    private final String CAUNT_OF_SOCIAL_WORK = "Каличество общественной работы:";
    private JFrame frame;
    private Window.StudentTable searchStudentTable;
    private JTextField socialWork;

    public SearchDialog(TableModel tableModel) {
        this.tableModel = tableModel;
        frame = createFrame();
        frame.setJMenuBar(createMenuBar());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private JFrame createFrame(){
        JFrame frame = new JFrame("Поиск Студентов");
        JLabel labelText = new JLabel();
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        Window.AddComponent.add(jPanelID,labelText, 0, 0, 3, 1);

        String[] labelString = {LAST_NAME, GROUP,SOCIAL_WORK,CAUNT_OF_SOCIAL_WORK};
        labelText = new JLabel(labelString[0]);
        Window.AddComponent.add(jPanelID,labelText, 0, 1, 1, 1);

        lastName = new JTextField(30);
        Window.AddComponent.add(jPanelID, lastName, 1, 1, 3, 1);
        labelText = new JLabel(labelString[1]);
        Window.AddComponent.add(jPanelID, labelText, 0, 2, 1, 1);

        group = new JTextField(30);
        Window.AddComponent.add(jPanelID, group, 1, 2, 3, 1);
        labelText = new JLabel(labelString[2]);
        Window.AddComponent.add(jPanelID, labelText, 0, 3, 1, 1);

        socialWork = new JTextField(30);
        Window.AddComponent.add(jPanelID, socialWork, 1, 3, 3, 1);
        String[] markString = {"-","1","2","3", "4", "5", "6", "7", "8", "9", "10"};
        labelText = new JLabel(labelString[3]);
        labelText.setHorizontalAlignment(JLabel.CENTER);
        Window.AddComponent.add(jPanelID,labelText, 0, 4, 1, 1);
        minCount = new JComboBox(markString);
        Window.AddComponent.add(jPanelID, minCount, 1, 4, 1, 1);
        maxCount = new JComboBox(markString);
        Window.AddComponent.add(jPanelID, maxCount, 2, 4, 1, 1);

        frame.add(jPanelID, BorderLayout.NORTH);
        JButton okButton = new JButton("Поиск");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        frame.add(okButton, BorderLayout.SOUTH);
        return frame;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        Font font = new Font("Verdana", Font.ITALIC, 12);

        JMenu table = new JMenu("Таблица");
        table.setFont(font);

        JMenuItem firstPage = new JMenuItem("Первая страница");
        firstPage.setFont(font);
        table.add(firstPage);
        firstPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentTable.firstPage();
            }
        });

        JMenuItem lastPage = new JMenuItem("Поcледняя страница");
        lastPage.setFont(font);
        table.add(lastPage);
        lastPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentTable.lastPage();
            }
        });
        JMenuItem nextPage = new JMenuItem("Следующая страница");
        nextPage.setFont(font);
        table.add(nextPage);
        nextPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentTable.nextPage();
            }
        });

        JMenuItem prevPage = new JMenuItem("Предведущая страница");
        prevPage.setFont(font);
        table.add(prevPage);
        prevPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentTable.prevPage();
            }
        });

        JMenu size = new JMenu("Студентов на странице");
        size.setFont(font);
        table.add(size);

        JMenuItem fiveSize = new JMenuItem("5");
        fiveSize.setFont(font);
        size.add(fiveSize);
        fiveSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentTable.setStudentOnPage(5);
                searchStudentTable.updateComponent();
            }
        });

        JMenuItem tenSize = new JMenuItem("10");
        tenSize.setFont(font);
        size.add(tenSize);
        tenSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentTable.setStudentOnPage(10);
                searchStudentTable.updateComponent();
            }
        });

        JMenuItem fiftySize = new JMenuItem("50");
        fiftySize.setFont(font);
        size.add(fiftySize);
        fiftySize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentTable.setStudentOnPage(50);
                searchStudentTable.updateComponent();
            }
        });

        menuBar.add(table);
        return menuBar;
    }

    private void searchStudent(){
        if (isAllCorrect()){
            if (searchStudentTable != null)
                frame.remove(searchStudentTable);
            searchStudentTable = new Window.StudentTable();
            searchStudentTable.getTableModel().getStudents().clear();
            for (Window.Student student: tableModel.getStudents()) {
                if (compliesTemplate(student)) {
                    searchStudentTable.getTableModel().getStudents().add(student);
                }
            }
            searchStudentTable.updateComponent();
            frame.add(searchStudentTable, BorderLayout.CENTER);
            frame.setSize(new Dimension(850,600));
            frame.revalidate();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog
                    (null, "Информация не корректна", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean compliesTemplate(Window.Student student) {
        if (!lastName.getText().equals(student.getLastName())) return false;
        if (!isTextEmpty(group.getText()) && !group.getText().equals(student.getGroupNumber())) return false;
        if (!isTextEmpty(socialWork.getText()) && !findSocialWork(socialWork.getText(),student.getSocialWork()) && minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-")) return false;
        if (!isTextEmpty(socialWork.getText()) && !findSocialWorkBitweenMinAndMax(socialWork.getText(),student.getSocialWork())) return false;
        return true;
    }

    private boolean findSocialWorkBitweenMinAndMax(String searchSocialWork, List<String> student) {
        if(minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-"))
            return true;

        int min=0;
        if(!minCount.getSelectedItem().equals("-"))
            min=Integer.parseInt((String)minCount.getSelectedItem());
        int max=0;
        if(!maxCount.getSelectedItem().equals("-"))
            max=Integer.parseInt((String)maxCount.getSelectedItem());

        int count=0;
        for (String elOfSocialWork:student) {
            if(elOfSocialWork.equals(searchSocialWork))
                count++;
        }
        if(count>=min&&count<=max)
            return true;
        return false;
    }

    private boolean findSocialWork(String searchSocialWork, List<String> student) {
        for (String elOfSocialWork:student) {
            if(elOfSocialWork.equals(searchSocialWork))
                return true;
        }
        return false;
    }

    private boolean isAllCorrect() {
        return !(isTextEmpty(lastName.getText()) || isNotCorrectText(lastName.getText()) || isNotCorrectText(group.getText()) || isNotCorrectText(socialWork.getText()) );
    }

    private boolean isTextEmpty(String text) {
        return text.equals("");
    }

    private boolean isNotCorrectText(String text) {
        return (text.length() > 0 && text.charAt(0) == ' ');
    }


}
