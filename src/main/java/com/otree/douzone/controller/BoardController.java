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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.otree.douzone.dto.Board;
import com.otree.douzone.dto.BoardComment;
import com.otree.douzone.dto.BoardFile;
import com.otree.douzone.service.BoardCommentService;
import com.otree.douzone.service.BoardFileService;
import com.otree.douzone.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardCommentService boardCommentService;
	
	@Autowired
	private BoardFileService boardFileService;
	
	
	//������ �Խ��� ��� Ŭ��������
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = null;
		int boardCount = boardService.getBoardCount();
		boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board"; 
	}
	
	//�Խñ� ��������
	@GetMapping("/getBoardDetail")
	public String getBoardDetail(@RequestParam("boardId") int boardId,Model model) {
		Board board = boardService.getBoardByBoardId(boardId);
		List<BoardComment> boardCommentList = boardCommentService.getCommentList(boardId);
		List<BoardFile> boardFile = boardFileService.getFile(boardId);
		model.addAttribute("boardDetail",board);
		model.addAttribute("boardFileList", boardFile);
		model.addAttribute("boardCommentList",boardCommentList);
		return "boarddetail"; 
	}
	
	//detail���� ��� �������� 
	@GetMapping("/getBoardList2")
	public String getBoardList2() {
		return "redirect:getBoardList"; 
	}
	
	//�۾��� ��ư ��������
	@GetMapping("/createBoard")
	public String createBoard() {
		return "boardregisterform"; 
	}
		
	// �۾��, ����÷�� �� ��� ��ư �������� 
	@PostMapping("/createBoard")
	public String createBoard2( Board board /*BoardFile boardfile*/) {
		boolean result = false;
		String pathResult = null;
		result = boardService.createBoard(board);
//		boardFileService.createFile(boardfile);
		if (result==true) {
			pathResult = "redirect:getBoardList";
		}
		else {
			pathResult = "boardregisterform";
		}
		return pathResult; 
		
	}
	
	//board detial ���� update ��ư ��������
	@GetMapping("/updateBoard")
	public String modifyBoard(Model model,@RequestParam("boardId") int boardId) {
		Board board = boardService.getBoardByBoardId(boardId);
		model.addAttribute("board", board);
		return "boardupdateform"; 
	}
	
	//update form���� �����Ϸ� �������� 
	@PostMapping("/updateBoardOk")
	public String modifyBoard2(Board board,Model model) {
		String param = Integer.toString(board.getBoardId());
		boolean result = false;
		String pathResult = null;
		result = boardService.modifyBoard(board);
		if (result==true) {
			pathResult = "redirect:getBoardDetail?boardId="+param; //������ redirect:boarddetail
			model.addAttribute("modifyBoard",boardService.getBoardByBoardId(board.getBoardId()));   
		}
		else {
			pathResult = "boardupdateform"; //���н�
		}
		return pathResult;
	}
	
	//board detail���� delete ��ư ��������
	@GetMapping("/deleteBoard")
	public String removeBoard(@RequestParam("boardId") int boardId) {
		String param = Integer.toString(boardId); 
		boolean result = false;
		String pathResult = null;
		result = boardService.removeBoard(boardId);
		if(result==true) {
			pathResult = "redirect:getBoardList"; //������
		}
		else {
			pathResult = "redirect:getBoardDetail?boardId="+param; //���н� 
		}
		return pathResult; 
	}
}









