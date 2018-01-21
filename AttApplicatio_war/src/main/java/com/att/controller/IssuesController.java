package com.att.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.att.Model.AttResponse;
import com.att.servcie.AttServcieI;

@RestController
public class IssuesController {
	@Autowired
	private AttServcieI attServcie;
	
	@RequestMapping(value="/opneIssues", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttResponse>> getAllOpneIssues()
	{
		List<AttResponse> attResponse= new ArrayList<>();
		try {
			attResponse=attServcie.getIssuesAndComments();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<AttResponse>>(attResponse, HttpStatus.OK);
	}
}
