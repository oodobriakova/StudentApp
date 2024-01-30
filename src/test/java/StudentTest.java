import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StudentTest {
    private static Curriculum mockCurriculum;
    private static LocalDateTime startDateTime;
    private static Student mockStudent;
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
	
	@BeforeAll
	static void setUpData() {
		// Create a mock for Curriculum class
		mockCurriculum = mock(Curriculum.class);
        when(mockCurriculum.getTotalDurationHrs()).thenReturn(25);
        
		mockStudent = new Student("Test Student", mockCurriculum, startDateTime);
		
	    // Redirect System.out to capture printed output
        System.setOut(new PrintStream(outContent));
        
       
	}
	
	@AfterEach
    public void tearDown() {
        // Reset System.out
        System.setOut(originalOut);
    }
	
	
	
	@Nested
	class getEndDateTime {
		
		@Test
		void testGetEndDateTime_WithoutSkippingWeekends() {
			startDateTime = LocalDateTime.of(2024, 1, 23, 10, 0);
			LocalDateTime actual = mockStudent.getEndDateTime(startDateTime, mockCurriculum);
			LocalDateTime expected = LocalDateTime.of(2024, 1, 26, 11, 0);

		    assertEquals(expected, actual);
		}
		
		@Test
		void testGetEndDateTime_WithSkippingWeekends() {
			startDateTime = LocalDateTime.of(2024, 1, 26, 10, 0);
			LocalDateTime actual = mockStudent.getEndDateTime(startDateTime, mockCurriculum);	
			LocalDateTime expected = LocalDateTime.of(2024, 1, 31, 11, 0);

		    assertEquals(expected, actual);
		}
	}
	
	@Nested
	class calculateTotalHours {
		@Test
		void testCalculateTotalHours_MinutesLessThanThirty() {
			LocalDateTime startTimePoint = LocalDateTime.of(2024, 1, 30, 10, 0);
			LocalDateTime endTimePoint = LocalDateTime.of(2024, 1, 30, 11, 29);
			
			long actual = mockStudent.calculateTotalHours(startTimePoint, endTimePoint);
			long expected = 1;
			
			assertEquals(expected, actual);
	}
		
		@Test
		void testCalculateTotalHours_MinutesEqualsThirty() {
			LocalDateTime startTimePoint = LocalDateTime.of(2024, 1, 30, 10, 0);
			LocalDateTime endTimePoint = LocalDateTime.of(2024, 1, 30, 11, 30);
			
			long actual = mockStudent.calculateTotalHours(startTimePoint, endTimePoint);
			long expected = 2;
			
			assertEquals(expected, actual);
	}
		
		@Test
		void testCalculateTotalHours_MinutesMoreThanThirty() {
			LocalDateTime startTimePoint = LocalDateTime.of(2024, 1, 30, 10, 0);
			LocalDateTime endTimePoint = LocalDateTime.of(2024, 1, 30, 11, 31);
			
			long actual = mockStudent.calculateTotalHours(startTimePoint, endTimePoint);
			long expected = 2;
			
			assertEquals(expected, actual);
	}
	}
	
	@Nested
	class getProgress {
		
		@Test
		void testgetProgress_TrainingNotStrted() { 
		startDateTime = LocalDateTime.now().plusDays(1);

       
		mockStudent.getProgress(startDateTime, mockCurriculum);
        String expected = "Training is not started yet. ";
        
        
        assertTrue(outContent.toString().contains(expected));
    }

		@Test
		void testgetProgress_TrainingCompleted() { 
		startDateTime = LocalDateTime.now().minusDays(9);

       
		mockStudent.getProgress(startDateTime, mockCurriculum);
        String expected = "Training completed. ";
        
        
        assertTrue(outContent.toString().contains(expected));
    }
		
		@Test
		void testgetProgress_TrainingNotCompleted() { 
		startDateTime = LocalDateTime.now().minusDays(1);

       
		mockStudent.getProgress(startDateTime, mockCurriculum);
        String expected = "Training is not finished. ";
        
        
        assertTrue(outContent.toString().contains(expected));
    }	
}
		
}
