
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class Parser {
  String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  public static void main (String[] args) {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(true);
    dbf.setNamespaceAware(true);
    dbf.setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);
    File fileSchema = newFile(“eaml.xsd”);
    dbf.setAttribute(JAXP_SCHEMA_SOURCE,fileSchema);
    DocumentBuilder db = dbf.newDocumentBuilder();
    db.setErrorHandler(newXML_ErrorHandler());
    try{
      //Document doc = db.parse(fichero|URL);
      //Document doc = db.parse("teleco.xml"|??);      ¡¡¡¡¡IMPORTANTE!!!!!!!
    }catch(SAXExceptione){
      /*provocada por documento no well-formed*/
    }//aquí deberíamos comprobar que no ha entrado el gestor de errores
  }
}


}
