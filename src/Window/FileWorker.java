package Window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 16.3.17.
 */
public class FileWorker {

    private static final String LAST_NAME = "last_name";
    private static final String FIRST_NAME = "first_name";
    private static final String FATHER_NAME = "father_name";
    private static final String GROUP = "group";
    private static final String SOCAIL_WORK = "social_work";
    private static final String STUDENT = "student";
    private static final String STUDENTS = "students";
    private static final String FORMAT = "UTF-8";
    private static final String VER = "1.0";
    private static final String EXTENSION = "xml";

    private TableModel tableModel;

    public FileWorker(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void openFile() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("xml", EXTENSION));
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            openXMLFile(fc.getSelectedFile().getPath());
        }
    }

    public void saveFile() {
        try {
            JFileChooser fc = new JFileChooser();
            System.out.println("fsfs");
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                XMLOutputFactory output = XMLOutputFactory.newInstance();
                System.out.println("fsfs");
                XMLStreamWriter writer = output.createXMLStreamWriter
                        (new FileWriter(fc.getSelectedFile() + "." + EXTENSION));
                writer.writeStartDocument(FORMAT, VER);
                System.out.println("fsfs");
                writer.writeStartElement(STUDENTS);
//                writer.writeEndElement();
                for (Student student : tableModel.getStudents()) {
                    System.out.println("fsfs");
                    writer.writeStartElement(STUDENT);
                    writer.writeAttribute(LAST_NAME, student.getLastName());
                    writer.writeAttribute(FIRST_NAME, student.getFirstName());
                    writer.writeAttribute(FATHER_NAME, student.getMiddleName());
                    writer.writeAttribute(GROUP, student.getGroupNumber());
                    for (String socialWork : student.getSocialWork()) {
                        System.out.println("ofo");
                        writer.writeStartElement(SOCAIL_WORK);
                        writer.writeCharacters(socialWork);
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                }
                System.out.println("ofdfo");
                writer.writeEndElement();
                writer.writeEndDocument();
                writer.flush();
            }
        } catch (Exception eSave) {
            JOptionPane.showMessageDialog
                    (null, "Can't save file", "ERROR", JOptionPane.ERROR_MESSAGE | JOptionPane.OK_OPTION);
        }
    }

    public void openXMLFile(String fileName) {
//        try {
//            String name;
//            String mark;
//            String lastName = "";
//            String firstName = "";
//            String middleName = "";
//            String group = "";
//            String numberExam = "";
//            tableModel.getStudents().clear();
//            List<String> socialWork = new ArrayList<String>();
//            XMLStreamReader xmlr = XMLInputFactory.newInstance()
//                    .createXMLStreamReader(fileName, new FileInputStream(fileName));
//            System.out.println("ds");
//            while (xmlr.hasNext()) {
//                System.out.println("ds");
//                xmlr.next();
//                if (xmlr.isStartElement()) {
//                    if (xmlr.getLocalName().equals(STUDENT)) {
//                        socialWork = new ArrayList<String>();
//                        lastName = xmlr.getAttributeValue(null, LAST_NAME);
//                        firstName = xmlr.getAttributeValue(null, FIRST_NAME);
//                        middleName = xmlr.getAttributeValue(null, FATHER_NAME);
//                        group = xmlr.getAttributeValue(null, GROUP);
//                        System.out.println("ds");
//                        xmlr.next();
//                    } else if (xmlr.getLocalName().equals(SOCAIL_WORK)) {
//                        System.out.println("ds1");
//                        socialWork.add(xmlr.getAttributeValue(null, SOCAIL_WORK));
//                    } else if (xmlr.isEndElement()) {
//                        System.out.println("ds2");
//                        if (xmlr.getLocalName().equals(STUDENT)) {
//                            tableModel.getStudents().add(new Student(lastName, firstName, middleName, group, socialWork));
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//            JOptionPane.showMessageDialog
//                    (null, "Can't open file", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
        try {
            String name;
            String mark;
            String lastName = "";
            String firstName = "";
            String middleName = "";
            String group = "";
            String numberExam = "";
            tableModel.getStudents().clear();
            List<String> socialWork = new ArrayList<String>();
            XMLStreamReader xmlr = XMLInputFactory.newInstance()
                    .createXMLStreamReader(fileName, new FileInputStream(fileName));
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement()) {
                    if (xmlr.getLocalName().equals(STUDENT)){
                        socialWork = new ArrayList<String>();
                        lastName = xmlr.getAttributeValue(null, LAST_NAME);
                        firstName = xmlr.getAttributeValue(null, FIRST_NAME);
                        middleName = xmlr.getAttributeValue(null, FATHER_NAME);
                        group = xmlr.getAttributeValue(null, GROUP);
                    }
                    else
                        if (xmlr.getLocalName().equals(SOCAIL_WORK)){
                        xmlr.next();
                        socialWork.add(xmlr.getText());
                    }
                } else if (xmlr.isEndElement()) {
                    if (xmlr.getLocalName().equals(STUDENT)){
                        tableModel.getStudents().add(new Student(lastName, firstName, middleName, group, socialWork));
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Can't open file", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
