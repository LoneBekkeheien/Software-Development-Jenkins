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

  private static final String[] contactsFirstNameData = new String[] {
      "Hollie", "Emerson", "Healy", "Brigitte", "Elba", "Claudio",
      "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred",
      "Candice", "Louise", "Emilio", "Geneva", "Heriberto", "Bulrush", 
      "Abigail", "Chad", "Terry", "Bell"};
  
  private final String[] contactsLastNameData = new String[] {
      "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
      "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
      "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard", 
      "Louis", "Andrews", "English", "Snedden"};
  
  private final String[] contactsGender = new String[] {
      "mark@example.com", "hollie@example.com", "boticario@example.com",
      "emerson@example.com", "healy@example.com", "brigitte@example.com",
      "elba@example.com", "claudio@example.com", "dena@example.com",
      "brasilsp@example.com", "parker@example.com", "derbvktqsr@example.com",
      "qetlyxxogg@example.com", "antenas_sul@example.com",
      "cblake@example.com", "gailh@example.com", "orville@example.com",
      "post_master@example.com", "rchilders@example.com", "buster@example.com",
      "user31065@example.com", "ftsgeolbx@example.com"};
      
  private final HashMap<String, Student> contacts = new HashMap<String, Student>();

  public StudentsServiceImpl() {
    initStudents();
  }
  
  private void initStudents() {
    // TODO: Create a real UID for each contact
    //
    for (int i = 0; i < contactsFirstNameData.length && i < contactsLastNameData.length && i < contactsGender.length; ++i) {
    	Student contact = new Student(String.valueOf(i), contactsFirstNameData[i], contactsLastNameData[i], contactsGender[i]);
      contacts.put(contact.getId(), contact); 
    }
  }
  
  public Student addStudent(Student contact) {
    contact.setId(String.valueOf(contacts.size()));
    contacts.put(contact.getId(), contact); 
    return contact;
  }

  public Student updateStudent(Student contact) {
	  String lid=contact.getId();
    contacts.remove(contact.getId());
    contacts.put(contact.getId(), contact); 
    return contact;
  }

  public Boolean deleteStudent(String id) {
    contacts.remove(id);
    return true;
  }
  
  public ArrayList<StudentDetails> deleteStudents(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deleteStudent(ids.get(i));
    }
    
    return getStudentDetails();
  }
  
  public ArrayList<StudentDetails> getStudentDetails() {
    ArrayList<StudentDetails> contactDetails = new ArrayList<StudentDetails>();
    
    Iterator<String> it = contacts.keySet().iterator();
    while(it.hasNext()) { 
      Student contact = contacts.get(it.next());          
      contactDetails.add(contact.getLightWeightStudent());
    }
    
    return contactDetails;
  }

  public Student getStudent(String id) {
    return contacts.get(id);
  }
}
