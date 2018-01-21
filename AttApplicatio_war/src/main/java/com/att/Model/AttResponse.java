package com.att.Model;

import java.util.List;

public class AttResponse {
private String repository;
private List<RepositoryIssues> issues;
public String getRepository() {
	return repository;
}
public void setRepository(String repository) {
	this.repository = repository;
}
public List<RepositoryIssues> getIssues() {
	return issues;
}
public void setIssues(List<RepositoryIssues> issues) {
	this.issues = issues;
}
@Override
public String toString() {
	return "AttResponse [repository=" + repository + ", issues=" + issues + "]";
}

}
