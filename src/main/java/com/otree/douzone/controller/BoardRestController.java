package com.otree.douzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otree.douzone.dto.*;
import com.otree.douzone.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardRestController {

	private BoardService boardService;
	
	@Autowired
	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	
	@PostMapping
	public ResponseEntity<String> createBoard(@RequestBody Board board) {
		try {
			System.out.println("insert ����");
			boardService.createBoard(board);
			return new ResponseEntity<String>("create success", HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>("create fail", HttpStatus.BAD_REQUEST);
	}
	}
	
	// �׳���ü��������
	@GetMapping
	public ResponseEntity<List<Board>> getBoardList() {
		List<Board> boardList = null;
		try {
			boardList = boardService.getBoardList();
			return new ResponseEntity<List<Board>>(boardList,HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<List<Board>>(boardList,HttpStatus.BAD_REQUEST);
	}
	}
	
//	// ����¡ ó���Ѱ� ��������.
//	@GetMapping
//	public ResponseEntity<List<Emp>> getBoardListForPaging() {
//		List<Emp> empList = empService.getEmpList();
//		System.out.println(empList);
//		return ResponseEntity.status(HttpStatus.OK).body(empList);
//	}
//	
	//boardTitle�� �˻��ϱ�
	@GetMapping("/{boardTitle}")
	public ResponseEntity<List<Board>> getBoardListByBoardTitle(@PathVariable("boardTitle") String boardTitle) {
		List<Board> boardList = null;
		
		try {
	    	boardList = boardService.getBoardListByBoardTitle(boardTitle);
	    	System.out.println(boardList);
			return new ResponseEntity<List<Board>>(boardList,HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<List<Board>>(boardList,HttpStatus.BAD_REQUEST);
	}
	}
	
	
	@PutMapping
	public ResponseEntity<String> modifyBoard(@RequestBody Board board) {
		try {
			boardService.modifyBoard(board);
			return new ResponseEntity<String>("modify success", HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>("modify fail", HttpStatus.BAD_REQUEST);
	}
	}
	
	
	@DeleteMapping
	public ResponseEntity<String> deleteBoard(@PathVariable("boardId") int boardId) {
		try {
			boardService.removeBoard(boardId);
			return new ResponseEntity<String>("delete success", HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>("delete fail", HttpStatus.BAD_REQUEST);
	}
}
	
	
	
}
