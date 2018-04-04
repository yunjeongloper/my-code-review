package com.devunlimit.project.board.service.serviceImpl;

import com.devunlimit.project.board.domain.dao.ReplyDAO;
import com.devunlimit.project.board.domain.dto.ReplyDTO;
import com.devunlimit.project.board.service.ReplyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

  @Autowired
  private ReplyDAO replyDAO;

  // 안쓰일듯. 추후에 수정
  @Override
  public List<ReplyDTO> selectList(String boardNum) {

    List<ReplyDTO> replyDTOList = replyDAO.selectList(boardNum);

    return replyDTOList;
  }

  @Override
  public int insertReply(ReplyDTO replyDTO) {

    checkInfo(replyDTO);

    int insertResult = replyDAO.insertReply(replyDTO);
    int updateResult = 0;

    if( insertResult == 1) {

      updateResult = replyDAO.updateReplyParents(replyDTO.getNo());

    } else {

    }

    return updateResult;
  }

  @Override
  public int deleteReply(String replyNo) {

    int deleteResult = replyDAO.deleteReply(replyNo);

    return deleteResult;
  }

  @Override
  public int updateReply(String replyNo, String reContent) {

    int updateResult = 0;

    if ( reContent.length() == 0 || reContent.isEmpty() ) {

      throw new IllegalStateException("ContentNull");

    } else {

      updateResult = replyDAO.updateReply(replyNo, reContent);

    }

    return updateResult;
  }

  // insert 데이터 유효성 검사
  private void checkInfo(ReplyDTO replyDTO) {
    if ( replyDTO.getBoard_no().length() == 0 || replyDTO.getBoard_no().isEmpty()) {
      throw new IllegalStateException("BoardNumError");
    } else if ( replyDTO.getContent().length() == 0 || replyDTO.getContent().isEmpty() ) {
      throw new IllegalStateException("ContentNullError");
    } else if ( replyDTO.getWriter().length() == 0 || replyDTO.getWriter().isEmpty()) {
      throw new IllegalStateException("WriterNumError");
    } else if ( replyDTO.getWrite_date() == null ) {
      throw new IllegalStateException("DateNullError");
    } else if ( replyDTO.getParents_no().length() == 0 || replyDTO.getParents_no().isEmpty()) {
      throw new IllegalStateException("ParentsNumError");
    }
  }

}
