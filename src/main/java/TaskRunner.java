import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskRunner{

	public static void main(String[] args) {
		
		
		//Setting courses data
		Course[] javaDeveloper = {
				new Course("Java.", 16),
				new Course("JDBC.", 24),
				new Course("Springa.", 16)
		};
		Course[] aqe = {
				new Course("Test Design.", 10),
				new Course("Page Object.", 16),
				new Course("Selenium.", 16)
		};
		
		
		//Setting curriculums data
		Curriculum javaDevCurriculum = new Curriculum("Java Developer", javaDeveloper);
		Curriculum aqeCurriculum = new Curriculum("AQE", aqe);
		
		
		//Setting launch date (via input)
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter curriculum launch date (yyyy-MM-dd): ");
        String userInput = scanner.nextLine();
        
        
        //Parse the input string to a LocalDate and define required output format 
        LocalDate startDate = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(10, 0)); //we assume that each course starts at 10 am

		
		//Setting students data
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Ivanov Ivan", javaDevCurriculum, startDateTime));
        students.add(new Student("Sidorov Ivan", aqeCurriculum, startDateTime));
        
        
        ReportGenerator reportGenerator = new ReportGenerator(students);
        
        //Displaying report based on user input
        System.out.println("Report has been generated!");
        System.out.print("Press 0 or just skip to open short version: ");
        String userInput2 = scanner.nextLine().trim();
        
        	if (userInput2.isEmpty() || userInput2.equals("0")) {
        		reportGenerator.generateShortReport();
        	} else {
        		reportGenerator.generateLongReport();
        } 
        	
		scanner.close();
}
        }