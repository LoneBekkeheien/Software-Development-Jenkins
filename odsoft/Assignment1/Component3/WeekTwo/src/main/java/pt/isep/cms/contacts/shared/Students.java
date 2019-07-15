package pt.isep.cms.contacts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Students implements Serializable {
	public String id;
  public String firstName;
  public String lastName;
  public String gender;
  public String birthdate;
	
	public Students() {}
	
	public Students(String id, String firstName, String lastName, String gender, String birthdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate = birthdate;
	}
	
	public ContactDetails getLightWeightContact() {
	  return new ContactDetails(id, getFullName());
	}
	
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public String getGender() { return gender; }
  public void setGender(String gender) { this.gender = gender; }
  public String getBirthdate() { return birthdate; }
  public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
  public String getFullName() { return firstName + " " + lastName; }
}
