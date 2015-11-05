package com.ajou.ase.board;

public class Board {
	private int boardNumSeq;
	private String boardSubject;
	private String boardWriter;	//외래키 userName
	private String boardContents;
	private String boardOpenPolicy;
	private String boardUpdateTime;
	private String boardFinishedTime;
	private String boardServerAdmin; // 외래키 userServerAdmin
	
	public int getBoardNumSeq() {
		return boardNumSeq;
	}
	public void setBoardNumSeq(int boardNumSeq) {
		this.boardNumSeq = boardNumSeq;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}
	public String getBoardOpenPolicy() {
		return boardOpenPolicy;
	}
	public void setBoardOpenPolicy(String boardOpenPolicy) {
		this.boardOpenPolicy = boardOpenPolicy;
	}
	public String getBoardUpdateTime() {
		return boardUpdateTime;
	}
	public void setBoardUpdateTime(String boardUpdateTime) {
		this.boardUpdateTime = boardUpdateTime;
	}
	public String getBoardFinishedTime() {
		return boardFinishedTime;
	}
	public void setBoardFinishedTime(String boardFinishedTime) {
		this.boardFinishedTime = boardFinishedTime;
	}
	public String getBoardServerAdmin() {
		return boardServerAdmin;
	}
	public void setBoardServerAdmin(String boardServerAdmin) {
		this.boardServerAdmin = boardServerAdmin;
	}
	
}
