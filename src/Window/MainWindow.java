package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by alex on 15.3.17.
 */
public class MainWindow {
    private final MainTable mainTable;
    private JFrame frame;
    private FileWorker fileWorker;

    public MainWindow() {

        frame = new JFrame("Таблица общественных работ студентов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createMenuPanel(), BorderLayout.PAGE_START);
        mainTable = new MainTable();
        fileWorker = new FileWorker(mainTable.getTableModel());
        frame.add(mainTable, BorderLayout.CENTER);
        frame.setJMenuBar(createMenuBar());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    private JToolBar createMenuPanel() {
        JToolBar toolBar = new JToolBar();

        toolBar.add(AddComponenter.makeButton(new JButton(), "save.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.saveFile();
            }
        }));
        toolBar.add(AddComponenter.makeButton(new JButton(), "open.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.openFile();
                mainTable.updateComponent();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(AddComponenter.makeButton(new JButton(), "add.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddDialog(mainTable);

            }
        }));
        toolBar.add(AddComponenter.makeButton(new JButton(), "delete.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(mainTable);

            }
        }));
        toolBar.add(AddComponenter.makeButton(new JButton(), "search.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchDialog(mainTable.getTableModel());

            }
        }));

        return toolBar;

    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        Font font = new Font("Verdana", Font.ITALIC, 12);
        fileMenu.setFont(font);


        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(font);
        fileMenu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.openFile();
                mainTable.updateComponent();
            }
        });

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setFont(font);
        fileMenu.add(saveItem);
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileWorker.saveFile();
            }
        });

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(fileMenu);


        JMenu table = new JMenu("Table");
        table.setFont(font);

        JMenuItem firstPage = new JMenuItem("First page");
        firstPage.setFont(font);
        table.add(firstPage);
        firstPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTable.firstPage();
            }
        });

        JMenuItem lastPage = new JMenuItem("Last page");
        lastPage.setFont(font);
        table.add(lastPage);
        lastPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTable.lastPage();
            }
        });
        JMenuItem nextPage = new JMenuItem("Next page");
        nextPage.setFont(font);
        table.add(nextPage);
        nextPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTable.nextPage();
            }
        });

        JMenuItem prevPage = new JMenuItem("Prev page");
        prevPage.setFont(font);
        table.add(prevPage);
        prevPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTable.prevPage();
            }
        });

        JMenu size = new JMenu("Student on page");
        size.setFont(font);
        table.add(size);

        JMenuItem fiveSize = new JMenuItem("5");
        fiveSize.setFont(font);
        size.add(fiveSize);
        fiveSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTable.setStudentOnPage(5);
                mainTable.updateComponent();
            }
        });

        JMenuItem tenSize = new JMenuItem("10");
        tenSize.setFont(font);
        size.add(tenSize);
        tenSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTable.setStudentOnPage(10);
                mainTable.updateComponent();
            }
        });

        JMenuItem fiftySize = new JMenuItem("50");
        fiftySize.setFont(font);
        size.add(fiftySize);
        fiftySize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTable.setStudentOnPage(50);
                mainTable.updateComponent();
            }
        });

        menuBar.add(table);
        return menuBar;
    }


    public static void main(String[] args) {
        new MainWindow();
    }

}
