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
public class BoardService {

	SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	//Board table ���

	//(C)�� ����
	public boolean createBoard(Board board) {
		boolean result = false;
		try {
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			boardDao.insertBoard(board);
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();		
		}
		return result;
	}
	
	
	
	//(R)�Խ��� �� ��ü���� ��ȸ, ����¡ ó�� ��ȸ
	
	
	
	//(R) ����¡ ���� ��ü ��ȸ
	public List<Board> getBoardList() {
		List<Board> boardList = null;
		try {
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			boardList = boardDao.getBoardList();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();		
		}
		return boardList;
		}
	
	
	//(R) boardTitle�� �Խù� �˻�
		public List<Board> getBoardListByBoardTitle(String boardTitle){
		List<Board> listBoard = null;
		try {
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			listBoard = boardDao.searchBoardByBoardTitle(boardTitle);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();		
		}
		return null;
		}
	
	
	// (R) boardId�� ����ȸ ( �Խñ� Ŭ��)
		public Board getBoardByBoardId(int boardId) {
		Board board = null;
		try {
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			board = boardDao.getBoardByBoardId(boardId);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();		
		}
		return board;
	}
		
		
		// (U) �Խñ� ���� �� ��ȸ �� ������ư ����.
		public boolean modifyBoard(Board board) {
			boolean result = false;
			try {
				BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
				boardDao.updateBoard(board);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return result;
		}
	
	//(D) ����
	public boolean removeBoard(int boardId) {
		boolean result = false;
		try {
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			boardDao.deleteBoard(boardId);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	//Boardfile ���
	
	//(C) �۾��� �Ҷ� ���� ����Ʈ �Ķ���� �Է¹ް� ���� ����.
	// ���⼭ ����÷�� ���� ��ƾߵ�.
	public boolean insertFile(List<BoardFile> list) {
		boolean result = false;
		try {
			BoardFileDao boardFileDao = sqlsession.getMapper(BoardFileDao.class);
			boardFileDao.insertFile(list);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	//(R)
	// �Խ��� �󼼺��� ������ �� ���� ����Ʈ ��������
	public List<BoardFile> getFile(int boardId)  {
		List<BoardFile> listBoardFile = null;
		try {
			BoardFileDao boardFileDao = sqlsession.getMapper(BoardFileDao.class);
			listBoardFile = boardFileDao.getFile(boardId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return listBoardFile;
	}
	
	//(U)
	// �۾� ������ ���� ��  �Խ��� �󼼺��� ���� �� ÷������ ������Ʈ(����)
	public boolean updateFile(BoardFile boardfile)  {
		boolean result = false;
		List<BoardFile> listBoardFile = null;
		try {
			BoardFileDao boardFileDao = sqlsession.getMapper(BoardFileDao.class);
			boardFileDao.updateFile(boardfile);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	//(D)
	// �۾� ������ ���� �� �Խ��� �󼼺��� ���� �� �� ����
	public boolean deleteFile(int fileId)  {
		boolean result = false;
		try {
			BoardFileDao boardFileDao = sqlsession.getMapper(BoardFileDao.class);
			boardFileDao.deleteFile(fileId);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	//BoardComment ���
	//C
	// �Խñ� ����ȸ �� ��� �ۼ��� 
	public boolean insertComment(BoardComment boardcomment) {
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
	public List<BoardComment> getComment(int boardId) {
		List<BoardComment> listBoardComment = null;
		try {
			BoardCommentDao boardCommentDao = sqlsession.getMapper(BoardCommentDao.class);
			listBoardComment  = boardCommentDao.getComment(boardId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return listBoardComment;
	}
	
	//U
	//�Խñ� ����ȸ �� ������ �ִ� ��� ����
	public boolean updateComment(String boardComment, int commentId) {
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
	public boolean deleteComment(int boardId) {
		boolean result = false;
		try {
			BoardCommentDao boardCommentDao = sqlsession.getMapper(BoardCommentDao.class);
			boardCommentDao.deleteComment(boardId);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	
	
	//���� 
	public boolean updateEmp(Emp emp) {
		boolean result = false;
		try {
			EmpDao empDao = sqlsession.getMapper(EmpDao.class);
			empDao.updateEmp(emp);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteEmp(int empno) {
		boolean result = false;
        try {
        	EmpDao empDao = sqlsession.getMapper(EmpDao.class);
            empDao.deleteEmp(empno);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public boolean insertEmp(Emp emp) {
		boolean result = false;
		try {
			EmpDao empDao = sqlsession.getMapper(EmpDao.class);
			empDao.insertEmp(emp);
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public List<Emp> searchEmpByName(String name) {
		List<Emp> empList = null;
		try {
			EmpDao empDao = sqlsession.getMapper(EmpDao.class);
			empList = empDao.searchEmpByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
	
}
