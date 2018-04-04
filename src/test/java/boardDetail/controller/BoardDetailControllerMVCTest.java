package boardDetail.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/config/spring/dispatcher-servlet.xml",
    "file:src/main/webapp/WEB-INF/config/context-common.xml",
    "file:src/main/webapp/WEB-INF/config/context-datasource.xml"})
public class BoardDetailControllerMVCTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mock;

  @Before
  public void setup() {
    mock = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void selectBoardDetail() throws Exception{
  }
}
