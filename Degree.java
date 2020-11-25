package p2;

public class Degree implements Comparable<Degree> {
  String degreeName;
  String scope;
  int course;
  String location;

  //CONSTRUCTORES
  //Constructor por defecto
  public Degree() {
  }

  //Constructor comun
  public Degree(String degreeName, String scope, int course, String location) {
    this.degreeName = degreeName;
    this.scope = scope;
    this.course = course;
    this.location = location;
  }

  public Degree(String degreeName, String scope, int course) {
    this.degreeName = degreeName;
    this.scope = scope;
    this.course = course;
  }

  //METODOS GET
  public String getDegreeName() {
    return degreeName;
  }

  public String getScope() {
    return scope;
  }

  public int getCourse() {
    return course;
  }

  public String getLocation() {
    return location;
  }

  //METODO TOSTRING
  public String toString() {
    if (location == null) {
      return  " --- Curso = '" + course + "' --- Ámbito = '" + scope + "'";
    } else {
      return " --- Curso = '" + course + "' --- Ámbito = '" + scope + "' --- Campus = '" + location + "'";
    }
  }

  public int compareTo(Degree d) {
    return this.degreeName.compareTo(d.getDegreeName());
  }

}
