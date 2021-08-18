package Rv.Action;

//특정 비즈니스가 요청으로 수행하고 결과 값을 ActionForward 타입으로 변환하는 
//메서드가 정의 되어 있습니다. 
//Action : 인터페이스명
//ActionForward:반환형

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public ActionForward execute
	(HttpServletRequest request,
	HttpServletResponse response) throws ServletException , IOException;
}
