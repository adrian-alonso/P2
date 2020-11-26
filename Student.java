package p2;

public class Student implements Comparable<Student> {
  String studentName;
  String id;
  float grade;
  String address;

  //CONSTRUCTORES
  //Constructor por defecto
  public Student() {
  }

  //Constructor comun
  public Student(String studentName, String id, float grade, String address) {
    this.studentName = studentName;
    this.id = id;
    this.grade = grade;
    this.address = address;
  }

  public Student(String studentName, String id, float grade) {
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

  public float getGrade() {
    return grade;
  }

  public String getAddress() {
    return address;
  }

  //METODO TOSTRING
  public String toString() {
    if (address == null) {
      return "Nombre = '" + studentName + "' --- ID = '" + id + "'";
    } else {
      return "Nombre = '" + studentName + "' --- ID = '" + id + "' --- Direcci&oacute;n = '" + address + "'";
    }
  }

  public int compareTo(Student st) {
    return this.id.compareTo(st.getId());
  }

}
