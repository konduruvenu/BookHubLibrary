package com.venu.library.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venu.library.Model.Issuedetails;
import com.venu.library.Model.Returndetails;
import com.venu.library.Repository.ReturnRepository;

@Service
public class ReturnServiceImpl implements ReturnService {

	@Autowired
	private ReturnRepository returnRepository;

	public List<Returndetails> getAllDetails() {

		List<Returndetails> alldetails = returnRepository.findAll();
		return alldetails;
	}

	@Override
	public List<Returndetails> getAllDetails(long id) {
		List<Returndetails> detail = returnRepository.findAll();
		List<Returndetails> detailById = new ArrayList<Returndetails>();

		for (Returndetails i : detail) {
			if (id == i.getIssuedetails().getUser().getId()) {
				detailById.add(i);
			}
		}
		return detailById;
	}

	@Override
	public void saveReturnDetails(Returndetails returndetails) {
		// TODO Auto-generated method stub
		returnRepository.save(returndetails);

	}

	@Override
	public Returndetails findByIssueDetails(Issuedetails issueDetails) {
		// TODO Auto-generated method stub
		Returndetails detail = returnRepository.findByIssuedetails(issueDetails);
		return detail;
	}

	public ReturnServiceImpl(ReturnRepository returnRepository) {
		super();
		this.returnRepository = returnRepository;
	}

}
