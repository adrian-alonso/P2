package p2;

import java.util.ArrayList;

public class FatalErrorFile implements Comparable<FatalErrorFile> {
  String fatalErrorID;
  ArrayList<String> fatalErrors = new ArrayList<String>();

  //CONSTRUCTOR
  //Constructor por defecto
  public FatalErrorFile() {
  }

  //Constructor comun
  public FatalErrorFile(String fatalErrorID, ArrayList<String> fatalErrors) {
    this.fatalErrorID = fatalErrorID;
    this.fatalErrors = fatalErrors;
  }

  //METODOS
  public String getFatalErrorID() {
    return this.fatalErrorID;
  }

  public ArrayList<String> getFatalErrors() {
    return this.fatalErrors;
  }

  //Metodo toString
  public String toString() {
    return this.fatalErrorID;
  }

  public int compareTo(FatalErrorFile fatalerrorfile) {
    return this.fatalErrorID.compareTo(fatalerrorfile.getFatalErrorID());
  }

}
