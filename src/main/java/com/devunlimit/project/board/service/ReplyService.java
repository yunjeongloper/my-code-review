package com.devunlimit.project.board.service;

import com.devunlimit.project.board.domain.dto.ReplyDTO;
import java.util.List;

public interface ReplyService {

  List<ReplyDTO> selectList(String boardNum);

  int insertReply(ReplyDTO replyDTO);

  int deleteReply(String replyNo);

  int updateReply(String replyNo, String reContent);

}
