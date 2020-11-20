package p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class Parser {
  String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  String nextFile;
  ArrayList<String> filesList = new ArrayList<String>();
  HashMap<String,Document> docsMap = new HashMap<String,Document>();

  //Listas para guardar los errores de las excepciones
  ArrayList<WarningFile> warningsFiles = new ArrayList<WarningFile>();
  ArrayList<ErrorFile> errorsFiles = new ArrayList<ErrorFile>();
  ArrayList<FatalErrorFile> fatalErrorsFiles = new ArrayList<FatalErrorFile>();

  //CONSTRUCTOR
  public Parser() {
  }

  //METODOS
  public HashMap<String,Document> parser(String file) {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(true);
    dbf.setNamespaceAware(true);

    dbf.setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);
    dbf.setAttribute(JAXP_SCHEMA_SOURCE,file);

    DocumentBuilder db = null;
    try {
      db  = dbf.newDocumentBuilder();
    } catch(ParserConfigurationException pce) {
    }

    boolean moreEAML = searchEAML(file, db);
    while (moreEAML == true) {
      moreEAML = searchEAML(nextFile, db);
    }

    return docsMap;
  }

  public boolean searchEAML(String file, DocumentBuilder db) {
    File eamlFile = new File(file);

    //Llama gestor de errores
    ErrorHandler eamlErrorHandler = new ErrorHandler();
    db.setErrorHandler(eamlErrorHandler);

    Document doc = null;
    try {
      doc = db.parse(eamlFile);
    } catch(SAXException saxe) {
    } catch (IOException ioe) {
    }

    //Obtenemos archivos EAML
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();
    String exp = "//EAML";

    String degree;
    try {

      //Obtenemos el grado
      NodeList degreenode = doc.getElementsByTagName("Degree");
      degree = ((Element)degreenode.item(0)).getTextContent();

      //Obtenemos los nodos eaml
      NodeList eamlnodes = (NodeList)xpath.evaluate(exp, doc, XPathConstants.NODESET);
      filesList.add(file);

      //Buscamos mas ficheros EAML
      for (int i = 0; i < eamlnodes.getLength(); i++) {
        nextFile = eamlnodes.item(i).getTextContent();
        if (!filesList.contains(nextFile)) {
            return true;
        }
      }

    } catch(NullPointerException npe) {

    } catch (XPathExpressionException xpe_e) {

    }

    //En caso de warnings

    //En caso de errores
    if (eamlErrorHandler.getError() == 1) {
      ErrorFile error = new ErrorFile(file, eamlErrorHandler.getErrorList());
      boolean anyerror = false;
      //for
      if (!anyerror) {
        errorsFiles.add(error);
      }
    }


    //En caso de errores fatales


    return false;
  }
}
