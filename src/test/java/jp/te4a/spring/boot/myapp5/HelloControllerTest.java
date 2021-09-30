package jp.te4a.spring.boot.myapp5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.yaml.snakeyaml.events.MappingStartEvent;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
// SpringBootの起動クラスを指定
@ContextConfiguration(classes = Application.class)
// クラス内の全メソッドにおいて、実行前にDIコンテナの中身を破棄する
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
// テストランナー：各テストケース（テストメソッド）を制御する：DIする場合は必須？
@ExtendWith(SpringExtension.class)
// MockおよびWebApplicationContextの自動ロード：サーブレット環境を自動作成する
@AutoConfigureMockMvc
//テスト時に起動するSprinbBootプロジェクトの使用ポート番号を設定する場合：ランダム
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// クラス単位でインスタンス生成（通常はメソッド単位）：@BeforeAllを使うため
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class HelloControllerTest {

	@Autowired
    MockMvc mockMvc;  // SpringMVCモックオブジェクト
    @Autowired
    WebApplicationContext wac;  // Webアプリへの設定提供
    @BeforeAll
    public void テスト前処理() {
        // Thymeleafを使用していることがテスト時に認識されない様子
        // 循環ビューが発生しないことを明示するためにViewResolverを使用
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates");
        viewResolver.setSuffix(".html");
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


	// @Test
	// void test() {
	// 	HelloController helloController = new HelloController();
		
	// 	String expect = "index";
	// 	String actual = helloController.index();
	// 	assertEquals(expect,actual);
	// }
	
	// @Test
	// void test2() {
	// 	HelloController helloController = new HelloController();
		
	// 	String expect = "{msg=you write 'aaa'!!!}";
	// 	ModelAndView aaa = helloController.postForm("aaa");
	// 	ModelMap mmap = aaa.getModelMap();
	// 	String actual = mmap.toString();
		
	// 	assertEquals(expect,actual);
	// }

	// static HelloController helloController ;
	// @BeforeAll
	// static void テスト前処理2() {
	// 	helloController = new HelloController();
	// }
	// @Test
	// // void addテスト_正常() {
	// // 	assertEquals(helloController.postForm("aaa"),"you write 'aaa'!!!");
	// // }
	// @AfterAll
	// static void テスト後処理() {
	// 	helloController = null;
	// }
	

	@Test
    public void indexに遷移しているか () throws Exception {

        MvcResult result = mockMvc.perform(  get("/")  )

            .andExpect(  status().is2xxSuccessful()  )

            .andExpect(  view().name("index")  )

            .andReturn();
	}   

	@Test
    public void post_form_1 () throws Exception {

        MvcResult result = mockMvc.perform(  post("/post").param("text1", "aaa") )
            .andExpect(  status().is2xxSuccessful()  )
			.andExpect(  view().name("index")  )
			.andReturn();
			
		ModelAndView m =  result.getModelAndView();
		assertEquals("index", m.getViewName());
		assertEquals("you write 'aaa'!!!", m.getModel().get("msg"));
	}   	
	
	@Test
    public void post_form_2 () throws Exception {

        MvcResult result = mockMvc.perform(  post("/post").param("text1", "") )
            .andExpect(  status().is2xxSuccessful()  )
			.andExpect(  view().name("index")  )
			.andReturn();
			
		ModelAndView m =  result.getModelAndView();
		assertEquals("index", m.getViewName());
		assertEquals("you write ''!!!", m.getModel().get("msg"));
    }   	
}


	

