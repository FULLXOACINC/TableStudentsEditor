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

    public MainWindow() {

        frame = new JFrame("Student Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createMenuPanel(), BorderLayout.PAGE_START);
        studentTable = new StudentTable();
        frame.add(studentTable, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    private JToolBar createMenuPanel() {
        JToolBar toolBar = new JToolBar();

        toolBar.add(makeButton(new JButton(), "save.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("save");
            }
        }));
        toolBar.add(makeButton(new JButton(), "open.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("open");
            }
        }));
        toolBar.addSeparator();
        toolBar.add(makeButton(new JButton(), "add.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddDialog(studentTable);

            }
        }));
        toolBar.add(makeButton(new JButton(), "delete.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                studentTable.getTableModel().getStudents().remove(0);
//                studentTable.updateComponent();
            }
        }));
        toolBar.add(makeButton(new JButton(), "search.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchDialog(studentTable);

            }
        }));

        return toolBar;


    }

    private JButton makeButton(JButton button, String imgString, ActionListener action){
        button.addActionListener(action);
        ImageIcon img = new ImageIcon("img/"+imgString);
        button.setIcon(img);
        return button;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        Font font = new Font("Verdana", Font.ITALIC, 12);
//        fileMenu.setFont(font);
//
//        JMenuItem newMenu = new JMenuItem("New");
//        newMenu.setFont(font);
//        fileMenu.add(newMenu);
//        newMenu.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                int answer = JOptionPane.showConfirmDialog(
//                        frame,
//                        "Del all?",
//                        "New",
//                        JOptionPane.YES_NO_OPTION);
//                if(answer==0)
//                    fileWork.newFile();
//                updateWindow();
//
//            }
//        });
//
//        JMenuItem openItem = new JMenuItem("Open");
//        openItem.setFont(font);
//        fileMenu.add(openItem);
//        openItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                fileWork.openFile();
//                updateWindow();
//            }
//        });
//
//        JMenuItem saveItem = new JMenuItem("Save");
//        saveItem.setFont(font);
//        fileMenu.add(saveItem);
//        saveItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                fileWork.saveFile();
//                updateWindow();
//            }
//        });
//
//        fileMenu.addSeparator();
//
//        JMenuItem exitItem = new JMenuItem("Exit");
//        exitItem.setFont(font);
//        fileMenu.add(exitItem);
//
//        exitItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int answer = JOptionPane.showOptionDialog(frame,
//                        "Would you like to save this document?",
//                        "Save",
//                        JOptionPane.YES_NO_CANCEL_OPTION,
//                        JOptionPane.QUESTION_MESSAGE, null, null, null);
//                if(answer==2)
//                    return;
//                if(answer==0){
//                    fileWork.saveFile();
//                }
//                System.exit(0);
//
//            }
//        });
//
//        menuBar.add(fileMenu);
//
//        JMenu edit = new JMenu("Edit");
//        edit.setFont(font);
//
//        JMenuItem copy = new JMenuItem("Copy");
//        copy.setFont(font);
//        edit.add(copy);
//        copy.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().copy();
//                updateWindow();
//
//            }
//        });
//
//        JMenuItem paste = new JMenuItem("Paste");
//        paste.setFont(font);
//        edit.add(paste);
//        paste.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().paste();
//                updateWindow();
//
//            }
//        });
//
//        JMenuItem cut = new JMenuItem("Cut");
//        cut.setFont(font);
//        edit.add(cut);
//        cut.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().cut();
//                updateWindow();
//
//            }
//        });
//
//        menuBar.add(edit);
//
//        JMenu formatter = new JMenu("Format");
//        formatter.setFont(font);
//
//        JMenuItem bold = new JMenuItem("Bold");
//        bold.setFont(font);
//        formatter.add(bold);
//        bold.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().changeStyleOnBold();
//                updateWindow();
//            }
//        });
//
//        JMenuItem italic = new JMenuItem("Italic");
//        italic.setFont(font);
//        formatter.add(italic);
//        italic.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().changeStyleOnItalic();
//                updateWindow();
//            }
//        });
//
//        JMenu size = new JMenu("Size");
//        size.setFont(font);
//        formatter.add(size);
//
//        JMenuItem tenSize = new JMenuItem("10");
//        tenSize.setFont(font);
//        size.add(tenSize);
//        tenSize.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().changeSizeFont(10);
//                updateWindow();
//            }
//        });
//
//        JMenuItem fiftySize = new JMenuItem("50");
//        fiftySize.setFont(font);
//        size.add(fiftySize);
//        fiftySize.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().changeSizeFont(50);
//                updateWindow();
//            }
//        });
//
//        JMenu textFont = new JMenu("Font");
//        textFont.setFont(font);
//        formatter.add(textFont);
//
//        JMenuItem liberSerif = new JMenuItem("Liberation Serif");
//        liberSerif.setFont(font);
//        textFont.add(liberSerif);
//        liberSerif.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().changeTypeFont("Liberation Serif");
//                updateWindow();
//            }
//        });
//
//        JMenuItem monospaned = new JMenuItem("Monospaned");
//        monospaned.setFont(font);
//        textFont.add(monospaned);
//        monospaned.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textPanel.getTextPanelModel().changeTypeFont("Monospaned");
//                updateWindow();
//            }
//        });
//
//        menuBar.add(formatter);

        return menuBar;
    }


    public static void main(String[] args){
        new MainWindow();
    }

}
