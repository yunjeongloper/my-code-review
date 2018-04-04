package boardDetail.service.serviceImpl;

import com.devunlimit.project.boardDetail.domain.dao.BoardDetailDAO;
import com.devunlimit.project.boardDetail.service.BoardDetailService;
import com.devunlimit.project.boardDetail.service.serviceImpl.boardDetailServiceImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class boardDetailServiceImplTest {

  @Mock
  BoardDetailDAO boardDetailDAO;

  @InjectMocks
  private BoardDetailService boardDetailService = new boardDetailServiceImpl();

  private List<BoardFileDTO> fileList = new ArrayList<>();

  private DownloadUtil downloadUtil = new DownloadUtil();


  @Before
  public void setup() {

  }


  //게시물 키값의 존재유무
  @Test
  public void boardNoTest() {

    when(boardDetailDAO.boardExist("3")).thenReturn(200); //
    when(boardDetailDAO.boardExist("")).thenReturn(400); //

    assertEquals(200, boardDetailService.boardExist("3")); //키값이 존재할때 통과
    assertEquals(400, boardDetailService.boardExist("")); //키값이 존재하지않을때 통과

  }

  //게시물의 존재유무
  @Test
  public void checkBoardDetail() throws Exception {

    when(boardDetailDAO.checkBoardDetail("3")).thenReturn(1); //
    when(boardDetailDAO.checkBoardDetail("")).thenReturn(0); //

    assertEquals(200, boardDetailService.checkBoardDetail("3")); //게시글이 존재할때 통과
    assertEquals(400, boardDetailService.checkBoardDetail("")); //게시글이 존재하지 않을때 통과

  }

  //게시물 조회
  @Test
  public void selectBoardDetail() throws Exception {
    BoardDTO boardDTO = new BoardDTO();

    String boardNo = "1";

    when(boardDetailDAO.selectBoardDetail(boardNo)).thenReturn(boardDTO);

    assertNotNull(boardDetailService.selectBoardDetail(boardNo)); //게시글이 정상적으로 조회되면 통과
    assertNull(boardDetailService.selectBoardDetail("")); // 게시글이 조회되지 않으면 통과

  }

  //조회수 증가 테스트
  @Test
  public void viewCountUpdateTest() throws Exception {

    when(boardDetailDAO.updateViewCount("3")).thenReturn(1); //조회수가 정상적으로 업데이트됨(1행)
    when(boardDetailDAO.updateViewCount("")).thenReturn(0); //조회수가 업데이트되지않음

    assertEquals(1, boardDetailService.updateViewCount("3")); // 게시물이 업데이트 메서드 통과하면 1되서 반환되면 통과
    assertEquals(0, boardDetailService.updateViewCount("")); // 게시물이 업데이트되지않아 0반환되면 통과

  }

  //첨부파일조회 테스트
  @Test
  public void checkFileExist() throws Exception {

    fileList.add(new BoardFileDTO());

    when(boardDetailDAO.selectUploadFile("2")).thenReturn(fileList); // 게시물조회 리스트반환

    List<BoardFileDTO> fileList2 = new ArrayList<>();
    when(boardDetailDAO.selectUploadFile("")).thenReturn(fileList2); //  게시물 조회 리스트 반환

    assertEquals(1, boardDetailService.selectUploadFile("2").size()); //리스트사이즈를 1로 반환하면 통과
    assertEquals(0, boardDetailService.selectUploadFile("").size()); //반환값이 0이면 통과
  }

  //이미지파일조회 테스트
  @Test
  public void checkImageFileExist() throws Exception {
    //작성전
  }

  //파일다운로드 테스트
  @Test
  public void fileDownloadTest() throws Exception {

    HttpServletResponse response = new MockHttpServletResponse();
    String fileName = "random.txt";
    String filePath= "c:" + File.separator + "Temp" + File.separator + "1" + File.separator;

    downloadUtil.download(response,fileName,filePath); // 다운로드가 진행되어 에러가 없이 정상진행되면 통과

  }
  //게시물 답변 가능여부
  @Test
  public void checkReplyBoardTest() throws Exception {


    when(boardDetailDAO.checkBoardReply("2")).thenReturn(2);
    when(boardDetailDAO.checkBoardReply("4")).thenReturn(1);


    assertEquals(400,boardDetailService.checkBoardReply("2")); // 답변불가 반환시 통과
    assertEquals(200,boardDetailService.checkBoardReply("4")); // 답변가능 반환시 통과

  }

}
