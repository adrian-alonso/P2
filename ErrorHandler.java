package p2;

import java.util.ArrayList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;

public class ErrorHandler extends DefaultHandler {
    //Contadores para el numero de excepciones
    int error = 0;
    int warning = 0;
    int fatalError = 0;

    //Listas para guardar los errores de las excepciones
    ArrayList<String> warningList = new ArrayList<String>();
    ArrayList<String> errorList = new ArrayList<String>();
    ArrayList<String> fatalErrorList = new ArrayList<String>();

    public ErrorHandler() {
    }

    //Metodos para guardar errores detectados en las listas
    public void warning(SAXParseException eWarning) {
      warningList.add(eWarning.toString());
      warning = 1;
    }
    public void error(SAXParseException eError) {
      errorList.add(eError.toString());
      error = 1;
    }
    public void fatalerror(SAXParseException eFatalError) {
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
