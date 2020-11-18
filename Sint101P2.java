package p2;

import java.io.*;
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
  //private final static String PASS = "";
  final static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  final static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  // Document BuilderFactorydbf= DocumentBuilderFactory.newInstance();
  // dbf.setValidating(true);     /* factoríade parsers validadores*/
  // dbf.setNamespaceAware(true);
  // dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
  // File fileSchema= new File(“eaml.xsd”);   // ¿cuál es la ruta completa?
  // dbf.setAttribute(JAXP_SCHEMA_SOURCE, fileSchema);
  // DocumentBuilderdb= dbf.newDocumentBuilder();
  // EAML_ErrorHandlererrorHandler= new EAML_ErrorHandler();
  // db.setErrorHandler(errorHandler);
  // Document doc = db.parse(URL o fichero);

  //WARNINGS Y ERRORES
  // static ArrayList<ErrorFile> FileWithErrors = new ArrayList<ErrorFile>();
  // static ArrayList<WarningFile> FileWithErrors = new ArrayList<WarningFile>();
  // static ArrayList<FatalErrorFile> FileWithErrors = new ArrayList<FatalErrorFile>();


  public void init (ServletConfig config) throws ServletException {
    try {

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
           screen.phase02(req, res, pphase);
           break;

         case "11":
           screen.phase11(req, res, pphase);
           break;

         case "12":
           screen.phase12(req, res, pphase, pdegree);
           break;

         case "13":
           screen.phase13(req, res, pphase, pdegree, psubject);
           break;

         default:
           screen.phase01(req, res, pphase);
           break;
        }
      }
    }
  }

}
