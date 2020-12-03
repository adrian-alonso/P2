package p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.servlet.*;

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
  public HashMap<String,Document> parser(String file_xml, String file_xsd, ServletContext servletcontext) {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(true);
    dbf.setNamespaceAware(true);

    dbf.setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);
    dbf.setAttribute(JAXP_SCHEMA_SOURCE,file_xsd);

    DocumentBuilder db = null;
    try {
      db  = dbf.newDocumentBuilder();
    } catch(ParserConfigurationException pce) {
    }

    boolean moreEAML = searchEAML(file_xml, db, servletcontext);
    while (moreEAML == true) {
      moreEAML = searchEAML(nextFile, db, servletcontext);
    }

    return docsMap;
  }

  public boolean searchEAML(String file, DocumentBuilder db, ServletContext servletcontext) {
    File eamlFile = new File(file);
    boolean moreFiles = false;

    //Llama gestor de errores
    EAML_ErrorHandler eaml_ErrorHandler = new EAML_ErrorHandler();
    db.setErrorHandler(eaml_ErrorHandler);

    Document doc = null;
    try {
      doc = db.parse(eamlFile);
    } catch(SAXException saxe) {
      System.out.println("1: " + saxe);
    } catch (IOException ioe) {
    }

    //Obtenemos archivos EAML
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();
    String exp = "//EAML";

    String degree = null;
    try {

      //Obtenemos el grado
      NodeList degreenode = doc.getElementsByTagName("Name");
      degree = ((Element)degreenode.item(0)).getTextContent();
      //Obtenemos los nodos eaml
      NodeList eamlnodes = (NodeList)xpath.evaluate(exp, doc, XPathConstants.NODESET);
      filesList.add(file);

      //Buscamos mas ficheros EAML
      for (int i = 0; i < eamlnodes.getLength(); i++) {
        String nextFile_url = ((Element)eamlnodes.item(i)).getTextContent();
        nextFile = servletcontext.getRealPath(nextFile_url);
        if (!filesList.contains(nextFile)) {
            moreFiles = true;
        }
      }

    } catch(NullPointerException npe) {
    } catch (XPathExpressionException xpe_e) {
    }

    //En caso de warnings
    if (eaml_ErrorHandler.getWarning() == 1) {
      WarningFile warning = new WarningFile(file, eaml_ErrorHandler.getWarningList());
      boolean anywarning = false;
      for (int i = 0; i < warningsFiles.size(); i++) {
        if (warningsFiles.get(i).getWarningID().equals(warning.getWarningID())) {
          anywarning = true;
        }
      }
      System.out.println(anywarning);
      if (!anywarning) {
        warningsFiles.add(warning);
      }
    }

    //En caso de errores
    if (eaml_ErrorHandler.getError() == 1) {
      ErrorFile error = new ErrorFile(file, eaml_ErrorHandler.getErrorList());
      System.out.println(eaml_ErrorHandler.getErrorList());
      boolean anyerror = false;
      for (int i = 0; i < errorsFiles.size(); i++) {
        if (errorsFiles.get(i).getErrorID().equals(error.getErrorID())) {
          anyerror = true;
        }
      }
      System.out.println(anyerror);
      if (!anyerror) {
        errorsFiles.add(error);
        System.out.println(errorsFiles);
        System.out.println(error.getErrors());
      }
    }

    //En caso de errores fatales
    if (eaml_ErrorHandler.getFatalError() == 1) {
      FatalErrorFile fatalerror = new FatalErrorFile(file, eaml_ErrorHandler.getFatalErrorList());
      boolean anyfatalerror = false;
      for (int i = 0; i < fatalErrorsFiles.size(); i++) {
        if (fatalErrorsFiles.get(i).getFatalErrorID().equals(fatalerror.getFatalErrorID())) {
          anyfatalerror = true;
        }
      }
      System.out.println(anyfatalerror);
      if (!anyfatalerror) {
        fatalErrorsFiles.add(fatalerror);
        System.out.println(fatalErrorsFiles);
      }
    }

    //En caso de que el fichero este correcto
    if ((eaml_ErrorHandler.getWarning() == 0) && (eaml_ErrorHandler.getError() == 0) && (eaml_ErrorHandler.getFatalError() == 0)) {
      if ((degree != null) && (!(docsMap.containsKey(degree)))) {
        docsMap.put(degree,doc);
      }
    }

    return moreFiles;
  }

  //METODOS PARA OBTENER LAS LISTAS
  public ArrayList<WarningFile> getWarningsFiles() {
    return warningsFiles;
  }

  public ArrayList<ErrorFile> getErrorsFiles() {
    return errorsFiles;
  }

  public ArrayList<FatalErrorFile> getFatalErrorsFiles() {
    return fatalErrorsFiles;
  }
}
