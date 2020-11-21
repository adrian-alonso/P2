package p2;

import java.util.ArrayList;

public class ErrorFile implements Comparable<ErrorFile> {
  String errorID;
  ArrayList<String> errors = new ArrayList<String>();

  //CONSTRUCTOR
  //Constructor por defecto
  public ErrorFile() {
  }

  //Constructor comun
  public ErrorFile(String errorID, ArrayList<String> errrors) {
    this.errorID = errorID;
    this.errors = errors;
  }

  //METODOS
  public String getErrorID() {
    return errorID;
  }

  public ArrayList<String> getErrors() {
    return errors;
  }

  //Metodo toString
  public String toString() {
    return errorID;
  }

  public int compareTo(ErrorFile errorfile) {
    return this.errorID.compareTo(errorfile.getErrorID());
  }

}
