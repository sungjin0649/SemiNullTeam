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



public class bestmovieAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    WebDriver driver;
	    
	     final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	     final String WEB_DRIVER_PATH = "C:\\Users\\win10\\Desktop\\selenium-java-3.141.59\\chromedriver_92/chromedriver.exe";
	    
	    //ũ�Ѹ� �� URL
	    String base_url="http://www.cgv.co.kr/movies";
	 
	        //System Property SetUp ����̹�����
	        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	                   
	        //Driver SetUp
	         ChromeOptions options = new ChromeOptions();
	         options.setCapability("ignoreProtectedModeSettings", true);
	         options.addArguments("headless"); //�̺κ��� �־�� get()�Ҷ� â�� �ȶ��
	         driver = new ChromeDriver(options);

	        ArrayList<Crawl> movielist = new ArrayList<Crawl>();
	        Crawl c;
	        try {
	            //get page (= ���������� url�� �ּ�â�� ���� �� request �� �Ͱ� ����)
	            driver.get(base_url);
	          //������ �̵��� ����� �ε�ð��� ��ٸ���.
	    	 //HTTP����ӵ����� �ڹ��� ������ �ӵ��� �� ������ ������ ���������� 1�ʸ� ����Ѵ�.
	    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
	    		
	    		 for(int i =0; i< 3; i++) {
	    			 c= new Crawl();
	    			c.setRank(driver.findElements(By.className("rank")).get(i).getText()); //�ѹ�
	    			c.setName(driver.findElements(By.className("title")).get(i).getText()); //����
	    			c.setTicket(driver.findElements(By.className("percent")).get((i*2)).getText());//������
	    			c.setGood(driver.findElements(By.className("percent")).get((i*2)+1).getText());//����
	    			c.setSrc(driver.findElement(By.xpath("//*[@id=\"contents\"]/div[1]/div[3]/ol[1]/li["+(i+1)+"]/div[1]/a/span/img")).getAttribute("src"));
	    			movielist.add(c);
	    		 }
	    		 for(int i =0; i< 4; i++) {
	    			 c= new Crawl();
	     			c.setRank(driver.findElements(By.className("rank")).get(i+3).getText()); //�ѹ�
	     			c.setName(driver.findElements(By.className("title")).get(i+3).getText()); //����
	     			c.setTicket(driver.findElements(By.className("percent")).get((i+3)*2).getText());//������
	     			c.setGood(driver.findElements(By.className("percent")).get((i+3)*2+1).getText());//����
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
			//�� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
			forward.setPath("Movie/movieList.jsp");
			return forward; //BoardFontController.java�� ���ϵ˴ϴ�.
	        
	}

}
