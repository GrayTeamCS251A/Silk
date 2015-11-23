import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import Entities.Schedule;
import Entities.Task;

public class TaskUnitTests {

	@Test
	public void calculateStartTimesOnSingleTask() {

		// create Task
		Task tester = new Task("aaa", "Task 1", "...", 2);
		tester.calculateStartTimes(0);

		// assert statements
		assertEquals("startTime should be 0", 0, (int)tester.getStartTime());
		assertEquals("endTime should be 2", 2, (int)tester.getEndTime());
	}

	@Test
	public void calculateStartTimesOnSingleTaskWithOffset() {

		// create Task
		Task tester = new Task("aaa", "Task 1", "...", 2);
		tester.calculateStartTimes(10);

		// assert statements
//		System.out.println(tester.getEndTime());
		assertEquals("startTime should be 10", 10, (int)tester.getStartTime());
		assertEquals("endTime should be 12", 12, (int)tester.getEndTime());
	}


	
	@Test
	public void calculateStartTimesOnATaskWithChildren() {
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
		
		// Create SuperTask
		Task superTask = new Task("000", "Task 0", "...", 1);	// this duration should be replaced by the children's aggregate duration
		superTask.addChildren(tasks);
		
		//calculate start times on them
		superTask.calculateStartTimes(0);
		
		for (Task child : superTask.getChildren().values()) {
			System.out.println(child.getName() + ": " + child.getStartTime() + "->" + child.getEndTime() + " (d: " + child.getDuration() + ")");
		}
		
		// assert statements
		assertEquals("superTask.startTime should be 0", 0, (int)superTask.getStartTime());
		assertEquals("superTask.endTime should be 4", 4, (int)superTask.getEndTime());

		assertEquals("task1.startTime should be 0", 0, (int)task1.getStartTime());
		assertEquals("task1.endTime should be 1", 1, (int)task1.getEndTime());

		assertEquals("task2.startTime should be 1", 1, (int)task2.getStartTime());
		assertEquals("task2.endTime should be 3", 3, (int)task2.getEndTime());

		assertEquals("task3.startTime should be 3", 3, (int)task3.getStartTime());
		assertEquals("task3.endTime should be 4", 4, (int)task3.getEndTime());

		assertEquals("task4.startTime should be 1", 1, (int)task4.getStartTime());
		assertEquals("task4.endTime should be 3", 3, (int)task4.getEndTime());
		
		assertEquals("superTask.duration should have been changed to 4", 4, (int)superTask.getDuration());

	}
} 
