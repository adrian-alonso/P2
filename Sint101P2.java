package p2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;

public class Sint101P2 extends HttpServlet {
  final static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  final static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  final static String xsd_url = "/p2/eaml.xsd";
  static String xml_url = "/p2/teleco.xml";
  static File xsd;
  static File xml;
  //Lista de documentos validos
  static HashMap<String,Document> docsMap = new HashMap<String, Document>();
  //Listas de warnings, errores y errores fatales
  static ArrayList<WarningFile> warningsFiles = new ArrayList<WarningFile>();
  static ArrayList<ErrorFile> errorsFiles = new ArrayList<ErrorFile>();
  static ArrayList<FatalErrorFile> fatalErrorsFiles = new ArrayList<FatalErrorFile>();

  public int number;


  public void init (ServletConfig config) throws ServletException {
    try {
      number = 0;
      ServletContext servletcontext= config.getServletContext();
      xsd = new File(servletcontext.getRealPath(xsd_url));
      xml = new File(servletcontext.getRealPath(xml_url));

      //Llamo al parser
      Parser eamlParser = new Parser();
      docsMap = eamlParser.parser(servletcontext.getRealPath(xml_url), servletcontext.getRealPath(xsd_url));
      number = eamlParser.number;

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

    EAMLlists eamlLists = new EAMLlists(xml, docsMap);
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
        screen.phase01(req, res, pphase, number, docsMap);
      } else {
        switch (pphase.trim()) {
          case "01":
            screen.phase01(req, res, pphase, number, docsMap);
            break;

         case "02":
           screen.phase02(req, res, pphase, warningsFiles, errorsFiles, fatalErrorsFiles);
           break;

         case "11":
           ArrayList<String> degrees = eamlLists.getC1Degrees();
           screen.phase11(req, res, pphase, degrees);
           break;

         case "12":
           screen.phase12(req, res, pphase, pdegree);
           break;

         case "13":
           screen.phase13(req, res, pphase, pdegree, psubject);
           break;

         default:
           screen.phase01(req, res, pphase, number, docsMap);
           break;
        }
      }
    }
  }

}
