package com.venu.library.Service;

import java.util.List;

import com.venu.library.Model.Issuedetails;

public interface IssueService {
	
	void saveIssue(Issuedetails issuedetails);
	
	List<Issuedetails> getAllDetailsById(long id); 
	
	List<Issuedetails> getAllDetails();
	
	Issuedetails findById(long id);

}
