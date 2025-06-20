package com.javaex.vo;

public class GuestbookVO {
	//필드
	private int guestbookId;
	private String name;
	private String password;
	private String content;
	private String regDate;
	
	//생성자
	public GuestbookVO() {
		super();
	}
	
	public GuestbookVO(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
	}
	
	public GuestbookVO(String name, String password, String content, String regDate) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}
	
	public GuestbookVO(int guestbookId, String name, String password, String content) {
		super();
		this.guestbookId = guestbookId;
		this.name = name;
		this.password = password;
		this.content = content;
	}
	
	public GuestbookVO(int guestbookId, String name, String password, String content, String regDate) {
		super();
		this.guestbookId = guestbookId;
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}
	
	//메소드gs
	public int getGuestbookId() {
		return guestbookId;
	}
	
	public void setGuestbookId(int guestbookId) {
		this.guestbookId = guestbookId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	//메소드일반
	@Override
	public String toString() {
		return "GuestbookVO [guestbookId=" + guestbookId + ", name=" + name + ", password=" + password + ", content="
				+ content + ", regDate=" + regDate + "]";
	}
	
}
