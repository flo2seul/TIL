package com.encore.test;

import java.sql.SQLException;
import java.util.ArrayList;

import com.encore.dao.impl.BookDAOImpl;
import com.encore.exception.DuplicateISBNException;
import com.encore.vo.Book;


import config.ServerInfo;

public class BookDAOTest {
	public  BookDAOTest() throws ClassNotFoundException {
		Class.forName(ServerInfo.DRIVER);
		System.out.println("Driver Loading....");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, DuplicateISBNException {
		
		BookDAOTest jdbc = new BookDAOTest();
		
		BookDAOImpl dao =  BookDAOImpl.getInstance();
		try{
			dao.registerBook(new Book("a","자기만의 방","버지니아 울프","펭귄",13000));
		} catch(Exception e) {
			System.out.println("책 등록 실패");
		}
		//dao.deleteBook("6F6");
		//System.out.println(dao.findByBook("a", "자기만의 방"));
		//ArrayList<Book> returnList = dao.findByWriter("권정생");
		//for (Book b : returnList)
			//System.out.println(b);
		//ArrayList<Book> returnList = dao.findByWriter("권정생");
				//for (Book b : returnList)
					//System.out.println(b);
		//ArrayList<Book> returnList = dao.findByPublisher("길벗");
				//for (Book b : returnList)
					//System.out.println(b);
		//ArrayList<Book> returnList = dao.findByPrice(10000,30000);
			  //for (Book b : returnList)
					//System.out.println(b);
		//dao.discountBook(10, "동아");
		//dao.printAllInfo();
	}

}
