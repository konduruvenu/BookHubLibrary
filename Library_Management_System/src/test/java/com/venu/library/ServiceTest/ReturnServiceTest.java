package com.venu.library.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.venu.library.Model.Issuedetails;
import com.venu.library.Model.Returndetails;
import com.venu.library.Repository.ReturnRepository;
import com.venu.library.Service.ReturnServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReturnServiceTest {
	
	@Mock
	private ReturnRepository mockReturnRepo;

	private ReturnServiceImpl returnService;

	@BeforeEach
	void setUp() throws Exception {
		returnService = new ReturnServiceImpl(mockReturnRepo);
	}
	
	@Test
	void getAllDetails_test() {
		Returndetails detail = new Returndetails();
		Returndetails detail2 = new Returndetails();
		List<Returndetails> details = new ArrayList<>();
		details.add(detail);
		details.add(detail2);
		when(mockReturnRepo.findAll()).thenReturn(details);
		List<Returndetails> details2 = returnService.getAllDetails();
		verify(mockReturnRepo,times(1)).findAll();
		assertEquals(details, details2);
	}
	
	@Test
	void findByIssueDetails_test() {
		Returndetails detail = new Returndetails();
		Issuedetails detail2=new Issuedetails();
		when(mockReturnRepo.findByIssuedetails(detail2)).thenReturn(detail);
		Returndetails testDetail = returnService.findByIssueDetails(detail2);
		verify(mockReturnRepo, times(1)).findByIssuedetails((detail2));
		assertEquals(detail, testDetail);
	}
	
	@Test
	void saveReturnDetails_test() {
		Returndetails detail = new Returndetails();
		returnService.saveReturnDetails(detail);
		verify(mockReturnRepo, times(1)).save(detail);
	}


}
