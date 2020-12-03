package p2;

import java.util.ArrayList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

public class EAML_ErrorHandler extends DefaultHandler {
    //Contadores para el numero de excepciones
    int error = 0;
    int warning = 0;
    int fatalError = 0;

    //Listas para guardar los errores de las excepciones
    ArrayList<String> warningList = new ArrayList<String>();
    ArrayList<String> errorList = new ArrayList<String>();
    ArrayList<String> fatalErrorList = new ArrayList<String>();

    public EAML_ErrorHandler() {
    }

    //Metodos para guardar errores detectados en las listas
    @Override
    public void warning(SAXParseException eWarning) throws SAXException {
      warningList.add(eWarning.toString());
      warning = 1;
    }

    @Override
    public void error(SAXParseException eError) throws SAXException {
      errorList.add(eError.toString());
      error = 1;
    }

    @Override
    public void fatalError(SAXParseException eFatalError) throws SAXException {
      fatalErrorList.add(eFatalError.toString());
      fatalError = 1;
    }

    //Funciones para detectar que ha ocurrido una excepcion
    public int getWarning() {
      return warning;
    }

    public int getError() {
      return error;
    }

    public int getFatalError() {
      return fatalError;
    }

    //Metodos para obtener las listas de errores
    public ArrayList<String> getWarningList() {
      return warningList;
    }

    public ArrayList<String> getErrorList() {
      return errorList;
    }

    public ArrayList<String> getFatalErrorList() {
      return fatalErrorList;
    }
}
