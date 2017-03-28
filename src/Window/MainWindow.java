package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by alex on 15.3.17.
 */
public class MainWindow {
    private final StudentTable studentTable;
    private JFrame frame;
    private FileWorker fileWorker;

    public MainWindow() {

        frame = new JFrame("Таблица общественных работ студентов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createMenuPanel(), BorderLayout.PAGE_START);
        studentTable = new StudentTable();
        fileWorker = new FileWorker(studentTable.getTableModel());
        frame.add(studentTable, BorderLayout.CENTER);
        frame.setJMenuBar(createMenuBar());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    private JToolBar createMenuPanel() {
        JToolBar toolBar = new JToolBar();

        toolBar.add(AddComponent.makeButton(new JButton(), "save.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.saveFile();
            }
        }));
        toolBar.add(AddComponent.makeButton(new JButton(), "open.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.openFile();
                studentTable.updateComponent();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(AddComponent.makeButton(new JButton(), "add.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddDialog(studentTable);

            }
        }));
        toolBar.add(AddComponent.makeButton(new JButton(), "delete.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(studentTable);

            }
        }));
        toolBar.add(AddComponent.makeButton(new JButton(), "search.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchDialog(studentTable.getTableModel());

            }
        }));

        return toolBar;

    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        Font font = new Font("Verdana", Font.ITALIC, 12);
        fileMenu.setFont(font);


        JMenuItem openItem = new JMenuItem("Открыть");
        openItem.setFont(font);
        fileMenu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.openFile();
                studentTable.updateComponent();
            }
        });

        JMenuItem saveItem = new JMenuItem("Сохранить");
        saveItem.setFont(font);
        fileMenu.add(saveItem);
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.saveFile();
            }
        });

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выйти");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(fileMenu);


        JMenu table = new JMenu("Таблица");
        table.setFont(font);

        JMenuItem firstPage = new JMenuItem("Первая страница");
        firstPage.setFont(font);
        table.add(firstPage);
        firstPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentTable.firstPage();
            }
        });

        JMenuItem lastPage = new JMenuItem("Поcледняя страница");
        lastPage.setFont(font);
        table.add(lastPage);
        lastPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentTable.lastPage();
            }
        });
        JMenuItem nextPage = new JMenuItem("Следующая страница");
        nextPage.setFont(font);
        table.add(nextPage);
        nextPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentTable.nextPage();
            }
        });

        JMenuItem prevPage = new JMenuItem("Предведущая страница");
        prevPage.setFont(font);
        table.add(prevPage);
        prevPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentTable.prevPage();
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
                studentTable.setStudentOnPage(5);
                studentTable.updateComponent();
            }
        });

        JMenuItem tenSize = new JMenuItem("10");
        tenSize.setFont(font);
        size.add(tenSize);
        tenSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentTable.setStudentOnPage(10);
                studentTable.updateComponent();
            }
        });

        JMenuItem fiftySize = new JMenuItem("50");
        fiftySize.setFont(font);
        size.add(fiftySize);
        fiftySize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentTable.setStudentOnPage(50);
                studentTable.updateComponent();
            }
        });

        menuBar.add(table);
        return menuBar;
    }

    public static void main(String[] args) {
        new MainWindow();
    }

}
