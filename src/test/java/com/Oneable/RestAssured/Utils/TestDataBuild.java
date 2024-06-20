package com.Oneable.RestAssured.Utils;


import java.io.IOException;

import org.hamcrest.io.FileMatchers.FileStatus;

import com.Oneable.RestAssured.JiraApi.StepDefintion.IssuesAssineeStatus;
import com.Oneable.RestAuusred.Payloads.ChatMessage;
import com.Oneable.RestAuusred.Payloads.Listofissues;
import com.Oneable.RestAuusred.Payloads.ListofissuesStatus;
import com.Oneable.RestAuusred.Payloads.MeetingTime;
import com.Oneable.RestAuusred.Payloads.Meetingcount;
import com.Oneable.RestAuusred.Payloads.RegisterPojo;
import com.Oneable.RestAuusred.Payloads.SprintDetailsPayload;

import io.cucumber.java.Status;

public class TestDataBuild {
	
	public RegisterPojo addpayLoad(String username, String password)
	{
		RegisterPojo reg =new RegisterPojo();
		reg.setUsername(username);
		reg.setpassword(password);
		return reg;	
	}
	public SprintDetailsPayload SprintPayload(String sprintId)
	{
		SprintDetailsPayload sp = new SprintDetailsPayload();
		sp.setSprintId(sprintId);
		return sp;
	}
	public Listofissues IssuesPayload(String assigneeAccountId)
	{
		Listofissues ip = new Listofissues();
		ip.setAssigneeAccountId(assigneeAccountId);
		return ip;
	}
	public ListofissuesStatus statuspayload(String assigneeAccountId,String userStatus ){
		ListofissuesStatus LS =new ListofissuesStatus();
		LS.setAssigneeAccountId(assigneeAccountId);
		LS.setuserstatus(userStatus);
		return LS;
	}
	public MeetingTime Meetingpayload(String userPrincipalName ,String startDate,String endDate ){
		MeetingTime MT =new MeetingTime();
		MT.setuserPrincipalName(userPrincipalName);
		MT.setstartDate(startDate);
		MT.setendDate(endDate);	
		return MT;
	}
	public Meetingcount Meetingcountpayload(String userPrincipalName ,String startDate,String endDate ){
		Meetingcount MC =new Meetingcount();
		MC.setuserPrincipalName(userPrincipalName);
		MC.setstartDate(startDate);
		MC.setendDate(endDate);	
		return MC;
	}
	public ChatMessage ChatMessagepayload(String userPrincipalName ,String startDate,String endDate ){
		ChatMessage CM =new ChatMessage();
		CM.setuserPrincipalName(userPrincipalName);
		CM.setstartDate(startDate);
		CM.setendDate(endDate);	
		return CM;
	}
}
