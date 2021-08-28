package com.smspck.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.smspck.model.Student;
import java.util.List;


@Service
public interface StudentService {	
	
	//to display the list of student
	List<Student> getAllStudents();
	
	//to save a new student
	void saveStudent(Student student );  
	
	//to delete a old student
	void deleteStudentById (long id );  
	
	//to edit a particular student
	Student getStudentByID(long id);  
	
	//for pageing & sorting
	Page<Student> findPaginated(int pageNo, int pageSixe, String sortField, String sortDirection);

}
