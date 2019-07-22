package util.VO;
public class CommentVO {
	
//	글 안에서 댓글의 순서
	private int commentseq ;
//	댓글,대댓글 작성자
	private String writer ;
//	내용
	private String content ;
//	댓글이 있는 해당 글의 고유 번호
	private int boardseq ;
//	작성일자
	private String regdate ;
//	댓글안에서 대댓글의 순서
	private int recommentseq ;
//	댓글, 대댓글의 고유 번호
	private int compk;
//	댓글, 대댓글의 부모
	private String parent ;
	
	public int getCommentseq() {
		return commentseq;
	}
	public void setCommentseq(int commentseq) {
		this.commentseq = commentseq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(int boardseq) {
		this.boardseq = boardseq;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getRecommentseq() {
		return recommentseq;
	}
	public void setRecommentseq(int recommentseq) {
		this.recommentseq = recommentseq;
	}
	public int getCompk() {
		return compk;
	}
	public void setCompk(int compk) {
		this.compk = compk;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "CommentVO [commentseq=" + commentseq + ", writer=" + writer + ", content=" + content + ", boardseq="
				+ boardseq + ", regdate=" + regdate + ", recommentseq=" + recommentseq + ", compk=" + compk
				+ ", parent=" + parent + "]";
	}
} // class
