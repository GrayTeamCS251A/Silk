import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import Entities.Schedule;
import Entities.Task;

public class ScheduleUnitTests {

	@Test
	public void constructingScheduleWithDefaultValues() {

		// create Schedule object
		Schedule tester = new Schedule();

		// assert statements
		assertEquals("Shedule should default to not current when created", false, tester.isCurrent());
	}

	@Test
	public void validateAndInvalidateSchedule() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create various Task objects
		Set<Task> tasks = null;

		// assert statements
		assertEquals("Schedule should start as not current", false, tester.isCurrent());

		tester.generateSchedule(tasks);
		assertEquals("Schedule should be current after a successful generateSchedule()", true, tester.isCurrent());

		assertEquals("Schedule shouldn't generate XLS if Schedule is not current", null, tester.toXLS());
	}

	@Test
	public void generateScheduleWithNoValues() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create various Task objects
		Set<Task> tasks = null;

		// assert statements
		assertEquals("generateSchedule should return null if given no values", null, tester.generateSchedule(tasks));
	}

} 
