import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
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
		assertEquals("current should default to false when created", false, tester.isCurrent());
		assertEquals("startDate should default to null when created", null, tester.getStartDate());
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

	@Test
	public void generateScheduleWithoutSettingStartDate() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create various Task objects
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(new Task(1,"Task1", "description", 10));

		// assert statements
		assertEquals("generateSchedule should return null if given no startDate", null, tester.generateSchedule(tasks));
	}
	
	@Test
	public void generateScheduleWithOneTask() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create a single Task object
		Set<Task> tasks = new HashSet<Task>();
		Task task1 = new Task(1,"Task1", "description", 10);
		tasks.add(task1);
		
		// create startDate
		Calendar startDate = new GregorianCalendar(2011, Calendar.JULY, 3);

		// assert statements
		tester.generateSchedule(startDate, tasks);
		
		assertEquals("Task1's startTime should be 0", (int)0, (int)task1.getStartTime());
	}		

	@Test
	public void generateScheduleWithTwoTask() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create various Task objects
		Set<Task> tasks = new HashSet<Task>();
		Task task1 = new Task(1,"Task1", "description", 10);
		tasks.add(task1);
		Task task2 = new Task(2,"Task2", "description", 10);
		task2.addPredecessor(task1);
		tasks.add(task2);
		
		// create startDate
		Calendar startDate = new GregorianCalendar(2011, Calendar.JULY, 3);

		// assert statements
		tester.generateSchedule(startDate, tasks);
		
		assertEquals("Task1's startTime should be 0", (int)0, (int)task1.getStartTime());
		assertEquals("Task2's startTime should be 10", (int)10, (int)task2.getStartTime());
	}		

} 
