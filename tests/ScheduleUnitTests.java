import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import Entities.Resource;
import Entities.ResourceType;
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
		HashMap<String, Task> tasks = null;

		// assert statements
		assertEquals("Schedule should start as not current", false, tester.isCurrent());

		tester.generateSchedule(tasks);
		assertEquals("Schedule should be current after a successful generateSchedule()", true, tester.isCurrent());
	}

	@Test
	public void generateScheduleWithNoValues() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create various Task objects
		HashMap<String, Task> tasks = null;

		// assert statements
		assertEquals("generateSchedule should return null if given no values", null, tester.generateSchedule(tasks));
	}

	@Test
	public void generateScheduleWithoutSettingStartDate() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create various Task objects
		HashMap<String, Task> tasks = new HashMap<String,Task>();
		tasks.put("1", new Task("1","Task1", "description", 10));

		// assert statements
		assertEquals("generateSchedule should return null if given no startDate", null, tester.generateSchedule(tasks));
	}
	
	@Test
	public void generateScheduleWithOneTask() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create a single Task object
		HashMap<String, Task> tasks = new HashMap<String,Task>();
		Task task1 = new Task("1","Task1", "description", 10);
		tasks.put("1", task1);
		
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
		HashMap<String, Task> tasks = new HashMap<String,Task>();
		Task task1 = new Task("1a","Task1", "description", 10);
		tasks.put("1", task1);
		Task task2 = new Task("1","Task2", "description", 10);
		task1.addSuccessor(task2);
		task2.addPredecessor(task1);
		tasks.put("2", task2);
		
		// create startDate
		Calendar startDate = new GregorianCalendar(2011, Calendar.JULY, 3);

		// assert statements
		tester.generateSchedule(startDate, tasks);
		
		assertEquals("Task1's startTime should be 0", 0, task1.getStartTime());
		assertEquals("Task2's startTime should be 10", 10, task2.getStartTime());
	}		
	
	@Test
	public void toMatrixWithoutGeneratingSchedule() {
		// Set up Tasks
		HashMap<String, Task> tasks = new HashMap<String, Task>();
		Task task1 = new Task("aaa", "Task 1", "...", 1);
		tasks.put("aaa", task1);

		Task task2 = new Task("bbb", "Task 2", "...", 2);
		tasks.put("bbb", task2);
		task2.addPredecessor(task1);
		task1.addSuccessor(task2);
		
		Task task3 = new Task("ccc", "Task 3", "...", 1);
		tasks.put("aaa", task3);
		task3.addPredecessor(task2);
		task3.addPredecessor(task1);
		task2.addSuccessor(task3);
		task1.addSuccessor(task3);
		
		//calculate start times on them...
		
		task1.setStartTime(0);
		task1.setEndTime(1);
		
		task2.setStartTime(1);
		task2.setEndTime(3);
		
		task3.setStartTime(3);
		task3.setEndTime(4);
		
		// Create Schedule
		Schedule tester = new Schedule(new GregorianCalendar(2011, Calendar.DECEMBER, 3));
		String [][] result = tester.toMatrix();
		assertEquals("toMatrix() should return null if the schedule has not been generated", true, result == null);

	}


	@Test
	public void toMatrixWithASimpleSchedule() {
		// Set up Tasks
		HashMap<String, Task> tasks = new HashMap<String, Task>();
		Task task1 = new Task("aaa", "Task 1", "...", 1);
		tasks.put("aaa", task1);

		Task task2 = new Task("bbb", "Task 2", "...", 2);
		tasks.put("bbb", task2);
		task2.addPredecessor(task1);
		task1.addSuccessor(task2);
		
		Task task3 = new Task("ccc", "Task 3", "...", 1);
		tasks.put("ccc", task3);
		task3.addPredecessor(task2);
		task3.addPredecessor(task1);
		task2.addSuccessor(task3);
		task1.addSuccessor(task3);
		
		Task task4 = new Task("ddd", "Task 2a", "...", 1);
		tasks.put("ddd", task4);
		task4.addPredecessor(task1);
		task1.addSuccessor(task4);
		task4.addSuccessor(task3);
		task4.addPredecessor(task4);
		
		//calculate start times on them...
		
		task1.setStartTime(0);
		task1.setEndTime(1);
		
		task2.setStartTime(1);
		task2.setEndTime(3);
		
		task3.setStartTime(3);
		task3.setEndTime(4);
		
		task4.setStartTime(1);
		task4.setEndTime(2);
		
		// Create Schedule
		Schedule tester = new Schedule(new GregorianCalendar(2015, Calendar.JULY, 30));
		tester.generateSchedule(tasks);

		String [][] result = tester.toMatrix();
		
		assertEquals("result[0][0] should be 2015/7/30", "2015/7/30", result[0][0]);
		assertEquals("result[1][0] should be 2015/7/31", "2015/7/31", result[1][0]);
		assertEquals("result[2][0] should be 2015/8/1", "2015/8/1", result[2][0]);
		assertEquals("result[3][0] should be 2015/8/2", "2015/8/2", result[3][0]);

		assertEquals("result[0][1] should be \"aaa\"", "aaa", result[0][1]);
		assertEquals("either result[1][1] or result[2][1] should be \"bbb,ddd\" or \"ddd,bbb\"", true, result[1][1].equals("bbb,ddd") || result [2][1].equals("bbb,ddd") || result[1][1].equals("ddd,bbb") || result [2][1].equals("ddd,bbb"));
		assertEquals("result[3][1] should be \"ccc\"", "ccc", result[3][1]);
	}

	@Test
	public void generateScheduleWithTasksThatShareAResource() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create resource
		Resource resource1 = new Resource("rrr", "Resource 1", 10.50, ResourceType.labor);
		
		// create various Task objects
		HashMap<String, Task> tasks = new HashMap<String,Task>();
		
		Task childTask1 = new Task("111","Child Task 1", "...", 10);
		childTask1.addResource(resource1);
		tasks.put("111", childTask1);

		Task childTask2 = new Task("222","Child Task 2", "...", 10);
		childTask2.addResource(resource1);
		tasks.put("222", childTask2);
		
		Task parentTask = new Task("ppp","Parent Task", "...", 0);
		tasks.put("PPP", parentTask);
				
		parentTask.addChild(childTask1);
		childTask1.setParent(parentTask);
		
		parentTask.addChild(childTask2);
		childTask2.setParent(parentTask);
		
		// generate schedule
		tester.generateSchedule(new GregorianCalendar(), tasks);
			
		// assert statements
		assertEquals("The combined duration should be 20 (= 10 + 10)", 20, parentTask.getDuration());
	}

	
	@Test
	public void generateScheduleWithProjectZZZ() {

		// create Schedule object
		Schedule tester = new Schedule();

		// create resource
		Resource resource1 = new Resource("rrr", "Resource 1", 10.50, ResourceType.labor);
		
		// create various Task objects
		HashMap<String, Task> tasks = new HashMap<String,Task>();
		
		Task childTask1 = new Task("111","Child Task 1", "...", 10);
		childTask1.addResource(resource1);
		tasks.put("111", childTask1);

		Task childTask2 = new Task("222","Child Task 2", "...", 10);
		childTask2.addResource(resource1);
		tasks.put("222", childTask2);
		
		Task parentTask = new Task("ppp","Parent Task", "...", 0);
		tasks.put("PPP", parentTask);
				
		parentTask.addChild(childTask1);
		childTask1.setParent(parentTask);
		
		parentTask.addChild(childTask2);
		childTask2.setParent(parentTask);
		
		// generate schedule
		tester.generateSchedule(new GregorianCalendar(), tasks);
			
		// assert statements
		assertEquals("The combined duration should be 20 (= 10 + 10)", 20, parentTask.getDuration());
	}


} 
