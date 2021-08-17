package okybo.Freeboard.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FreeBoardFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����� ���� �ٿ�ε� ���μ���");
		String fileName =request.getParameter("filename");
	    System.out.println("fileName = " + fileName);
	    
	    String savePath = "frboardupload";
	    
	    //������ ���� ȯ�� ������ ��� �ִ� ��ü�� �����մϴ�.
	    ServletContext context = request.getServletContext();
	    String sDownloadPath = context.getRealPath(savePath);
	    //�� �� ������ �� �������� ��Ÿ���� ������ �����ϴ�.
	    //String sDowloadPath = application.getRealPath(savePath);
	    
	    // String sFilePath = sDownloadPath + "\\" + fileName;
	    // "\" �߰��ϱ� ���� "\\" ����մϴ�.
	    String sFilePath = sDownloadPath + "\\" + fileName;
	    System.out.println(sFilePath);
	    
	    byte b[] = new byte[4096];
	    
	    //sFilePath�� �ִ� ������ MimeType�� ���ؿɴϴ�.
	    String sMimeType = context.getMimeType(sFilePath);
	    System.out.println("sMimeType>>>" + sMimeType);
	    
	    /*
	       1. Content-Type: ���۵Ǵ� Content�� � �������� �˷��ִ� ������ ������ �ֽ��ϴ�.
	         - text/html, image/png, application/octet-stream ���� ���� �����ϴ�.
	       2. Content-Type�� ���ؼ� �������� �ش� �����͸� ��� ó���ؾ� �� �� �Ǵ��� �� 
	                �ְ� �˴ϴ�.
	            (��)
	              1) image/png:  �������� �ش� ����Ʈ�� �̹����ν� �����ϰ� �˴ϴ�.
	              2) application/octet-stream: ��Ȯ�� Binary ������ �ǹ��ϸ�,
	                                   �� ��� �������� ������ �ٿ�ε��ϴ� ���·� �����մϴ�.
	              3) text/javascript; �������� Content�� javascript������ ����ϰ� �˴ϴ�.
	    */
	    
	    /*
	        octet-stream�� 8��Ʈ�� �� �����͸� �ǹ��ϸ� �������� ���� ���� ������ �ǹ��մϴ�.
	    */
	    if (sMimeType == null)
	    	sMimeType = "application/octet-stream";
	    
	    response.setContentType(sMimeType);
	    
	    /*
	      -  �� �κ��� �ѱ� ���ϸ��� ������ ���� ������ �ݴϴ�.
	      -  getBytes(ĳ���ͼ�) : �ڹ� ���ο� �����Ǵ� �����ڵ� ���ڿ��� ���ڷ� ������
	                                  ĳ���ͼ��� ����Ʈ �迭�� ��ȯ�ϴ� �޼����Դϴ�.
	      -  String(byte[] bytes, Charset charset)
	         String(����Ʈ�迭, ĳ���ͼ�) ������ : �ش� ����Ʈ �迭�� �־��� ĳ���� ������
	                                                      �����Ͽ� ��Ʈ���� ����� �������Դϴ�.  
	    */
	    String sEncoding = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
	         System.out.println(sEncoding); //����Ʈ�� ó���߱� ������(��Ʈ��ũ ����� ����Ʈ�� �ϱ⶧��) �ѱ۱��ڴ� ������ ���´�
	         
	    /*
	       - Content-Disposition: Content�� ��� ó���Ǿ�� �ϴ��� ��Ÿ���ϴ�.
	         1) Content-Disposition:
	        	inline: �������� Content�� ��� ����ؾ� ���� ��Ÿ���ϴ�.
	        	 �̹����� ��� ������ ������ ��� ����ϸ�,  ������ ��� �ؽ�Ʈ�� ����մϴ�.
	         2) Content-Disposition:
	        	attachment: �������� �ش� Content�� ó������ �ʰ� �ٿ�ε��ϰ� �˴ϴ�.       	
	   */           
	   response.setHeader("Content-Disposition","attachment; filename=" + sEncoding);
	   /* response.setHeader("Content-Disposition","inline; filename=" + sEncoding);*/
	   
	   /*
	   1. try-with-resource������ try()��ȣ �ȿ� ����� �ڿ���
	      try���� ���� �� �ڵ����� close()�޼��带 ȣ���մϴ�.
	      
	   2. try-with-resource���� ���� �ڵ����� ��ü�� close()�� ȣ��� �� �������� Ŭ������
	      AutoCloseable�̶�� �������̽��� ������ ���̾�� �մϴ�.
	     ����) try(){
	       .....
	     }catch(){
	    	 
	     }finally{}
	   */
	   
	   try (
			   //�� ���������� ��� ��Ʈ�� �����մϴ�.
			   BufferedOutputStream out2 =
			          new BufferedOutputStream(response.getOutputStream());
			   
			   //sFilePath�� ������ ���Ͽ� ���� �Է� ��Ʈ���� �����մϴ�.
			   BufferedInputStream in =
				          new BufferedInputStream(new FileInputStream(sFilePath));
			   )
	   {
		   int numRead;
		   
		  //read(b, 0, b.length) : ����Ʈ �迭 b�� 0�� ���� b.length ũ�� ��ŭ �о�ɴϴ�.
		  while ((numRead = in.read(b, 0, b.length)) != -1) {//���� �����Ͱ� �����ϴ� ���
			  out2.write(b, 0, numRead);// ����Ʈ �迭 b�� 0�� ���� numReadũ�� ��ŭ �������� ���
		  }
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
		return null;
	}

}
