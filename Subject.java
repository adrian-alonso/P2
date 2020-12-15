package p2;

public class Subject implements Comparable<Subject> {
  String subjectName;
  String type;
  int course;
  String idSub;

  //CONSTRUCTORES
  //Constructor por defecto
  public Subject() {
  }

  //Constructor comun
  public Subject(String subjectName, int course, String type, String idSub) {
    this.subjectName = subjectName;
    this.type = type;
    this.course = course;
    this.idSub = idSub;
  }


  //METODOS GET
  public String getSubjectName() {
    return subjectName;
  }

  public String getType() {
    return type;
  }

  public int getCourse() {
    return course;
  }

  public String getIdSub() {
    return idSub;
  }

  //METODO TOSTRING
  public String toString() {
    return  " --- Curso = '" + course + "' --- Tipo = '" + type + "'";
  }

  public int compareTo(Subject s) {
    if (this.getCourse()<s.getCourse()) {
      return -2;
    } else if (this.getCourse()>s.getCourse()) {
      return 2;
    } else {
      if (this.getSubjectName().compareTo(s.getSubjectName())<0) {
        return -1;
      } else if (this.getSubjectName().compareTo(s.getSubjectName())>0) {
        return 1;
      } else {
        return 0;
      }
    }
  }

}
