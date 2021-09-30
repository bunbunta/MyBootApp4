package jp.te4a.spring.boot.myapp5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;



class TestModel implements Model{

	@Override
	public Model addAttribute(String attributeName, Object attributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model addAttribute(Object attributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model addAllAttributes(Collection<?> attributeValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model addAllAttributes(Map<String, ?> attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model mergeAttributes(Map<String, ?> attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAttribute(String attributeName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getAttribute(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> asMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
}


public class HelloControllerTest {

	@Test
	void test() {
		HelloController helloController = new HelloController();
		
		String expect = "index";
		String actual = helloController.index();
		assertEquals(expect,actual);
	}
	
	@Test
	void test2() {
		HelloController helloController = new HelloController();
		
		String expect = "{msg=you write 'aaa'!!!}";
		ModelAndView aaa = helloController.postForm("aaa");
		ModelMap mmap = aaa.getModelMap();
		String actual = mmap.toString();
		
		assertEquals(expect,actual);
	}
	
}
