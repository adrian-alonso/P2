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
    String passwd = req.getParameter("p");
    String pdegree = req.getParameter("pdegree");
    String psubject = req.getParameter("psubject");
    String auto = req.getParameter("auto");
    Page01 page01 = new Page01();
    Page02 page02 = new Page02();
    Page11 page11 = new Page11();
    Page12 page12 = new Page12();
    Page13 page13 = new Page13();

    //Seleccionamos la fase a la que se quiera acceder
    if (pphase == null) {
      page01.phase01(req, res, auto, pphase);
    } else {
      switch (pphase.trim()) {
        case "01":
          page01.phase01(req, res, auto, pphase);
          break;

       case "02":
         page02.phase02(req, res, auto, pphase);
         break;

       case "11":
         page11.phase11(req, res, auto, pphase);
         break;

       case "12":
         page12.phase12(req, res, auto, pphase, pdegree);
         break;

       case "13":
         page13.phase13(req, res, auto, pphase, pdegree, psubject);
         break;

       default:
         page01.phase01(req, res, auto, pphase);
         break;

      }
    }
  }

}
