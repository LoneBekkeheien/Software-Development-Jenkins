package pt.isep.cms.students;

import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;
import pt.isep.cms.students.server.StudentsServiceImpl;

import java.util.ArrayList;
import junit.framework.TestCase;

public class StudentsServiceImplJRETest extends TestCase {
	private StudentsServiceImpl studentsServiceImpl;
	
	protected void setUp() {
		studentsServiceImpl = new StudentsServiceImpl();
	}
	
	// Test StudentsServiceImpl > addStudent
	public void testAddStudent() {
		Student student = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		studentsServiceImpl.addStudent(student);
		Student studentTest = studentsServiceImpl.getStudent(student.getId());
		assertEquals(student, studentTest);
	}
	
	// Test StudentsServiceImpl > updateStudent
	public void testUpdateStudent() {
		Student student = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		studentsServiceImpl.addStudent(student);
		student = studentsServiceImpl.getStudent(student.getId());
		student.setFirstName("Name2");
		student.setLastName("Name2");
		student.setGender("Gender2");
		student.setBirthDate("Birthdate2");
		studentsServiceImpl.updateStudent(student);
		
		Student studentTest = studentsServiceImpl.getStudent(student.getId());
		
		assertEquals(student, studentTest);
	}
	
	// Test StudentsServiceImpl > deleteStudent
	public void testDeleteStudent() {
		Student student = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		studentsServiceImpl.addStudent(student);
		int arraySizeBefore = studentsServiceImpl.getStudentDetails().size();
		studentsServiceImpl.deleteStudent(student.getId());
		int arraySizeAfter = studentsServiceImpl.getStudentDetails().size();
		assertTrue(arraySizeAfter < arraySizeBefore);
	}
	
	// Test StudentsServiceImpl > getStudent
	public void testGetStudent() {
		Student student = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		studentsServiceImpl.addStudent(student);
		Student studentTest = studentsServiceImpl.getStudent(student.getId());
		assertEquals(student, studentTest);
	}
	
	// Test StudentsServiceImpl > deleteStudents
	public void testDeleteStudents() {		
		Student student = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		Student student2 = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		studentsServiceImpl.addStudent(student);
		studentsServiceImpl.addStudent(student2);
		
		int arraySizeBefore = studentsServiceImpl.getStudentDetails().size();
		
		ArrayList<String> ids = new ArrayList<String>();
		ids.add(student.getId());
		ids.add(student2.getId());
				
		int arraySizeIds = ids.size();
		
		int arraySizeAfter = studentsServiceImpl.deleteStudents(ids).size();
	
		assertTrue((arraySizeBefore - arraySizeIds) == arraySizeAfter);
	}
	
	// Test StudentsServiceImpl > getStudentDetails
	public void testGetStudentDetails() {
	    
		Student student = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		Student student2 = new Student("0", "firstName", "lastName", "Gender", "Birthdate");
		studentsServiceImpl.addStudent(student);
		studentsServiceImpl.addStudent(student2);
		
		ArrayList<StudentDetails> studentDetails = studentsServiceImpl.getStudentDetails();
	
		ArrayList<String> idsFromStudentDetails = new ArrayList<String>();
		for (StudentDetails cd : studentDetails) {
			idsFromStudentDetails.add(cd.getId());
		}
		
	    assertTrue(idsFromStudentDetails.contains(student.getId()));
	    assertTrue(idsFromStudentDetails.contains(student2.getId()));
	}
}
