package studentDTO;

import java.sql.Date;

public class studentDTO {
int studenID;
String studentName;
Date dob;
String email;
String password;
int courseID;
public int getStudenID() {
	return studenID;
}
public void setStudenID(int studenID) {
	this.studenID = studenID;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getCourseID() {
	return courseID;
}
public void setCourseID(int courseID) {
	this.courseID = courseID;
}


}
