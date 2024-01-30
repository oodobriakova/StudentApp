
public class Course {
	private String courseName;
	private int courseDuration;
	

	public Course(String courseName, int courseDuration) {
		this.courseName = courseName;
		this.courseDuration = courseDuration;
	}


	public String getCourseName() {
		return courseName;
	}


	public int getCourseDurationHrs() {
		return courseDuration;
	}

}
