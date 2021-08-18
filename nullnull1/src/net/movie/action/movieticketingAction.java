package net.movie.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import net.movie.db.Crawl;

public class movieticketingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebDriver driver;
	    
	     final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	     final String WEB_DRIVER_PATH = "C:\\Users\\win10\\Desktop\\selenium-java-3.141.59\\chromedriver_92/chromedriver.exe";
	    
	    //크롤링 할 URL
	    String base_url="http://www.cgv.co.kr/movies";
	 
	        //System Property SetUp 드라이버설정
	        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	                   
	        //Driver SetUp
	         ChromeOptions options = new ChromeOptions();
	         options.setCapability("ignoreProtectedModeSettings", true);
	         options.addArguments("headless"); //이부분이 있어야 get()할때 창을 안띄움
	         driver = new ChromeDriver(options);

	        ArrayList<Crawl> movielist = new ArrayList<Crawl>();
	        Crawl c;
	        try {
	            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
	            driver.get(base_url);
	          //브라우저 이동시 생기는 로드시간을 기다린다.
	    	 //HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
	    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
	    		
	    		 for(int i =0; i< 3; i++) {
	    			 c= new Crawl();
	    			c.setName(driver.findElements(By.className("title")).get(i).getText()); //제목
	    			c.setSrc(driver.findElement(By.xpath("//*[@id=\"contents\"]/div[1]/div[3]/ol[1]/li["+(i+1)+"]/div[1]/a/span/img")).getAttribute("src"));
	    			movielist.add(c);
	    		 }
	    		 for(int i =0; i< 4; i++) {
	    			 c= new Crawl();     	
	     			c.setName(driver.findElements(By.className("title")).get(i+3).getText()); //제목
	     			c.setSrc(driver.findElement(By.xpath("//*[@id=\"contents\"]/div[1]/div[3]/ol[2]/li["+(i+1)+"]/div[1]/a/span/img")).getAttribute("src"));
	     			movielist.add(c);
	     		 }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            driver.close();
	        }//try end
	        
	        request.setAttribute("movielist", movielist);
			ActionForward forward =new ActionForward();
			forward.setRedirect(false);
			//글 목록 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("Movie/ticket.jsp");
			return forward; //BoardFontController.java로 리턴됩니다.
	}

}
