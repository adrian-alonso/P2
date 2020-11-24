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

  int number;

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

    number = 1;
    Document doc = null;
    docsMap.put(Integer.toString(number),doc);
    try {
      doc = db.parse(eamlFile);
      number = 2;
      docsMap.put(Integer.toString(number),doc);
    } catch(SAXException saxe) {
    } catch (IOException ioe) {
    }

    //Obtenemos archivos EAML
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();
    String exp = "//EAML";

    String degree = null;
    try {
      number = 3;

      docsMap.put(Integer.toString(number),doc);
      //Obtenemos el grado
      NodeList degreenode = doc.getElementsByTagName("Name");
      docsMap.put(((Element)degreenode.item(0)).getTextContent(),doc);
      //NodeList degreenode = (NodeList)xpath.evaluate("//Degree",doc,XPathConstants.NODESET);
      degree = ((Element)degreenode.item(0)).getTextContent();
      docsMap.put(degree,doc);
      //degree = ((Element)degreenode.item(0)).getElementsByTagName("Name");

      number = 4;
      docsMap.put(Integer.toString(number),doc);
      //Obtenemos los nodos eaml
      NodeList eamlnodes = (NodeList)xpath.evaluate(exp, doc, XPathConstants.NODESET);
      filesList.add(file);

      number = 5;
      docsMap.put(Integer.toString(number),doc);
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
    if (eamlErrorHandler.getWarning() == 1) {
      WarningFile warning = new WarningFile(file, eamlErrorHandler.getWarningList());
      boolean anywarning = false;
      for (int i = 0; i < warningsFiles.size(); i++) {
        if (warningsFiles.get(i).getWarningID().equals(warning.getWarningID())) {
          anywarning = true;
        }
      }
      if (!anywarning) {
        warningsFiles.add(warning);
      }
    }

    //En caso de errores
    if (eamlErrorHandler.getError() == 1) {
      ErrorFile error = new ErrorFile(file, eamlErrorHandler.getErrorList());
      boolean anyerror = false;
      System.out.println("ID: " + error.toString() + " Error: " + eamlErrorHandler.getErrorList());
      for (int i = 0; i < errorsFiles.size(); i++) {
        if (errorsFiles.get(i).getErrorID().equals(error.getErrorID())) {
          anyerror = true;
        }
      }
      if (!anyerror) {
        errorsFiles.add(error);
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
      if (!anyfatalerror) {
        fatalErrorsFiles.add(fatalerror);
      }
    }

    //En caso de que el fichero este correcto
    //if ((eamlErrorHandler.getWarning() == 0) && (eamlErrorHandler.getError() == 0) && (eamlErrorHandler.getFatalError() == 0)) {
      number = 600;
      docsMap.put(Integer.toString(number),doc);
      if ((degree != null) && (!(docsMap.containsKey(degree)))) {
        docsMap.put(degree,doc);
        number = 700;
      }
    //}

    return false;
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
