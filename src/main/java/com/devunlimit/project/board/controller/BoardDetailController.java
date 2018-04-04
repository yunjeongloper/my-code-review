package com.devunlimit.project.board.controller;

import com.devunlimit.project.board.domain.dto.BoardDTO;
import com.devunlimit.project.board.domain.dto.BoardFileDTO;
import com.devunlimit.project.board.domain.dto.ReplyDTO;
import com.devunlimit.project.board.service.BoardUpdateService;
import com.devunlimit.project.board.service.ReplyService;
import com.devunlimit.project.boardDetail.service.BoardDetailService;
import com.devunlimit.project.util.DownloadUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardDetailController {

  @Autowired
  private BoardDetailService boardDetailService;

  @Autowired
  private ReplyService replyService;

  @Autowired
  private BoardUpdateService boardUpdateService;

  @RequestMapping(value = "/detailView.do",method = RequestMethod.GET)
  public ModelAndView detail(String boardNum, String parentNum,HttpSession session)
      throws Exception {

    ModelAndView mav = new ModelAndView();

    try {

      boardDetailService.updateViewCount(boardNum);
      BoardDTO boardDetail = boardDetailService.selectBoardDetail(boardNum);
      List<BoardFileDTO> fileList = boardDetailService.selectUploadFile(boardNum);
      int replyOk = boardDetailService.checkBoardReply(parentNum, boardNum);

      List<ReplyDTO> replyDTOList = replyService.selectList(boardNum);
      int listLength = replyDTOList.size();
      int deletedNum = replyService.deletedReplyNum(boardNum);

      mav.addObject("boardNum", boardNum);
      mav.addObject("listLength", listLength);
      mav.addObject("replyDTOList", replyDTOList);
      mav.addObject("deletedNum",deletedNum);

      mav.addObject("boardDetail",boardDetail);
      mav.addObject("fileList",fileList);
      mav.addObject("replyOk",replyOk);
      mav.addObject("userId",boardUpdateService.loginId(session.getAttribute("memberNo").toString()));

      mav.setViewName("board/detail");


    } catch (Exception e) {

      e.printStackTrace();
      mav.setViewName("redirect:/board/list.do");
    }

    return mav;
  }

  @RequestMapping(value = "/fileDownload.do")
  public void fileDownload(HttpServletRequest request, HttpServletResponse response, String fileNum){

    BoardFileDTO boardFileDTO = boardDetailService.selectFileDetail(fileNum);

    DownloadUtil downloadUtil = new DownloadUtil();

    String fullPath = "";
    fullPath = boardFileDTO.getUrl() + boardFileDTO.getSaveName();

    try {
      downloadUtil.download(request,response,boardFileDTO.getOriginName(),fullPath);
    } catch (Exception e) {
      e.printStackTrace();
    }


  }


}
