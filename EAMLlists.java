package p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.io.File;
import javax.xml.xpath.*;
import org.w3c.dom.*;

public class EAMLlists {
  HashMap<String,Document> docsMap = new HashMap<String,Document>();
  File file;
  int number = 0;

  //CONSTRUCTOR
  //Constructor por defecto
  public EAMLlists() {
  }

  //Constructor comun
  public EAMLlists(File file, HashMap<String,Document> docsMap) {
    this.file = file;
    this.docsMap = docsMap;
  }

  public ArrayList<String> getC1Degrees() {
    ArrayList<String> docsList = new ArrayList<String>();
    for (String key : docsMap.keySet()) {
      docsList.add(key);
    }
    //Lista en orden alfabetico
    Collections.sort(docsList);
    return docsList;
  }

  public ArrayList<Subject> getC1Subjects(String degree) {
    ArrayList<Subject> subjectsList = new ArrayList<Subject>();

    try {
      Document doc = docsMap.get(degree);

      //Obtenemos el NodeList de los cursos
      Element coursesNode = doc.getDocumentElement();
      NodeList courses = coursesNode.getElementsByTagName("Course");

      for (int i = 0; i < courses.getLength(); i++) {
        //Curso
        Element course = (Element)courses.item(i);

        //Obtenemos el curso
        int courseNumber = Integer.parseInt(course.getAttribute("number"));

        //Obtenemos el NodeList de las asignaturas
        NodeList subjects = course.getElementsByTagName("Subject");

        //Obtenemos los datos de todas las asignaturas
        for (int j = 0; j < subjects.getLength(); j++) {
          Element subject = (Element)subjects.item(j);

          //Obtenemos los atributos
          String idSub = subject.getAttribute("idSub");
          String type = subject.getAttribute("type");

          //Obtenemos los elementos
          XPathFactory xpathfactory = XPathFactory.newInstance();
          XPath xpath = xpathfactory.newXPath();

          //Nombre
          String nameEXP = "/Degree/Course/Subject[@idSub=\"" + idSub + "\"]/Name";
          NodeList names = (NodeList)xpath.evaluate(nameEXP, doc, XPathConstants.NODESET);
          String subjectName = ((Element)names.item(0)).getTextContent().trim();

          subjectsList.add(new Subject(subjectName, courseNumber, type, idSub));
        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }

    //Ordenar
    return subjectsList;
  }

  public ArrayList<Student> getC1Students(String degree, String subject) {
    ArrayList<Student> studentsList = new ArrayList<Student>();

    number = 1;
    try {

      Document doc = docsMap.get(degree);

      //Obtenemos los elementos
      XPathFactory xpathfactory = XPathFactory.newInstance();
      XPath xpath = xpathfactory.newXPath();

      //Nombre
      String studentsEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]//Student";
      NodeList students = (NodeList)xpath.evaluate(studentsEXP, doc, XPathConstants.NODESET);

      number = 2;
      for (int i = 0; i < students.getLength(); i++) {
        //Estudiante
        Element student = (Element)students.item(i);

        // //Obtenemos los elementos de estudiante
        // XPathFactory xpathfactory = XPathFactory.newInstance();
        // XPath xpath = xpathfactory.newXPath();

        //Nombre
        String nameEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Name";
        NodeList names = (NodeList)xpath.evaluate(nameEXP, doc, XPathConstants.NODESET);
        String studentName = ((Element)names.item(0)).getTextContent().trim();

        number = 21;
        //DNI or Resident
        String idEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Dni | /Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Resident";
        NodeList ids = (NodeList)xpath.evaluate(idEXP, doc, XPathConstants.NODESET);
        String studentID = ((Element)ids.item(0)).getTextContent().trim();

        number = 22;
        //Grade
        String gradeEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Grade";
        NodeList grades = (NodeList)xpath.evaluate(gradeEXP, doc, XPathConstants.NODESET);
        int studentGrade = Integer.parseInt(((Element)grades.item(0)).getTextContent().trim());
        //int studentGrade = Integer.parseInt(((Element)grades.item(0)).getTextContent());

        number = 3;
        studentsList.add(new Student(studentName, studentID, studentGrade));
      }

    } catch(Exception e) {

    }

    Collections.sort(studentsList);
    return studentsList;
  }
}
