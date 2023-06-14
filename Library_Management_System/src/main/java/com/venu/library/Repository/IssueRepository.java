package com.venu.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venu.library.Model.Issuedetails;

@Repository
public interface IssueRepository extends JpaRepository<Issuedetails, Long> {

}
