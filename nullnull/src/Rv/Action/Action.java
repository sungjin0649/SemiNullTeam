package Rv.Action;

//Ư�� ����Ͻ��� ��û���� �����ϰ� ��� ���� ActionForward Ÿ������ ��ȯ�ϴ� 
//�޼��尡 ���� �Ǿ� �ֽ��ϴ�. 
//Action : �������̽���
//ActionForward:��ȯ��

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public ActionForward execute
	(HttpServletRequest request,
	HttpServletResponse response) throws ServletException , IOException;
}
