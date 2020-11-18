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
  // File fileSchema= new File(“tvml.xsd”);   // ¿cuálesla rutacompleta?
  // dbf.setAttribute(JAXP_SCHEMA_SOURCE, fileSchema);
  // DocumentBuilderdb= dbf.newDocumentBuilder();
  // TVML_ErrorHandlererrorHandler= new TVML_ErrorHandler();
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
    String pphase = req.getParameter("pphase");
    String pdegree = req.getParameter("pdegree");
    String psubject = req.getParameter("psubject");
    String password = req.getParameter("p");
    String auto = req.getParameter("auto");
    FrontEnd checkParameters = new FrontEnd();
    // Page01 page01 = new Page01();
    // Page02 page02 = new Page02();
    // Page11 page11 = new Page11();
    // Page12 page12 = new Page12();
    // Page13 page13 = new Page13();

    //Comprobamos si hay contraseña y si es correcta
    //En caso afirmativo sleccionamos la fase a la que se quiera acceder
    if (password == null) {
      if(auto==null){
        checkParameters.noPasswordHTML(req, res);
      } else if (!auto.equals("true")) {
        checkParameters.noPasswordHTML(req, res);
      } else {
        checkParameters.noPasswordXML(req, res);
      }
    } else if (!password.equals("Aadri4n999")) {
      if(auto==null){
        checkParameters.badPasswordHTML(req, res);
      } else if (!auto.equals("true")) {
        checkParameters.badPasswordHTML(req, res);
      } else {
        checkParameters.badPasswordXML(req, res);
      }
    } else {

      if (pphase == null) {
        checkParameters.phase01(req, res, pphase);
        // page01.phase01(req, res, pphase);
      } else {
        switch (pphase.trim()) {
          case "01":
            checkParameters.phase01(req, res, pphase);
            // page01.phase01(req, res, pphase);
            break;

         case "02":
           // page02.phase02(req, res, pphase);
           checkParameters.phase02(req, res, pphase);
           break;

         case "11":
           // page11.phase11(req, res, pphase);
           checkParameters.phase11(req, res, pphase);
           break;

         case "12":
           if (pdegree == null) {
             if(auto==null){
               checkParameters.noParamHTML(req, res, "pdegree");
             } else if (!auto.equals("true")) {
               checkParameters.noParamHTML(req, res, "pdegree");
             } else {
               checkParameters.noParamXML(req, res, "pdegree");
             }
           } else {
             // page12.phase12(req, res, pphase, pdegree);
             checkParameters.phase12(req, res, pphase, pdegree);
           }
           break;

         case "13":
           if (pdegree == null) {
             if(auto==null){
               checkParameters.noParamHTML(req, res, "pdegree");
             } else if (!auto.equals("true")) {
               checkParameters.noParamHTML(req, res, "pdegree");
             } else {
               checkParameters.noParamXML(req, res, "pdegree");
             }
           } else {
             if (psubject == null){
               if(auto==null){
                 checkParameters.noParamHTML(req, res, "psubject");
               } else if (!auto.equals("true")) {
                 checkParameters.noParamHTML(req, res, "psubject");
               } else {
                 checkParameters.noParamXML(req, res, "psubject");
               }
             } else {
               // page13.phase13(req, res, pphase, pdegree, psubject);
               checkParameters.phase13(req, res, pphase, pdegree, psubject);
             }
           }
           break;

         default:
           // page01.phase01(req, res, pphase);
           checkParameters.phase01(req, res, pphase);
           break;
        }
      }
    }
  }

}
