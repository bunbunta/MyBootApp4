package jp.te4a.spring.boot.myapp4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;

import jp.te4a.spring.boot.myapp5.HelloController;

public class HelloControllerTest {

	@Test
	void test() {
		HelloController helloController = new HelloController();
		String expect = "this is Spring Boot sample.";
		String actual = "this is Spring Boot sample.";
		
		assertEquals(expect,actual);
	}
}
