import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReportGenerator {
	ArrayList<Student> students;
	Curriculum curriculum;
	LocalDateTime startDateTime;
		
	
	public ReportGenerator(ArrayList<Student> students) {
		this.students = students;
	}
	
	
	public ArrayList<Student> getStudent() {
		return students;
	}


	public Curriculum getCurriculum() {
		return curriculum;
	}


	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void generateShortReport () {
		LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedCurrentDate = currentDateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, EEEE, HH:mm"));
        System.out.println();
		System.out.println("Short (Generating report date - " + formattedCurrentDate + "):");
		System.out.println();
		
		for (Student student : students) {
		System.out.print("\u2022 " + student.getStudentName() + " (" + student.getCurriculum().getCurriculumName() + ") - ");
		student.getProgress(student.getStartDateTime(), student.getCurriculum());
		System.out.println();
		}
	}
	
	
	public void generateLongReport () {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, EEEE, HH:mm");
        String formattedCurrentDate = currentDateTime.format(formatter);
        System.out.println();
		System.out.println("Full (Generating report date - " + formattedCurrentDate + "):");
		System.out.println();
		
		for (Student student : students) {
		System.out.println("\u2022 " + student.getStudentName());
		System.out.println("	Curriculum name: " + student.getCurriculum().getCurriculumName() + ";");
		System.out.println("	Duration: " + student.getCurriculum().getTotalDurationHrs() + "h;" );
		String formattedStartDate = student.getStartDateTime().format(formatter);
		System.out.println("	Start date: " + formattedStartDate + ";" );
		String formattedEndDate = student.getEndDateTime(student.getStartDateTime(), student.getCurriculum()).format(formatter);
		System.out.println("	End date: " + formattedEndDate + ";" );
		System.out.print("	");
		student.getProgress(student.getStartDateTime(), student.getCurriculum());
		System.out.println();

		}
	}
	
}