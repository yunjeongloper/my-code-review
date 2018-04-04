package board.service.serviceImpl;

import static org.mockito.Mockito.when;

import com.devunlimit.project.board.domain.dao.ReplyDAO;
import com.devunlimit.project.board.domain.dto.ReplyDTO;
import com.devunlimit.project.board.service.ReplyService;
import com.devunlimit.project.board.service.serviceImpl.ReplyServiceImpl;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ReplyServiceImplTest {

  @Configuration
  static class ReplyServiceTestContextConfiguration {

    @Bean
    public ReplyService replyService() {
      return new ReplyServiceImpl();
    }

    @Bean
    public ReplyDAO replyDAO() { return Mockito.mock(ReplyDAO.class); }
  }

  @Autowired
  ReplyService replyService;

  @Autowired
  ReplyDAO replyDAO;

  /*
  현재 시간을 sql Date 로 얻어오는 방법
  Date date = new Date(new java.util.Date().getTime());
  */

  String  day = "2017-12-27";
  Date testDate = java.sql.Date.valueOf(day);

  ReplyDTO replyDTO = new ReplyDTO("1","3","하하하","72",testDate,"1");

  String boardNo = "3";

  String replyNo = "1";

  String reContent = "수정테스트";

  @Before
  public void setup() {

    // 댓글 등록 성공시
    when(replyDAO.insertReply(replyDTO)).thenReturn(1);

    // 댓글 리스트 조회시
    List<ReplyDTO> replyDTOList = new ArrayList<>();
    replyDTOList.add(replyDTO);
    when(replyDAO.selectList(boardNo)).thenReturn(replyDTOList);

    // 댓글 삭제 성공시
    when(replyDAO.deleteReply(replyNo)).thenReturn(1);

    // 댓글 수정 성공시
    when(replyDAO.deleteReply(replyNo)).thenReturn(1);
  }

  // 글번호 오류
  @Test(expected = IllegalStateException.class)
  public void insertReplyBoardNoFailTest() {
    replyService.insertReply(new ReplyDTO("","글번호오류","72",testDate,"1"));
    replyService.insertReply(new ReplyDTO(null,"글번호오류","72",testDate,"1"));
  }

  // 내용 없음 오류
  @Test(expected = IllegalStateException.class)
  public void insertReplyContentFailTest() {
    replyService.insertReply(new ReplyDTO("3","","72",testDate,"1"));
    replyService.insertReply(new ReplyDTO("3",null,"72",testDate,"1"));
  }

  // 작성자번호 오류
  @Test(expected = IllegalStateException.class)
  public void insertReplyWriterNoFailTest() {
    replyService.insertReply(new ReplyDTO("3","작성자번호오류","",testDate,"1"));
    replyService.insertReply(new ReplyDTO("3","작성자번호오류","72",testDate,"1"));
  }

  // 날짜 없음 오류
  @Test(expected = IllegalStateException.class)
  public void insertReplyDateFailTest() {
    replyService.insertReply(new ReplyDTO("3","작성자번호오류","72",null,"1"));
  }

  // 작성자번호 오류
  @Test(expected = IllegalStateException.class)
  public void insertReplyParentNoFailTest() {
    replyService.insertReply(new ReplyDTO("3","작성자번호오류","72",testDate,""));
    replyService.insertReply(new ReplyDTO("3","작성자번호오류","72",testDate,null));
  }

  // 댓글 삽입 성공
  @Test()
  public void insertReplySuccessTest() {
    replyService.insertReply(replyDTO);
  }

  // 댓글 조회(멤버 번호 같이) 성공
  @Test()
  public void selectListSuccessTest() {
    replyService.selectList(boardNo);
  }

  // 댓글 삭제 성공
  @Test()
  public void deleteReplySuccessTest() { replyService.deleteReply(replyNo); }

  // 댓글 수정 실패
  @Test(expected = IllegalStateException.class)
  public void updateReplyFailTest() { replyService.updateReply(replyNo, ""); }

  // 댓글 수정 실패
  @Test()
  public void updateReplySuccessTest() { replyService.updateReply(replyNo, reContent); }



}
