package com.otree.douzone.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.otree.douzone.dto.Board;
import com.otree.douzone.dto.BoardComment;
import com.otree.douzone.dto.BoardCommentVO;
import com.otree.douzone.dto.BoardFile;
import com.otree.douzone.dto.Workspace;
import com.otree.douzone.dto.WorkspaceTeamUser;
import com.otree.douzone.service.BoardCommentService;
import com.otree.douzone.service.BoardFileService;
import com.otree.douzone.service.BoardService;
import com.otree.douzone.service.TeamRoleService;
import com.otree.douzone.service.WorkspaceService;
import com.otree.douzone.util.DateFormatter;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workspace")
public class BoardController {
	
	private final BoardService boardService;	
	private final BoardCommentService boardCommentService;	
	private final BoardFileService boardFileService;	
	private final WorkspaceService workspaceService;	
	private final TeamRoleService teamRoleService;
	private final HttpSession session;
	
	//왼쪽의 게시판 배너 클릭했을때
	@GetMapping("/{workspaceId}/board")
	public String getBoardList(@PathVariable("workspaceId") int workspaceId, Model model) {
		//Boardlist 뿌리기 위한 정보들
		

		List<Board> boardList = null;
		boardList = boardService.getBoardList(workspaceId);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("workspaceId", workspaceId);

		
		// Workspace 관련 정보들
		Workspace selectedWorkspace = workspaceService.getWorkspaceById(workspaceId);
		List<WorkspaceTeamUser> teamUserList = teamRoleService.getWorkspaceTeamList(workspaceId);
		WorkspaceTeamUser owner = null;
		for(WorkspaceTeamUser user : teamUserList) {
			if(user.getRoleId()==3) {
				owner = user;
				break;
			}
		}
		model.addAttribute("selectedWorkspace", selectedWorkspace);
		model.addAttribute("teamUserList", teamUserList);
		model.addAttribute("owner", owner);
		model.addAttribute("pageType", "board");
		return "board"; 
	}
	
	
	//게시글 눌렀을때
	@GetMapping("/{workspaceId}/getBoardDetail")
	public String getBoardDetail(@RequestParam("boardId") int boardId,Model model, @PathVariable("workspaceId") int workspaceId, HttpServletRequest request) {
		boardService.modifyBoardReadCount(boardId); //readcount+1
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId"); //userId 가져오기
		Board board = boardService.getBoardByBoardId(boardId); //board 가져오기
		board.setUserId(userId); // session에서 가져온 userId
		List<BoardCommentVO> boardCommentListVO = boardCommentService.getCommentListVO(boardId);
		List<BoardFile> boardFile = boardFileService.getFile(boardId);
		model.addAttribute("boardDetail",board);
		model.addAttribute("boardFileList", boardFile);
		model.addAttribute("boardCommentList",boardCommentListVO);
		return "boarddetail"; 
	}
	
	
	//글쓰기 버튼 눌렀을때
	@GetMapping("/{workspaceId}/createBoard")
	public String createBoard(@PathVariable("workspaceId") int workspaceId, Model model) {
		model.addAttribute("workspaceId", workspaceId);
		return "boardregisterform"; 
	}
		
	// 글쓰고 등록버튼 눌렀을때
	@PostMapping("/{workspaceId}/createBoard")
	public String createBoard2( Board board ,@PathVariable("workspaceId")int workspaceId, HttpServletRequest request ) {
		boolean result = false;
		String pathResult = null;
		board.setWorkspaceId(workspaceId); //url parameter로 받은 workspaceId set
		HttpSession session = request.getSession(); 
		int userId = (int) session.getAttribute("userId"); //userId받기
		board.setUserId(userId);
		result = boardService.createBoard(board);
		 String path = Integer.toString(workspaceId);
		if (result==true) {
			pathResult = "redirect:/workspace/"+path+"/board";
		}
		else {
			pathResult = "boardregisterform";
		}
		return pathResult; 
		
	}
	
	//board detial 에서 update 버튼 눌렀을때
	@GetMapping("/{workspaceId}/updateBoard")
	public String modifyBoard(Model model, @RequestParam("boardId")int boardId ) {
		Board board = boardService.getBoardByBoardId(boardId);
		model.addAttribute("board", board);
		return "boardupdateform"; 
	}
	
	//update form에서 수정하기 눌렀을때 
	@PostMapping("/{workspaceId}/updateBoardOk")
	public String modifyBoard2(Board board,Model model) {
		String param = Integer.toString(board.getBoardId());
		boolean result = false;
		String pathResult = null;
		result = boardService.modifyBoard(board);
		if (result==true) {
			pathResult = "redirect:getBoardDetail?boardId="+param; //성공시 redirect:boarddetail
			model.addAttribute("modifyBoard",boardService.getBoardByBoardId(board.getBoardId()));   
		}
		else {
			pathResult = "boardupdateform"; //실패시
		}
		return pathResult;
	}
	
	//board detail에서 delete 버튼 눌렀을때
	@GetMapping("/{workspaceId}/deleteBoard")
	public String removeBoard(@RequestParam("boardId") int boardId, @PathVariable("workspaceId")int workspaceId) {
		String param = Integer.toString(boardId); 
		boolean result = false;
		String pathResult = null;
		result = boardService.removeBoard(boardId);
		String path = Integer.toString(workspaceId);
		if(result==true) {
			pathResult = "redirect:/workspace/"+path+"/board"; //성공시
		}
		else {
			pathResult = "redirect:getBoardDetail?boardId="+param; //실패시 
		}
		return pathResult; 
	}
}









