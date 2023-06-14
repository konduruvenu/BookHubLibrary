package com.venu.library.Service;

import java.util.List;

import com.venu.library.Model.Issuedetails;
import com.venu.library.Model.Returndetails;

public interface ReturnService {
	
	List<Returndetails> getAllDetails();
	
	List<Returndetails> getAllDetails(long id);
    
	void saveReturnDetails(Returndetails returndetails);
	
	Returndetails findByIssueDetails(Issuedetails issueDetails);
}
