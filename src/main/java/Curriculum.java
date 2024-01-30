
public class Curriculum {
		private String curriculumName;
		private Course[] courses;
		
		public Curriculum(String curriculumName, Course[] courses) {
			this.curriculumName = curriculumName;
			this.courses = courses;
		}

		public String getCurriculumName() {
			return curriculumName;
		}
		
		public Course[] getCourses() {
			return courses;
		}
		
		public int getTotalDurationHrs() {
			int totalDurationHrs = 0;
			for(Course course : courses) {
				totalDurationHrs += course.getCourseDurationHrs();
			}
			return totalDurationHrs;
		}
			
	}

