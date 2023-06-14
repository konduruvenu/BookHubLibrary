package com.venu.library.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.venu.library.Model.Issuedetails;
import com.venu.library.Repository.IssueRepository;
import com.venu.library.Service.IssueServiceImpl;

@ExtendWith(MockitoExtension.class)
public class IssueServiceTest {
	
	@Mock
	private IssueRepository mockIssueRepo;

	private IssueServiceImpl issueService;

	@BeforeEach
	void setUp() throws Exception {
		issueService = new IssueServiceImpl(mockIssueRepo);
	}
	
	@Test
	void getAllDetails_test() {
		Issuedetails detail = new Issuedetails();
		Issuedetails detail2 = new Issuedetails();
		List<Issuedetails> details = new ArrayList<>();
		details.add(detail);
		details.add(detail2);
		when(mockIssueRepo.findAll()).thenReturn(details);
		List<Issuedetails> details2 = issueService.getAllDetails();
		verify(mockIssueRepo,times(1)).findAll();
		assertEquals(details, details2);
	}
	
	@Test
	void findById_test() {
		Issuedetails detail = new Issuedetails();
		when(mockIssueRepo.findById(1L)).thenReturn(Optional.of(detail));
		Issuedetails testDetail = issueService.findById(1L);
		verify(mockIssueRepo, times(1)).findById((1L));
		assertEquals(detail, testDetail);
	}
	
	@Test
	void saveIssue_test() {
		Issuedetails detail = new Issuedetails();
		issueService.saveIssue(detail);
		verify(mockIssueRepo, times(1)).save(detail);
	}

}
