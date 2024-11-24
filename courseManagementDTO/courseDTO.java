package courseManagementDTO;

import courseManagementDAO.courseDAO;

public class courseDTO {
private int courseId;
private String courseName;
private int fees;
private int duration;
private String category;
private String faculty;
private Boolean ITCategory;
private String Pref;

public String getPref() {
	return Pref;
}
public void setPref(String pref) {
	Pref = pref;
}
public Boolean getITCategory() {
	return ITCategory;
}
public void setITCategory(Boolean iTCategory) {
	ITCategory = iTCategory;
}
public String getFaculty() {
	return faculty;
}
public void setFaculty(String faculty) {
	this.faculty = faculty;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public int getDuration() {
	return duration;
}
public int getFees() {
	return fees;
}
public void setFees(int string) {
	this.fees = string;
}
public void setDuration(int string) {
	this.duration = string;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
@Override
public String toString() {
	return "courseDTO [courseId=" + courseId + ", courseName=" + courseName + ", duration=" + duration + ", category="
			+ category + "]";
}

}
