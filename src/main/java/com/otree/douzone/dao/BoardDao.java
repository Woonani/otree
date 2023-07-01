package com.otree.douzone.dao;

import java.sql.SQLException;
import java.util.List;

import com.otree.douzone.dto.Board;


public interface BoardDao {
		void insertBoard(Board board) throws ClassNotFoundException, SQLException; // �Խù� ����
		int getBoardCount(String field, String query) throws ClassNotFoundException, SQLException; // �Խù� ���� ��������, ����¡ ó���� �ʿ��ѵ�
		
		List<Board> getBoardList() throws ClassNotFoundException, SQLException; //ù��° ȭ�� �Խù� ��������
		
		List<Board> getBoardListForPaging(int page, String field, String query) throws ClassNotFoundException, SQLException; // ����¡�� ��ü �Խù� ��������
		List<Board> getBoardByBoardTitle(String boardTitle) throws ClassNotFoundException, SQLException; // boardTitle�� �Խù� �˻�
		Board getBoardByBoardId(int boardId) throws ClassNotFoundException, SQLException; // boardId�� �Խù� �󼼺���
	    void updateBoard(Board board ) throws ClassNotFoundException, SQLException; // �Խù� ������Ʈ
	    void deleteBoard(int boardId) throws ClassNotFoundException, SQLException; // �Խù� ����
}
