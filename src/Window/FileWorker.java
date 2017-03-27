package Window;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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
    private static final String EXTENSION = "xml";

    private TableModel tableModel;

    public FileWorker(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("xml", EXTENSION));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                tableModel.getStudents().clear();
                DefaultHandler handler = new DefaultHandler() {
                    private static final String LAST_NAME = "last_name";
                    private static final String FIRST_NAME = "first_name";
                    private static final String FATHER_NAME = "father_name";
                    private static final String GROUP = "group";
                    private static final String SOCAIL_WORK = "social_work";
                    private static final String STUDENT = "student";
                    private String lastName = "";
                    private String firstName = "";
                    private String fatherName = "";
                    private String group = "";
                    private List<String> socialWork = new ArrayList<String>();

                    boolean isSocialWork = false;

                    @Override
                    public void startElement(String uri,
                                             String localName, String qName, Attributes attributes)
                            throws SAXException {
                        if (qName.equalsIgnoreCase(STUDENT)) {
                            lastName=attributes.getValue(LAST_NAME);
                            firstName=attributes.getValue(FIRST_NAME);
                            fatherName=attributes.getValue(FATHER_NAME);
                            group=attributes.getValue(GROUP);
                        } else if (qName.equalsIgnoreCase(SOCAIL_WORK)) {
                            isSocialWork = true;
                        }
                    }

                    @Override
                    public void endElement(String uri,
                                           String localName, String qName) throws SAXException {
                        if(socialWork.size()==tableModel.SEMESTER_NUMBER){
                            tableModel.getStudents().add(new Student(lastName, firstName, fatherName, group, new ArrayList<String>(socialWork) ));
                            socialWork.clear();
                        }
                    }

                    @Override
                    public void characters(char ch[],
                                           int start, int length) throws SAXException {
                        if (isSocialWork) {
                            socialWork.add(new String(ch, start, length));
                            isSocialWork = false;
                        }
                    }

                };
                saxParser.parse(new File(fileChooser.getSelectedFile().getPath()), handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        try {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement(STUDENTS);
                doc.appendChild(rootElement);

                for (Student student : tableModel.getStudents()) {
                    Element studentEl = doc.createElement(STUDENT);
                    rootElement.appendChild(studentEl);

                    Attr attr = doc.createAttribute(FIRST_NAME);
                    attr.setValue(student.getFirstName());
                    studentEl.setAttributeNode(attr);

                    attr = doc.createAttribute(LAST_NAME);
                    attr.setValue(student.getLastName());
                    studentEl.setAttributeNode(attr);

                    attr = doc.createAttribute(FATHER_NAME);
                    attr.setValue(student.getFatherName());
                    studentEl.setAttributeNode(attr);

                    attr = doc.createAttribute(GROUP);
                    attr.setValue(student.getGroupNumber());
                    studentEl.setAttributeNode(attr);


                    for (String socialWork : student.getSocialWork()) {
                        Element firstname = doc.createElement(SOCAIL_WORK);
                        firstname.appendChild(doc.createTextNode(socialWork));
                        studentEl.appendChild(firstname);
                    }

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(fc.getSelectedFile() + "." + EXTENSION));
                    transformer.transform(source, result);

                }
            }
        } catch (ParserConfigurationException pce) {
            JOptionPane.showMessageDialog(null, "Не получилось сохранить файл", "Ошибка", JOptionPane.ERROR_MESSAGE | JOptionPane.OK_OPTION);
        } catch (TransformerException tfe) {
            JOptionPane.showMessageDialog(null, "Не получилось сохранить файл", "Ошибка", JOptionPane.ERROR_MESSAGE | JOptionPane.OK_OPTION);
        }
    }

}
