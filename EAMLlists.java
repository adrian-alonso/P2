package p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.io.File;
import javax.xml.xpath.*;
import org.w3c.dom.*;


public class EAMLlists {
  HashMap<String,Document> docsMap = new HashMap<String,Document>();
  File file;

  //CONSTRUCTOR
  //Constructor por defecto
  public EAMLlists() {
  }

  //Constructor comun
  public EAMLlists(File file, HashMap<String,Document> docsMap) {
    this.file = file;
    this.docsMap = docsMap;
  }

  public ArrayList<String> getC1Degrees() {
    ArrayList<String> docsList = new ArrayList<String>();
    for (String key : docsMap.keySet()) {
      docsList.add(key);
    }
    //Lista en orden alfabetico
    Collections.sort(docsList);
    return docsList;
  }

  public ArrayList<Subject> getC1Subjects(String degree) {
    // if () {
    //
    // } else {
      ArrayList<Subject> subjectsList = new ArrayList<Subject>();
    //}
    //ordenar subjectsList;
    return subjectsList;
  }

  public ArrayList<Student> getC1Students(String degree, String subject) {
    ArrayList<Student> studentsList = new ArrayList<Student>();
    return studentsList;
  }
}
