package p2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sint101P2 extends HttpServlet {

  public void init (ServletConfig config) throws ServletException {
    try {

    } catch(Exception e) {

    }
  }

  public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String pphase = request.getParameter("pphase");
    String passwd = request.getParameter("p");
    String pdegree = request.getParameter("pdegree");
    String psubject = request.getParameter("psubject");
    Page01 page01 = new Page01();
    Page02 page02 = new Page02();
    Page11 page11 = new Page11();
    Page12 page12 = new Page12();
    Page13 page13 = new Page13();

    if (pphase == null) {
      page01.phase01(request, response);
    } else {
      switch (pphase.trim()) {
        case "01":
          page01.phase01(request, response);
          break;

       case "02":
         page02.phase02(request, response);
         break;

       case "11":
         page11.phase11(request, response);
         break;

       case "12":
         page12.phase12(request, response, pdegree);
         break;

       case "13":
         page13.phase13(request, response, pdegree, psubject);
         break;

       default:
         page01.phase01(request, response);
         break;

      }
    }
  }

}
