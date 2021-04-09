package listener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
/*
 * WAS? ??΄? ?ΈμΆλ? λͺ¨λ  ?Ό?΄? ?¬?΄?΄ λ©μ? ? λ¦?...
 * 1. contextInitialized()
 * --------------------Ready On------------------------------
 * 2. ?λΈλ¦Ώ ??±? ?ΈμΆ?
 * 3. init()
 * 
 * ---------------- Repeat---------------------------------- 
 * 
 * 4. service() --> doGet() | doPost()
 * 
 * ---------------------------------------------------------
 * 
 * 5. destroy()
 * 6. contextDestroyed()
 * 
 */
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
	private ServletContext cont;
	
	
	public void contextInitialized(ServletContextEvent sce)  { 
	   System.out.println("contextInitialized.....");
	   
	   //1. ServletContextλ¦¬ν΄ λ°μ...?΄? sceλ‘λ??° λ°μ?Ό ??€.
	   cont = sce.getServletContext();
	   
	   //2. DD??Ό? κ²½λ‘λ₯? ?½?΄?€?Έ?€.
	   String filename = cont.getInitParameter("path");
	   System.out.println("User File Path : "+filename);
	   
	   //3. getResourceAsStream()...
	   InputStream is = null;
	   BufferedReader br = null;
	   try {
		   System.out.println("getResourceAsStream()....");
		   is = cont.getResourceAsStream(filename);
		   
		   System.out.println("BufferedReader ??±...");
		   br = new BufferedReader(new InputStreamReader(is));
		   
		   String line = null;
		   System.out.println("?΄?©? ?½?΄?€???€...");
		   while((line = br.readLine())!=null) {
			   System.out.println(line);
		   }
	   }catch(Exception e) {
		   System.out.println("??Ό? ?½?΄?€?΄??° ?€?¨??΅??€...");
	   }
	  }
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("contextDestroyed.....");
    }   	
}













