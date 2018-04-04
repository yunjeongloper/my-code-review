package com.devunlimit.project.board.domain.dao;

import com.devunlimit.project.board.domain.dto.ReplyDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyDAO {

  List<ReplyDTO> selectList(@Param("boardNum") String boardNum);

  int insertReply(@Param("reply") ReplyDTO replyDTO);

  int updateReplyParents(@Param("replyNo") String replyNo);

  int deleteReply(@Param("replyNo") String replyNo);

  int updateReply(@Param("replyNo") String replyNo, @Param("reContent") String reContent);

}
