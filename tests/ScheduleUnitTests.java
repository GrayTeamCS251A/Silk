import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.javafx.tk.Toolkit.Task;

import Entities.Schedule;

public class ScheduleUnitTests {

	@Test
	public void constructingScheduleWithDefaultValues() {

		// create Schedule object
		Schedule tester = new Schedule();

		// assert statements
		assertEquals("isCurrent should default to to false", false, tester.getIsCurrent());
	}

	@Test
	public void generateScheduleWithNoValues() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create various Task objects
		Task tasks = null;

		// assert statements
		assertEquals("generateSchedule should return null if given no values", null, tester.generateSchedule(null));
	}
	
} 
