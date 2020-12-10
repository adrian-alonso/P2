package p2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import org.xml.sax.SAXException;
import org.w3c.dom.*;
import java.io.IOException;

public class Sint101P2 extends HttpServlet {
  final static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  final static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  final static String xsd_url = "/p2/eaml.xsd";
  //static String xml_url = "/p2/teleco.xml";
  static String xml_url = "http://gssi.det.uvigo.es/users/agil/public_html/SINT/20-21/teleco.xml";
  static File xsd;
  static File xml;
  //Lista de documentos validos
  static HashMap<String,Document> docsMap = new HashMap<String, Document>();
  //Listas de warnings, errores y errores fatales
  static ArrayList<WarningFile> warningsFiles = new ArrayList<WarningFile>();
  static ArrayList<ErrorFile> errorsFiles = new ArrayList<ErrorFile>();
  static ArrayList<FatalErrorFile> fatalErrorsFiles = new ArrayList<FatalErrorFile>();


  public void init (ServletConfig config) throws ServletException {
    try {
      ServletContext servletcontext= config.getServletContext();
      xsd = new File(servletcontext.getRealPath(xsd_url));
      //xml = new File(servletcontext.getRealPath(xml_url));
      xml = new File(xml_url);

      //Llamo al parser
      Parser eamlParser = new Parser();
      //docsMap = eamlParser.parser(servletcontext.getRealPath(xml_url), servletcontext.getRealPath(xsd_url), servletcontext);
      docsMap = eamlParser.parser(xml_url, servletcontext.getRealPath(xsd_url), servletcontext);

      //Obtengo avisos
      warningsFiles = eamlParser.getWarningsFiles();
      Collections.sort(warningsFiles);
      //Obtengo errores
      errorsFiles = eamlParser.getErrorsFiles();
      Collections.sort(errorsFiles);
      //Obtengo errores fatales
      fatalErrorsFiles = eamlParser.getFatalErrorsFiles();
      Collections.sort(fatalErrorsFiles);

    } catch(Exception e) {

    }
  }

  public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    //Obtenemos los parametros introducidos en la url
    String pphase = req.getParameter("pphase");
    String pdegree = req.getParameter("pdegree");
    String psubject = req.getParameter("psubject");
    String password = req.getParameter("p");
    String auto = req.getParameter("auto");

    //EAMLlists eamlLists = new EAMLlists(xml, docsMap);
    FrontEnd screen = new FrontEnd();

    //Comprobamos si hay contraseña y si es correcta
    //En caso afirmativo seleccionamos la fase a la que se quiera acceder
    if (password == null) {
      if(auto==null){
        screen.noPasswordHTML(req, res);
      } else if (!auto.equals("true")) {
        screen.noPasswordHTML(req, res);
      } else {
        screen.noPasswordXML(req, res);
      }
    } else if (!password.equals("Aadri4n999")) {
      if(auto==null){
        screen.badPasswordHTML(req, res);
      } else if (!auto.equals("true")) {
        screen.badPasswordHTML(req, res);
      } else {
        screen.badPasswordXML(req, res);
      }
    } else {

      if (pphase == null) {
        screen.phase01(req, res, pphase);
      } else {
        switch (pphase.trim()) {
          case "01":
            screen.phase01(req, res, pphase);
            break;

         case "02":
           screen.phase02(req, res, pphase, warningsFiles, errorsFiles, fatalErrorsFiles);
           break;

         case "11":
           ArrayList<String> degrees = getC1Degrees();
           screen.phase11(req, res, pphase, degrees);
           break;

         case "12":
           ArrayList<Subject> subjects = getC1Subjects(pdegree);
           screen.phase12(req, res, pphase, pdegree, subjects);
           break;

         case "13":
           ArrayList<Student> students = getC1Students(pdegree, psubject);
           screen.phase13(req, res, pphase, pdegree, psubject, students);
           break;

         default:
           screen.phase01(req, res, pphase);
           break;
        }
      }
    }
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

    try {

      Document doc = docsMap.get(degree);

      //Obtenemos los elementos
      XPathFactory xpathfactory = XPathFactory.newInstance();
      XPath xpath = xpathfactory.newXPath();

      //Nombre
      String studentsEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]//Student";
      NodeList students = (NodeList)xpath.evaluate(studentsEXP, doc, XPathConstants.NODESET);

      for (int i = 0; i < students.getLength(); i++) {
        //Estudiante
        Element student = (Element)students.item(i);

        //Nombre
        String nameEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Name";
        NodeList names = (NodeList)xpath.evaluate(nameEXP, doc, XPathConstants.NODESET);
        String studentName = ((Element)names.item(i)).getTextContent().trim();

        //DNI or Resident
        String idEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Dni | /Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Resident";
        NodeList ids = (NodeList)xpath.evaluate(idEXP, doc, XPathConstants.NODESET);
        String studentID = ((Element)ids.item(i)).getTextContent().trim();

        //Grade
        String gradeEXP = "/Degree/Course/Subject[Name=\"" + subject + "\"]/Student/Grade";
        NodeList grades = (NodeList)xpath.evaluate(gradeEXP, doc, XPathConstants.NODESET);
        float studentGrade = Float.parseFloat(((Element)grades.item(i)).getTextContent().trim());

        //Address
        Node studentNode = students.item(i);
        NodeList studentChildNodes = studentNode.getChildNodes();

        String address = null;
        String textLine = null;
        for (int j = 0; j < studentChildNodes.getLength(); j++) {
          Node addressNode = studentChildNodes.item(j);

          if (addressNode.getNodeType() == Node.TEXT_NODE) {
            if (addressNode.getNodeValue() != null) {
              textLine = addressNode.getNodeValue();
              textLine = textLine.trim();
              if (!textLine.equals("")) {
                address = textLine;
              }
            }
          }

        }

        studentsList.add(new Student(studentName, studentID, studentGrade, address));
      }

    } catch(Exception e) {

    }

    Collections.sort(studentsList);
    return studentsList;
  }
}
