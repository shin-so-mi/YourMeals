package com.oijoa.service;

import java.util.List;
import com.oijoa.domain.Board;

public interface BoardService {
  int delete(int no) throws Exception;
  int add(Board board) throws Exception;
  List<Board> list() throws Exception;
  List<Board> list(String keyword) throws Exception;
  Board get(int no) throws Exception;
  int update(Board board) throws Exception;
}
