package util.VO;
public class CommentVO {
	
//	�� �ȿ��� ����� ����
	private int commentseq ;
//	���,���� �ۼ���
	private String writer ;
//	����
	private String content ;
//	����� �ִ� �ش� ���� ���� ��ȣ
	private int boardseq ;
//	�ۼ�����
	private String regdate ;
//	��۾ȿ��� ������ ����
	private int recommentseq ;
//	���, ������ ���� ��ȣ
	private int compk;
//	���, ������ �θ�
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
