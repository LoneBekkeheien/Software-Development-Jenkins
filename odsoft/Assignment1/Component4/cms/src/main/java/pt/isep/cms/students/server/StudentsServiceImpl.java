package pt.isep.cms.students.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.students.client.StudentsService;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

@SuppressWarnings("serial")
public class StudentsServiceImpl extends RemoteServiceServlet implements
    StudentsService {

  private static final String[] studentsFirstNameData = new String[] {
      "Hollie", "Emerson", "Healy", "Brigitte", "Elba", "Claudio",
      "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred",
      "Candice", "Louise", "Emilio", "Geneva", "Heriberto", "Bulrush", 
      "Abigail", "Chad", "Terry", "Bell"};
  
  private final String[] studentsLastNameData = new String[] {
      "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
      "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
      "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard", 
      "Louis", "Andrews", "English", "Snedden"};

  
  private final String[] studentsGender = new String[] {
	      "male", "male", "female", "male", "female", "female",
	      "male", "male", "male", "female", "female", "male",
	      "female", "male", "male", "female", "male", "female", 
	      "male", "female", "male", "female"};
  
  //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
  
  private final String[] studentsBirthDates = new String[] {
	      "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963",
	      "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963",
	      "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963", 
	      "22-05-1963", "22-05-1963", "22-05-1963", "22-05-1963",};
  
  private final HashMap<String, Student> students = new HashMap<String, Student>();
  
  
  //Date[] studentsBirthDates = new Date[stringDates.length];
  
  public StudentsServiceImpl() {
    initStudents();
  }
  
  private void initStudents() {
    // TODO: Create a real UID for each student
    //
    for (int i = 0; i < studentsFirstNameData.length && i < studentsLastNameData.length  && i < studentsGender.length && i < studentsBirthDates.length ; ++i) {
	    /*
    	try {
			studentsBirthDates[i] = formatter.parse(stringDates[i]);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
      Student student = new Student(String.valueOf(i), studentsFirstNameData[i], studentsLastNameData[i], studentsGender[i], studentsBirthDates[i]);
      students.put(student.getId(), student); 
    }
  }
  
  public Student addStudent(Student student) {
    student.setId(String.valueOf(students.size()));
    students.put(student.getId(), student); 
    return student;
  }

  public Student updateStudent(Student student) {
	  //String id=student.getId();
    students.remove(student.getId());
    students.put(student.getId(), student); 
    return student;
  }

  public Boolean deleteStudent(String id) {
    students.remove(id);
    return true;
  }
  
  public ArrayList<StudentDetails> deleteStudents(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deleteStudent(ids.get(i));
    }
    
    return getStudentDetails();
  }
  
  public ArrayList<StudentDetails> getStudentDetails() {
    ArrayList<StudentDetails> studentDetails = new ArrayList<StudentDetails>();
    
    Iterator<String> it = students.keySet().iterator();
    while(it.hasNext()) { 
      Student student = students.get(it.next());          
      studentDetails.add(student.getLightWeightStudent());
    }
    
    return studentDetails;
  }

  public Student getStudent(String id) {
    return students.get(id);
  }
}
