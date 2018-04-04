package com.devunlimit.project.board.controller;

import com.devunlimit.project.board.domain.dto.ReplyDTO;
import com.devunlimit.project.board.service.ReplyService;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class ReplyController {

  @Autowired
  private ReplyService replyService;

  @RequestMapping(value = "/replyList.do", method = RequestMethod.GET)
  public ModelAndView replyList(String boardNum, HttpSession session) {

    ModelAndView mav = new ModelAndView();

    List<ReplyDTO> replyDTOList = replyService.selectList(boardNum);

    int listLength = replyDTOList.size();

    Date write_date = new Date(new java.util.Date().getTime());

    mav.addObject("listLength", listLength);
    mav.addObject("replyDTOList2", replyDTOList);
    mav.addObject("memberNo", session.getAttribute("memberNo"));
    mav.addObject("write_date", write_date);

    mav.setViewName("board/detail");

    return mav;

  }


  @RequestMapping(value = "/replyInsert.do", method = RequestMethod.GET)
  public ModelAndView replyInsert() {

    ModelAndView mav = new ModelAndView();

    mav.setViewName("board/detail");

    return mav;

  }


  @RequestMapping(value = "/replyInsert.do", method = RequestMethod.POST)
  @ResponseBody
  public Map replyInsert(@ModelAttribute ReplyDTO replyDTO, HttpSession session) {

    Map<String, String> status = new HashMap<>();

    // default 999
    status.put("result", "999");

    try {

      int resultBoolean = replyService.insertReply(replyDTO);

      // 성공하면 1
      status.put("result", String.valueOf(resultBoolean));

    } catch (IllegalStateException failLength) {

      // 실패하면 0
      status.put("result", "0");

      status.put("message", failLength.getMessage());
    }

    return status;

  }


  @RequestMapping(value = "/replyDelete.do", method = RequestMethod.GET)
  public ModelAndView replyDelete(String replyNo) {

    ModelAndView mav = new ModelAndView();

    int resultBoolean = replyService.deleteReply(replyNo);

    mav.addObject("result",String.valueOf(resultBoolean));

    mav.setViewName("board/detail");

    return mav;

  }


  @RequestMapping(value = "/replyUpdate.do", method = RequestMethod.GET)
  public ModelAndView replyUpdate(String replyNo, String reContent) {

    ModelAndView mav = new ModelAndView();

    try {

      // 성공하면 1
      int resultBoolean = replyService.updateReply(replyNo, reContent);

      mav.addObject("result", String.valueOf(resultBoolean));

    } catch (IllegalStateException failLength) {

      // 실패하면 0
      mav.addObject("result", "0");

    }

    mav.setViewName("board/detail");

    return mav;

  }

}
