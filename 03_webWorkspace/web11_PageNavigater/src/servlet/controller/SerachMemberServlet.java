package servlet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SerachMemberServlet
 */
@WebServlet("/SMS")
public class SerachMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SerachMemberServlet() {
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1. 폼값 받아서
		 * 2. dao 리턴 받고
		 * 3. 비즈니스 로직 호출한 결과로 vo객체 리턴 받고
		 * 4. 리턴받은 값을 Attribute에 바인딩...어떤 Attribute를 사용할 건지 잘 생각한다.
		 * 5. 네비게이션 --- 서버 내에 있는 jsp로 바로 이동 
		 */
    	//1.
        String id = request.getParameter("id");
    	
    	//2.
    	
        //3. request에 바인딩...
    	request.setAttribute("id", id);
    	
    	//4.
    	request.getRequestDispatcher("result_view.jsp").forward(request, response);

}
}
