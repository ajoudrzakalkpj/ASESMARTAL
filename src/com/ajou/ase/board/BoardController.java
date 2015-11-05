package com.ajou.ase.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.ase.common.RequestParameter;
import com.ajou.ase.common.Utils;
import com.ajou.ase.board.Board;
import com.ajou.ase.board.BoardServiceImpl;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardServiceImpl boardService;

	//11.6. - 봉재 - 처음 게시물 등록을 위한 로직
	@RequestMapping("/board/board_write.do")
	public ModelAndView boardWrite(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		System.out.println("-------------/board/board_write.do()--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		Board board = new Board();
		
		board.setBoardSubject(rp.get("boardSubject").toString());
		board.setBoardWriter(rp.get("boardWriter").toString());
		board.setBoardContents(rp.get("boardContents").toString());
		board.setBoardOpenPolicy(rp.get("boardOpenPolicy").toString());
		board.setBoardFinishedTime(rp.get("boardFinishedTime").toString());
		board.setBoardServerAdmin(rp.get("boardServerAdmin").toString());
		
		
		int ret = this.boardService.saveCheck(board);
		
		System.out.println("ret = "+ ret);
		
		
		if(ret > 0){
			map.put("success", "Success to register new post");
		}else{
			map.put("fail", "Fail to register new post");
		}
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	//11.6. - 봉재 - 게시물중 아직 종료되지 않은 게시물의 리스트를 가져오는 로직
	@RequestMapping("/board/board_loadunfinishedpostlist.do")
	public ModelAndView loadUnfinishedPostList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");

		System.out.println("-------------/board/board_loadunfinishedpostlist.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		ArrayList<Board> boardList = (ArrayList<Board>) this.boardService.getUnfinishedPostList(rp);
		
		System.out.println("boardlist = " + boardList);
		
		if(boardList.size() == 0){					
			map.put("fail", "There is no confirmation waiting member.");
		}else{ 
			map.put("success", boardList);
		}
		
		System.out.println("map =" +map);
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	//11.6. - 봉재 - 선택된 게시물의 내용을 가져오는 로직
	@RequestMapping("/board/board_view.do")
	public ModelAndView boardView(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		
		System.out.println("-------------/board/board_view.do--------------"); 
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Board board = (Board) this.boardService.getObject(rp);
		

		if(board != null){
			map.put("success", board);
		}else{
			map.put("fail", "Fail to bring existing Post");
		}
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	//11.6. - 봉재 - 선택된 게시물을 삭제하는 로직
	@RequestMapping("/board/board_remove.do")
	public ModelAndView boardRemove(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		
		System.out.println("-------------/board/board_remove.do--------------"); 
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();

		this.boardService.remove(rp);

	//	rp.put("boardRootUpper", rp.get("boardSeqNum").toString());

		map.put("success", "success");
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	//11.6. - 봉재 - 게시물을 업데이트 하는 로직
	@RequestMapping("/board/board_update.do")
	public ModelAndView boardUpdate(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		System.out.println("-------------/board/board_update.do()--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		Board board = new Board();
		board.setBoardNumSeq(Integer.parseInt(rp.get("boardNumSeq").toString()));
		board.setBoardSubject(rp.get("boardSubject").toString());
		board.setBoardWriter(rp.get("boardWriter").toString());
		board.setBoardContents(rp.get("boardContents").toString());
		board.setBoardOpenPolicy(rp.get("boardOpenPolicy").toString());
		board.setBoardFinishedTime(rp.get("boardFinishedTime").toString());
		
		boolean ret = this.boardService.update(board);
		
		System.out.println("ret = "+ ret);
		
		
		if(ret){
			map.put("success", "Success to update your post");
		}else{
			map.put("fail", "Fail to update your post");
		}
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	//11.6. - 봉재 - 사용자와 관련된 게시물의 목록을 가져오는 로직 
	@RequestMapping("/board/board_loadallpostlist.do")
	public ModelAndView boardloadallpostlist(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		
		System.out.println("-------------/board/board_loadallpostlist.do--------------");
		System.out.println("rp = "+ rp);		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		Map<String, String> param = new HashMap<String, String>();
		
		rp.put("starting", Integer.parseInt(rp.get("start").toString()));
		rp.put("ending", Integer.parseInt(rp.get("end").toString()));
		
		ArrayList<Board> boardList = (ArrayList<Board>) this.boardService.getList(rp);
	
		if(boardList != null){
			/*
			userMap.put("userSeq", user.getUserNumSeq());
			userMap.put("userEmail", user.getUserEmail1());
			userMap.put("userName", user.getUserName());
			*/
			
			//세션처리 - html5 sessionStorage 이용

			map.put("success", boardList);
		}else{
			map.put("fail", "Fail to bring the list");
		}
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	//11.6. - 봉재 - 게시물의 페이징을 위해 사용자와 관련된 게시물의 수를 파악하는 로직
	@RequestMapping("/board/board_count.do")
	public ModelAndView boardCount(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		
		System.out.println("-------------/board/board_count.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		int count = this.boardService.getBoardCount(rp);
		
		System.out.println("count = "+ count);
		
		if(count > 0)
			map.put("success", count);
		else
			map.put("fail", 1);
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
}
