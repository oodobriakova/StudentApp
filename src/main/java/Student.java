
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

	public class Student{
		private String studentName;
		private Curriculum curriculum;
		private LocalDateTime startDateTime;

		
		public Student(String studentName, Curriculum curriculum, LocalDateTime startDateTime) {
			this.studentName = studentName;
			this.curriculum = curriculum;
			this.startDateTime = startDateTime;
		}

		
	public String getStudentName() {
		return studentName;
	}

	
	public Curriculum getCurriculum() {
		return curriculum;
	}

	
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	
	
	public LocalDateTime getEndDateTime(LocalDateTime startDateTime, Curriculum curriculum) {
		int remainingHours = curriculum.getTotalDurationHrs(); 
        final int hoursPerDay = 8; 
        LocalDateTime endDateTime = startDateTime;

        while (remainingHours > 0) {
            // Check if it's a work day 
            if (endDateTime.getDayOfWeek() != DayOfWeek.SATURDAY && endDateTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
                // Check if it's within working hours
                if (endDateTime.getHour() >= 10 && endDateTime.getHour() < 18) {
                    int hoursToAdd = Math.min(hoursPerDay, remainingHours); // Add 8 hours or remaining hours, whichever is smaller
                    endDateTime = endDateTime.plusHours(hoursToAdd);
                    remainingHours -= hoursToAdd;
                } else {
                    // Move to the next day at 10 am 
                	endDateTime = endDateTime.plusDays(1).withHour(10).withMinute(0);
                }
            } else {
                // Move to the next day at 10 am
            	endDateTime = endDateTime.plusDays(1).withHour(10).withMinute(0);
            }
        }

        return endDateTime;
    }
	
    
        public void getProgress(LocalDateTime startDateTime, Curriculum curriculum) {
        	LocalDateTime currentDateTime = LocalDateTime.now();
        	LocalDateTime endDateTime = getEndDateTime(startDateTime, curriculum); 
        	
        	int diff1 = currentDateTime.compareTo(startDateTime);
        	int diff2 = currentDateTime.compareTo(endDateTime); 
        	
        	if (diff1 >= 0) { //training has been started
                if (diff2 < 0) { //training not completed
                    printProgressNotFinished(startDateTime, curriculum);
                } else { //training is completed
                    printProgressCompleted(startDateTime, curriculum);
                }
            } else {
                printProgressBeforeStart(startDateTime, curriculum);
            }
        }
        
        
        //internal helper counts of duration between to dates
        public long calculateTotalHours(LocalDateTime startTimePoint, LocalDateTime endTimePoint) {
            long totalHours = Duration.between(startTimePoint, endTimePoint).toHours();
            long minutes = Duration.between(startTimePoint, endTimePoint).toMinutesPart();
            
            if (minutes >= 30) {
                totalHours++;
            }
            return totalHours;
        }
        
        //internal helper to print amount of time in appropriate format
        void printProgressMessage(long totalHours, String description) {
            if (totalHours / 24 != 0) {
                System.out.printf("%d d ", totalHours / 24);
            }
            if (totalHours % 24 != 0) {
                System.out.printf("%d h ", totalHours % 24);
            }
            System.out.printf("%s.%n", description);
        }
        
        
        
        // counts amount of time until course completion
        public long getElapsedTime(LocalDateTime startDateTime, Curriculum curriculum) { 
        	LocalDateTime endDateTime = getEndDateTime(startDateTime, curriculum);
            return calculateTotalHours(LocalDateTime.now(), endDateTime);
        }
        
        //prints message with amount of time until course completion
        void printProgressNotFinished(LocalDateTime startDateTime, Curriculum curriculum) {
            long totalHours = getElapsedTime(startDateTime, curriculum);
            System.out.print("Training is not finished. ");
            printProgressMessage(totalHours, "left until the end");
        }
        
        
        //counts how much time has passed since course completion
        public long getTimeAfterCompletion(LocalDateTime startDateTime, Curriculum curriculum) { 
        	LocalDateTime endDateTime = getEndDateTime(startDateTime, curriculum);
            return calculateTotalHours(endDateTime, LocalDateTime.now());
        }
        
        
        //prints message with amount of time after course completion
        public void printProgressCompleted(LocalDateTime startDateTime, Curriculum curriculum) {
            long totalHours = getTimeAfterCompletion(startDateTime, curriculum);
            System.out.print("Training completed. ");
            printProgressMessage(totalHours, "passed since the end");
        }
        
        
        //counts amount of time till beginning of course
        public long getTimeBeforeStart(LocalDateTime startDateTime, Curriculum curriculum) {
            return calculateTotalHours(LocalDateTime.now(), startDateTime);
        }
        
        
      //prints message with amount of time until course starts
        public void printProgressBeforeStart(LocalDateTime startDateTime, Curriculum curriculum) {
            long totalHours = getTimeBeforeStart(startDateTime, curriculum);
            System.out.print("Training is not started yet. ");
            printProgressMessage(totalHours, "before start");
        }
        
	}