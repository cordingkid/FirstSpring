package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeVO {
	private int no;			//일반 게시판 번호
	private String subject;     //일반 게시판 제목
	private String writer;    //일반 게시판 작성자
	private String content;   //일반 게시판 내용
	private String regDate;      //일반 게시판 작성일
	private int hit;          //일반 게시판 조회수
}
