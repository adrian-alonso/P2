package p2;

import java.util.ArrayList;

public class WarningFile implements Comparable<WarningFile>{
  String warningID;
  ArrayList<String> warnings = new ArrayList<String>();

  //CONSTRUCTOR
  //Constructor por defecto
  public WarningFile() {
  }

  //Constructor comun
  public WarningFile(String warningID, ArrayList<String> warnings) {
    this.warningID = warningID;
    this.warnings = warnings;
  }

  //METODOS
  public String getWarningID() {
    return warningID;
  }

  public ArrayList<String> getWarnings() {
    return warnings;
  }

  //Metodo toString
  public String toString() {
    return warningID;
  }

  public int compareTo(WarningFile warningfile) {
    return this.warningID.compareTo(warningfile.getWarningID());
  }

}
