package com.otree.douzone.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.otree.douzone.dto.Board;
import com.otree.douzone.service.BoardFileService;
import com.otree.douzone.service.BoardService;

@Controller
@RequestMapping("/Board/")
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
		
	//������ �Խ��� ��� Ŭ��������
	// ���ʷ� ���� ���帮��Ʈ.
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> listBoard = null;
		listBoard = boardService.getBoardList();
			
		
		return "boardlist"; 
	}
	
	//�Խñ� ��������
	@GetMapping("/getBoardDetail")
	public String getBoardDetail(@RequestParam("boardId") int boardId,Model model) {
		Board board = boardService.getBoardByBoardId(boardId);
		model.addAttribute("boardDetail",board);
		return "login"; // boarddetail
	}
	
//	//detail���� ��� �������� 
//	// �ʿ��� ������ �ش� ������, ������ �۰���?? �׷� �߰��� �����۾��� ��� �Ǵ°���?
//	@GetMapping("/getBoardList2")
//	public String getBoardList2() {
//		
//		return "login"; //boardlist
//	}
	

	//�۾��� ��ư ��������
	@GetMapping("/createBoard")
	public String createBoard() {
		return "login"; //boardregisterform
	}
		
//	// �۾��, ����÷�� �� ��� ��ư �������� 
//	//form action="" method =post
//	@PostMapping("/createBoard")
//	public String createBoard2(Board board) {
//		boolean result = false;
//		String pathResult = null;
//		result = boardService.createBoard(board);
//	
//		if (result==true) {
//			pathResult = "login"; //������ redirect : /BoardList/getBoardList
//		}
//		else {
//			pathResult = "login"; //���н� boardregisterform
//		}
//		
//		return pathResult; // 
//		
//	}
	
	//board detial ���� update ��ư ��������
	@GetMapping("/updateBoard")
	public String modifyBoard() {
		return "login"; // boardupdateform
	}
	
	//update form���� �����Ϸ� �������� 
	@PostMapping("/updateBoard")
	public String modifyBoard2(Board board,Model model) {
		boolean result = false;
		String pathResult = null;
		result = boardService.modifyBoard(board);
		if (result==true) {
			pathResult = "login"; //������ redirect : /Board/getBoardDetail
			model.addAttribute("modifyBoard",boardService.getBoardByBoardId(board.getBoardId()));   
		}
		else {
			pathResult = "login"; //���н� boardupdateform 
		}
		return pathResult;
	}
	
	//board detail���� delete ��ư ��������
	@GetMapping("/deleteBoard")
	public String removeBoard(@RequestParam("boardId") int boardId) {
		boolean result = false;
		String pathResult = null;
		result = boardService.removeBoard(boardId);
		if(result==true) {
			pathResult = "login"; //������redirect:/Board/getBoardList
		}
		else {
			pathResult = "login"; //���н� boardupdateform
		}
		
		return "pathResult"; 
		
		
	}
	
}









