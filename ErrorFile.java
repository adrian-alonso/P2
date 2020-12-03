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
  public ErrorFile(String errorID, ArrayList<String> errors) {
    this.errorID = errorID;
    for (int i = 0; i < errors.size(); i++) {
      this.errors.add(errors.get(i));
    }

  }

  //METODOS
  public String getErrorID() {
    return this.errorID;
  }

  public ArrayList<String> getErrors() {
    return this.errors;
  }

  //Metodo toString
  public String toString() {
    return this.errorID;
  }

  public int compareTo(ErrorFile errorfile) {
    return this.errorID.compareTo(errorfile.getErrorID());
  }

}
