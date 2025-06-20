package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVO;

public class GuestbookDAO {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/phonebook_db";
	private String id = "phonebook";
	private String pw = "phonebook";
	
	//생성자
	public GuestbookDAO() {	}

	//메소드gs
	
	//메소드일반
	//DB연결 메소드-공통
	private void connect() { // 메인에서는 사용하지 못함
		
		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	// 자원정리 메소드-공통
	private void close() {
    // 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	// 등록
	public int guestbookInsert(GuestbookVO geustbookVO) {
		
		System.out.println("☆guestbookInsert()");
		
		int count = -1;
		
		// 0. import java.sql.*;
		
		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into guestbook (guestbook_id, name, password, content) ";
			query += " values(null, ?, ?, ?) ";
			
			// 바인딩 준비
			String name = geustbookVO.getName();
			String password = geustbookVO.getPassword();
			String content = geustbookVO.getContent();
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, content);
			
			String debugQuery = String.format(
					"insert into guestbook values(null, '%s', '%s', '%s')",
					name, password, content
					);
			System.out.println(debugQuery);
			
			// 실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		// 5. 자원정리
		this.close();
		
		return count;
	}
	
	// 삭제
	public int guestbookDelete(int gId) {
		
		System.out.println("☆guestbookDelete()");
		
		int count = -1;
		
		// 0. import java.sql.*;
		
		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where guestbook_id = ? ";
			
			// 바인딩 준비
			int gouestbookId = gId;
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, gouestbookId);
			
			String debugQuery = String.format(
					"delete from person where guestbook_id = '%s'",
					gouestbookId
					);
			System.out.println(debugQuery);
			
			// 실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		// 5. 자원정리
		this.close();
		
		return count;
	}
	
	public GuestbookVO getGuestbookById(int guestbookId) {
	    GuestbookVO guestbookVO = null;
	    this.connect();

	    try {
	        String query = "SELECT guestbook_id, name, password, content FROM guestbook WHERE guestbook_id = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, guestbookId);

	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	            String content = rs.getString("content");
	            guestbookVO = new GuestbookVO(guestbookId, name, password, content);
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	    this.close();

	    return guestbookVO;
	}
	
	//전체리스트 가져오기
	public List<GuestbookVO> guestbookSelect() {
		
		//리스트준비
		List<GuestbookVO> personList = new ArrayList<GuestbookVO>();
		
		this.connect();
		System.out.println("☆guestbookSelect()");
		
		try {
			//3.SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query="";
			query += " select guestbook_id, ";
			query += " 		  name, ";
			query += " 		  password, ";
			query += " 		  content, ";
			query += " 		  reg_date ";
			query += " from guestbook ";
			query += " order by guestbook_id ";
				
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
			//4.결과처리
			while(rs.next()) {
				int guestbookId = rs.getInt("guestbook_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				GuestbookVO guestbookVO = new GuestbookVO(guestbookId, name, password, content, regDate);
				
				personList.add(guestbookVO);
			}
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		
		return personList;
	}
}
