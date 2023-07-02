package com.otree.douzone.service;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otree.douzone.dao.BoardCommentDao;
import com.otree.douzone.dao.BoardDao;
import com.otree.douzone.dao.BoardFileDao;
import com.otree.douzone.dao.EmpDao;
import com.otree.douzone.dto.Board;
import com.otree.douzone.dto.BoardComment;
import com.otree.douzone.dto.BoardFile;
import com.otree.douzone.dto.Emp;


@Service
public class BoardCommentService {

	SqlSession sqlsession;
	@Autowired
	public BoardCommentService(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
		//C
		// �Խñ� ����ȸ �� ��� �ۼ��� 
		public boolean createComment(BoardComment boardcomment) {
			boolean result = false;
			try {
				BoardCommentDao boardCommentDao = sqlsession.getMapper(BoardCommentDao.class);
				boardCommentDao.insertComment(boardcomment);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return result;
		}
			
		//R
		// �Խñ� ����ȸ�� DB�� ����ִ� ������� ��������
		public List<BoardComment> getCommentList(int boardId) {
			List<BoardComment> listBoardComment = null;
			try {
				BoardCommentDao boardCommentDao = sqlsession.getMapper(BoardCommentDao.class);
				listBoardComment  = boardCommentDao.getCommentList(boardId);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return listBoardComment;
		}
		
		//R
		//��� ID�� ��۰�������
		public BoardComment getComment(int commentId) {
			BoardComment BoardComment = null;
			try {
				BoardCommentDao boardCommentDao = sqlsession.getMapper(BoardCommentDao.class);
				BoardComment  = boardCommentDao.getComment(commentId);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return BoardComment;
		}
		
		
		//U
		//�Խñ� ����ȸ �� ������ �ִ� ��� ����
		public boolean modifyComment(String boardComment, int commentId) {
			boolean result = false;
			try {
				BoardCommentDao boardCommentDao = sqlsession.getMapper(BoardCommentDao.class);
				boardCommentDao.updateComment(boardComment,commentId);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return result;
		}
		
		
		//D
		//�Խñ� ����ȸ �� ���� ��� ����
		public boolean removeComment(int CommentId) {
			boolean result = false;
			try {
				BoardCommentDao boardCommentDao = sqlsession.getMapper(BoardCommentDao.class);
				boardCommentDao.deleteComment(CommentId);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return result;
		}
	
	
}
