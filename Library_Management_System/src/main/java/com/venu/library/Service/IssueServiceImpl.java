package com.venu.library.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venu.library.Model.Issuedetails;
import com.venu.library.Repository.IssueRepository;

@Service
public class IssueServiceImpl implements IssueService {
	
	@Autowired
	private IssueRepository issueRepository;
	
	public IssueServiceImpl(IssueRepository issueRepository) {
		super();
		this.issueRepository = issueRepository;
	}

	public void saveIssue(Issuedetails issuedetails) {
		issueRepository.save(issuedetails);
	
	}


	public List<Issuedetails> getAllDetailsById(long id) {
		List<Issuedetails> detail=issueRepository.findAll();
		List<Issuedetails> detailById=new ArrayList<Issuedetails>();
		
		for(Issuedetails i:detail) {
			if(id==i.getUser().getId()) {
				detailById.add(i);
			}
		}
		return detailById;
	}


	public List<Issuedetails> getAllDetails() {
		List<Issuedetails> details=issueRepository.findAll();
		return details;
	}


	
	public Issuedetails findById(long id) {
		// TODO Auto-generated method stub
		Optional<Issuedetails> detail=issueRepository.findById(id);
		return detail.get();
	}

}
