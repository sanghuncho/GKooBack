package motherj.noticeModule;

import java.time.LocalDate;

public class Notice {
	
	private int noticeNumber;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private LocalDate noticeDate;
	private int noticeHit;
	
	public Notice() {}
	
	public Notice(int number, String title, String content, String writer, LocalDate date, int hit) {
		noticeNumber = number;
		noticeTitle = title;
		noticeContent = content;
		noticeWriter = writer;
		noticeDate = date;
		noticeHit = hit;
	}
	
	public int getNoticeNumber() {
		return noticeNumber;
	}
	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public LocalDate getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(LocalDate noticeDate) {
		this.noticeDate = noticeDate;
	}
	public int getNoticeHit() {
		return noticeHit;
	}
	public void setNoticeHit(int noticeHit) {
		this.noticeHit = noticeHit;
	}
}
