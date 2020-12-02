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
    ErrorHandler eamlErrorHandler = new ErrorHandler();
    db.setErrorHandler(eamlErrorHandler);

    Document doc = null;
    try {
      doc = db.parse(eamlFile);
    } catch(SAXException saxe) {
      System.out.println("1: " + saxe);
      //eamlErrorHandler.warning()
    } catch (IOException ioe) {
      System.out.println("2: " + ioe);
    }

    //Obtenemos archivos EAML
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();
    String exp = "//EAML";

    System.out.println(eamlErrorHandler.getWarning() + " " + eamlErrorHandler.getError() + " " + eamlErrorHandler.getFatalError());
    String degree = null;
    try {

      //Obtenemos el grado
      NodeList degreenode = doc.getElementsByTagName("Name");
      degree = ((Element)degreenode.item(0)).getTextContent();
      System.out.println('1');
      //Obtenemos los nodos eaml
      NodeList eamlnodes = (NodeList)xpath.evaluate(exp, doc, XPathConstants.NODESET);
      filesList.add(file);
      System.out.println('2');

      //Buscamos mas ficheros EAML
      for (int i = 0; i < eamlnodes.getLength(); i++) {
        System.out.println('3');
        String nextFile_url = ((Element)eamlnodes.item(i)).getTextContent();
        nextFile = servletcontext.getRealPath(nextFile_url);
        System.out.println('4');
        if (!filesList.contains(nextFile)) {
            moreFiles = true;
            System.out.println('5');
        }
      }
      System.out.println('6');

    } catch(NullPointerException npe) {
      System.out.println("3: " + npe);
    } catch (XPathExpressionException xpe_e) {
      System.out.println("4: " + xpe_e);
    }

    System.out.println(eamlErrorHandler.getWarning() + " " + eamlErrorHandler.getError() + " " + eamlErrorHandler.getFatalError());
    //En caso de warnings
    if (eamlErrorHandler.getWarning() == 1) {
      WarningFile warning = new WarningFile(file, eamlErrorHandler.getWarningList());
      boolean anywarning = false;
      for (int i = 0; i < warningsFiles.size(); i++) {
        if (warningsFiles.get(i).getWarningID().equals(warning.getWarningID())) {
          anywarning = true;
        }
      }
      System.out.println(anywarning);
      if (!anywarning) {
        warningsFiles.add(warning);
        System.out.println(warningsFiles);
      }
    }

    //En caso de errores
    if (eamlErrorHandler.getError() == 1) {
      ErrorFile error = new ErrorFile(file, eamlErrorHandler.getErrorList());
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
      }
    }

    //En caso de errores fatales
    if (eamlErrorHandler.getFatalError() == 1) {
      FatalErrorFile fatalerror = new FatalErrorFile(file, eamlErrorHandler.getFatalErrorList());
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
    if ((eamlErrorHandler.getWarning() == 0) && (eamlErrorHandler.getError() == 0) && (eamlErrorHandler.getFatalError() == 0)) {
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
