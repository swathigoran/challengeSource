package com.att.servcie;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.att.Constants.AttConstants;
import com.att.Model.AttResponse;
import com.att.Model.Comments;
import com.att.Model.Repositories;
import com.att.Model.RepositoryIssues;
@Service
public class AttServcieImpl implements AttServcieI{
	private final RestTemplate restTemplate;
	HttpHeaders headers=new HttpHeaders();
	public AttServcieImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}
	@Override
	public List<AttResponse> getIssuesAndComments() throws Exception {
		
		headers.set("Authorization",authentication());
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		// This holds final response which includes repository name for which it has issues and comments associated.
		List<AttResponse> attResponse=new ArrayList<>();
		ResponseEntity<List<Repositories>> repositories= restTemplate.exchange(AttConstants.GET_ALL_REPOSITORIES, HttpMethod.GET, entity,new ParameterizedTypeReference <List <Repositories>> (){});
		List<Repositories> ListOfrepositories=repositories.getBody();
		for(int i=0;i<ListOfrepositories.size();i++)
		{
			AttResponse response=new AttResponse();
			response.setRepository(ListOfrepositories.get(i).getName());
			List<RepositoryIssues> issues=getAllIssesForRepository(ListOfrepositories.get(i).getName());
			response.setIssues(issues);	
			attResponse.add(response);
		}
		
		return attResponse;
	}
	private List<RepositoryIssues> getAllIssesForRepository(String name) {
		// TODO Auto-generated method stub
		//https://api.github.com/repos/att/vfd/issues?q=isopen
		headers.set("Authorization",authentication());
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		String finalUrl=AttConstants.BASE_URL+name+AttConstants.TAIL_URL;
		List<Comments> comments=new ArrayList<>();
		List<RepositoryIssues> finalListOfRepositoryIssues=new ArrayList<>();
		ResponseEntity<List<RepositoryIssues>> issues= restTemplate.exchange(finalUrl, HttpMethod.GET, entity,  new ParameterizedTypeReference <List <RepositoryIssues>> (){});
		List<RepositoryIssues> listOfRepositoryIssues=issues.getBody();
		for(RepositoryIssues repoIssue :listOfRepositoryIssues )
		{
			//RepositoryIssues reposiryIssue= new RepositoryIssues();
			comments=getAllCommentsForIssue(repoIssue.getComments_url());
			comments.stream().map(x->x.getId()).collect(Collectors.toList());
			repoIssue.setTotalComments(comments.size());
			repoIssue.setCommentId(comments.stream().map(x->x.getId()).collect(Collectors.toList()));
			finalListOfRepositoryIssues.add(repoIssue);
		}
	
		return finalListOfRepositoryIssues;
		
		
	}
	private List<Comments> getAllCommentsForIssue(String comments_url) {
		// TODO Auto-generated method stub
		headers.set("Authorization",authentication());
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		ResponseEntity<List<Comments>> comments= restTemplate.exchange(comments_url, HttpMethod.GET, entity,  new ParameterizedTypeReference <List <Comments>> (){});
		List<Comments> listOfComments=comments.getBody();
		return listOfComments;	
	}
	
	public String authentication()
	{
		String auth=AttConstants.USER_NAME+":"+AttConstants.PASSWORD;
		byte[] encodedAuth=Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		return authHeader;
	}
	
	
}
