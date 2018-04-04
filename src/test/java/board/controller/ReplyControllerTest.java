package board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring/dispatcher-servlet.xml", "file:src/main/webapp/WEB-INF/config/context-common.xml", "file:src/main/webapp/WEB-INF/config/context-datasource.xml"})
@Transactional
public class ReplyControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mock;

  private MockHttpSession session = new MockHttpSession();

  String  day = "2017-12-28";
  Date testDate = java.sql.Date.valueOf(day);

  @Before
  public void setup() {

    mock = MockMvcBuilders.webAppContextSetup(context).build();

    session.setAttribute("memberNo","20");
  }

  @Test
  public void listViewTest() throws Exception {

    mock.perform(get("/board/replyList.do").session(session).param("boardNum","3"))
        .andExpect(status().isOk())
        .andExpect(view().name("board/detail"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void insertReply_success() throws Exception {

    replyInsertTest("3","하하하","72",testDate,"0","1");
  }

  @Test
  public void insertReply_fail() throws Exception {

    replyInsertTest("","하하하","72",testDate,"0","0");
    replyInsertTest("3","","72",testDate,"0","0");
    replyInsertTest("3","하하하","",testDate,"0","0");
    // Date 가 null로 들어가는건 안되는듯 replyInsertTest("3","하하하","72",null,"0","0");
    replyInsertTest("3","하하하","72",testDate,"","0");
  }

  @Test
  public void deleteReply_success() throws Exception {

    mock.perform(get("/board/replyDelete.do").session(session)
        .param("replyNo", "33"))
        .andExpect(status().isOk())
        .andExpect(view().name("board/detail"))
        .andDo(MockMvcResultHandlers.print());
  }


  @Test
  public void updateReply_fail() throws Exception {

    mock.perform(get("/board/replyUpdate.do").session(session)
        .param("replyNo", "33")
        .param("reContent",""))
        .andExpect(status().isOk())
        .andExpect(view().name("board/detail"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void updateReply_success() throws Exception {

    mock.perform(get("/board/replyUpdate.do").session(session)
        .param("replyNo", "33")
        .param("reContent","수정테스트"))
        .andExpect(status().isOk())
        .andExpect(view().name("board/detail"))
        .andDo(MockMvcResultHandlers.print());
  }

  public void replyInsertTest(String board_no, String content, String writer, Date write_date, String parents_no, String result) throws Exception {

    mock.perform(post("/board/replyInsert.do").session(session)
        .param("board_no", board_no)
        .param("content", content)
        .param("writer", writer)
        .param("write_date", String.valueOf(write_date))
        .param("parents_no",parents_no))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(result));
  }

}
