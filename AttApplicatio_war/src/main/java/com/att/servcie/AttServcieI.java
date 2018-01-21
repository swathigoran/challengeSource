package com.att.servcie;

import java.util.List;

import com.att.Model.AttResponse;

public interface AttServcieI {
public List<AttResponse> getIssuesAndComments() throws Exception;
}
