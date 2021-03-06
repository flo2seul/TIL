package com.encore.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;

import com.encore.dao.BookDAO;
import com.encore.exception.DuplicateISBNException;
import com.encore.vo.Book;

import config.ServerInfo;

public class BookDAOImpl implements BookDAO {

	private static BookDAOImpl dao = new BookDAOImpl();

	private BookDAOImpl() {

	}

	public static BookDAOImpl getInstance() {
		return dao;
	}

	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		System.out.println("디비 서버 연결..");
		return conn;

	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();

	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(ps, conn);

	}

	public int isIsbn(String isbn) throws SQLException {
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT count(*) FROM book WHERE isbn=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			ps.setString(1, isbn);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				return count;
			} else {
				return count;
			}
		} finally {
			closeAll(rs, ps, conn);
		}

	}

	@Override
	public void registerBook(Book vo) throws SQLException,DuplicateISBNException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
		     conn = getConnect();
		     if(isIsbn(vo.getIsbn())==0) { //isbn에 해당하는 책이 없다면...
		    	 String query = "INSERT INTO VALUES(?,?,?,?,?)";
		         ps = conn.prepareStatement(query);	
		    	 ps.setString(1, vo.getIsbn());
		    	 ps.setString(2, vo.getTitle());
		    	 ps.setString(3, vo.getWriter());
		    	 ps.setString(4, vo.getPublisher());
		    	 ps.setInt(5, vo.getPrice());
		    	 System.out.println(ps.executeUpdate() + "권이 추가되었습니다.");
		     }else {                     //isbn에 해당하는 책이 있다면...예외발생
		    	 throw new DuplicateISBNException();
		     }}finally {		
		    	 closeAll(ps, conn);
		     }
				
		    	 
		     }
		
		
		
	
		
	

	@Override
	public void deleteBook(String isbn) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
        try {
		conn = getConnect();
		
		String querydel = "DELETE FROM book WHERE isbn=?";
        ps = conn.prepareStatement(querydel);
		
		ps.setString(1, isbn);
		
		System.out.println(ps.executeUpdate() + "권 삭제가 완료되었습니다.");
        }finally {
		closeAll(ps, conn);
        }
	}

	@Override
	public Book findByBook(String isbn, String title) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		Book book = null;
		ResultSet rs = null;

		conn = getConnect();
		String query = "select * from book where isbn=? and title=?";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement 객체 생성...");

		ps.setString(1, isbn);
		ps.setString(2, title);

		rs = ps.executeQuery();
		while (rs.next()) {
			book = new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price"));
		}
		closeAll(rs, ps, conn);
		return book;
	}

	@Override
	public ArrayList<Book> findByWriter(String writer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Book> list = new ArrayList<Book>();

		conn = getConnect();
		String query = "select * from book where author=?";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement 객체 생성...");

		ps.setString(1, writer);

		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}

		closeAll(ps, conn);
		return list;
	}

	@Override
	public ArrayList<Book> findByPublisher(String publisher) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Book> list = new ArrayList<Book>();

		conn = getConnect();
		String query = "select * from book where publisher=?";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement 객체 생성...");

		ps.setString(1, publisher);

		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}

		closeAll(ps, conn);
		return list;

	}

	@Override
	public ArrayList<Book> findByPrice(int min, int max) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Book> list = new ArrayList<Book>();

		conn = getConnect();
		String query = "select * from book where price BETWEEN ? AND ?";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement 객체 생성...");

		ps.setInt(1, min);
		ps.setInt(2, max);

		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}

		closeAll(rs, ps, conn);
		return list;
	}

	@Override
	public void discountBook(int per, String publisher) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		conn = getConnect();
		String query = "update book set price=price-(price*(?/100))  where publisher=? ";

		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement 객체 생성...");

		ps.setInt(1, per);
		ps.setString(2, publisher);

		System.out.println(ps.executeUpdate() + "권의 정보가 변경되었습니다.");

		closeAll(ps, conn);

	}

	@Override
	public void printAllInfo() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		conn = getConnect();
		String query = "select * from book";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement 객체 생성...");

		rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}

		closeAll(rs, ps, conn);

	}

}
