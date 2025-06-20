package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDAO;
import com.javaex.vo.GuestbookVO;


//guestbook2 프로젝트와 동일한거 설정 다시 해볼 겸 만든거임..

@WebServlet("/pbc")
public class GuestbookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
    //생성자
    public GuestbookController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("★GuestbookController");
		
		String action = request.getParameter("action");
		
		System.out.println("★action="+action);
		
		if("list".equals(action)) {
			System.out.println("★리스트");
			
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			List<GuestbookVO> guestbookList =  guestbookDAO.guestbookSelect();
			
			//list.jsp에게 후반일 html을 만들고 응답문서 만들어 보낸다
			//1)request객체에 데이타를 넣는다
			request.setAttribute("gList", guestbookList);
			
			//2)list.jsp에 request 객체와 response 객체를 보낸다(*포워드)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
		
		}else if("wform".equals(action)) {
			System.out.println("★등록폼");
			
			//등록폼을 응답해야한다
			//db관련 할 일이 없다
			
			//jsp에게 화면을 그리게 한다(포워드)
			//writeForm.jsp 포워드
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
		}else if("write".equals(action)) {
			System.out.println("★등록");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//데이터를 묶는다
			GuestbookVO guestbookVO = new GuestbookVO(name, password, content);
			System.out.println(guestbookVO);
			
			//DAO를 통해서 저장시키기
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			guestbookDAO.guestbookInsert(guestbookVO);
			
			//리다이렉트
			System.out.println("★리다이렉트");
			response.sendRedirect("http://192.168.0.99:8080/guestbook3/pbc?action=list");
			
			/*
			//응답(리스트)
			List<PersonVO> personList = phonebookDAO.personSelect();
			
			//request의 어트리뷰트 영역에 데이타 넣기
			//1)request객체에 데이타를 넣는다
			request.setAttribute("pList", personList);
			
			//2)list.jsp에 request 객체와 response 객체를 보낸다(*포워드)
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			*/
			
		}else if("delete".equals(action)) {
			System.out.println("★삭제");
			
			int gId = Integer.parseInt(request.getParameter("gId"));
			String password = request.getParameter("password");
			
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			GuestbookVO guestbook = guestbookDAO.getGuestbookById(gId);
			
			String message;
			
			if(guestbook != null && guestbook.getPassword().equals(password)) {
		        guestbookDAO.guestbookDelete(gId);
		        message = "삭제 성공했습니다.";
		        System.out.println("삭제 성공");
		    } else {
		    	message = "비밀번호가 맞지 않거나 해당 글이 존재하지 않습니다.";
		        System.out.println("비밀번호가 맞지 않습니다.");
		    }
			
			List<GuestbookVO> guestbookList = guestbookDAO.guestbookSelect();
		    request.setAttribute("gList", guestbookList);
		    request.setAttribute("message", message);
		    
			//리다이렉트 & 포워드
			//System.out.println("★리다이렉트");
			//response.sendRedirect("http://192.168.0.99:8080/guestbook3/pbc?action=list");
			
			//이렇게 하면 message는 사라진다, 왜냐하면 sendRedirect()는 새 request이기 때문이다
			//그래서 사용자에게 삭제 성공/실패 메시지를 보여주고 싶다면 forward()를 사용해야 한다
			
			System.out.println("★포워드");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/addList.jsp");
		    rd.forward(request, response);
		    
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
