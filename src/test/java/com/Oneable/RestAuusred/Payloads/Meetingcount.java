package com.Oneable.RestAuusred.Payloads;

public class Meetingcount {
	private String userPrincipalName;
	private String startDate;
	private String endDate;

	public String getuserPrincipalName() {
		return userPrincipalName;
	}

	public void setuserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getstartDate() {
		return startDate;
	}

	public void setstartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getendDate() {
		return endDate;
	}

	public void setendDate(String endDate) {
		this.endDate = endDate;
	}

}
