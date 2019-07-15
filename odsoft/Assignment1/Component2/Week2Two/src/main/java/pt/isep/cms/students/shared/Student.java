package pt.isep.cms.students.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {
	public String id;
  public String firstName;
  public String lastName;
  public String emailAddress;
  //public String gender;
  //public String birthdate;
	
	public Student() {}
	
	public Student(String id, String firstName, String lastName, String emailAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		//this.gender = gender;
		//this.birthdate = birthdate;
	}
	
	public StudentDetails getLightWeightStudent() {
	  return new StudentDetails(id, getFullName());
	}
	
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getEmailAddress() { return emailAddress; }
  public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
 // public String getGender() { return gender; }
  //public void setGender(String gender) { this.gender = gender; }
  //public String getBirthdate() { return birthdate; }
  //public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
  public String getFullName() { return firstName + " " + lastName; }
}
