package p2;

public class Student {
  String studentName;
  String id;
  int grade;
  String address;

  //CONSTRUCTORES
  //Constructor por defecto
  public Student() {
  }

  //Constructor comun
  public Student(String studentName, String id, int grade, String address) {
    this.studentName = studentName;
    this.id = id;
    this.grade = grade;
    this.address = address;
  }

  public Student(String studentName, String id, int grade) {
    this.studentName = studentName;
    this.id = id;
    this.grade = grade;
  }

  //METODOS GET
  public String getStudentName() {
    return studentName;
  }

  public String getId() {
    return id;
  }

  public int getGrade() {
    return grade;
  }

  public String getAddress() {
    return address;
  }

  //METODO TOSTRING
  public void toString() {
    if (address == null) {
      return "Nombre = '" + studentName + "' --- ID = '" + id + "' --- Dirección = ''";
    } else {
      return "Nombre = '" + studentName + "' --- ID = '" + id + "' --- Dirección = '" + address + "'";
    }
  }

}
