package com.venu.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venu.library.Model.Issuedetails;
import com.venu.library.Model.Returndetails;

@Repository
public interface ReturnRepository extends JpaRepository<Returndetails, Long> {
	
	Returndetails findByIssuedetails(Issuedetails issueDetails);

}
