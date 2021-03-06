package com.oijoa.service;

import java.util.List;
import com.oijoa.dao.NoticeDao;
import com.oijoa.domain.Notice;

public class DefaultNoticeService implements NoticeService {

  NoticeDao noticeDao;

  public DefaultNoticeService(NoticeDao noticeDao) {
    this.noticeDao = noticeDao;
  }

  // @Override
  // public int delete(int no) throws Exception {
  // return boardDao.delete(no);
  // }
  //
  // @Override
  // public int add(Board board) throws Exception {
  // return boardDao.insert(board);
  // }
  //
  @Override
  public List<Notice> list() throws Exception {
    return noticeDao.findAll(null);
  }

  @Override
  public int add(Notice notice) throws Exception {
    return noticeDao.add(notice);
  }

  // @Override
  // public List<Order> list(String keyword) throws Exception {
  // return orderDao.findAll(keyword);
  // }
  //
  // @Override
  // public Board get(int no) throws Exception {
  // Board board = boardDao.findByNo(no);
  // if (board != null) {
  // boardDao.updateViewCount(no);
  // }
  // return board;
  // }
  //
  // @Override
  // public int update(Board board) throws Exception {
  // return boardDao.update(board);
  // }

}
