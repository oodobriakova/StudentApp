import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class CurriculumTest {

    @Test
    void testGetTotalDurationHrs() {
        // Create a mock for the Course class
        Course mockCourse1 = mock(Course.class);
        Course mockCourse2 = mock(Course.class);
        Course mockCourse3 = mock(Course.class);

        // Define the behaviour of the mock courses
        when(mockCourse1.getCourseDurationHrs()).thenReturn(10);
        when(mockCourse2.getCourseDurationHrs()).thenReturn(5);
        when(mockCourse3.getCourseDurationHrs()).thenReturn(32);

        // Create a Curriculum with mock courses
        Course[] mockCourses = {mockCourse1, mockCourse2, mockCourse3};
        Curriculum curriculum = new Curriculum("Test Curriculum", mockCourses);

        // Call the method being tested
        int totalDurationHrs = curriculum.getTotalDurationHrs();

        // Assert the result
        assertEquals(47, totalDurationHrs);
    }
}