import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import Entities.Project;
import Entities.Schedule;
import Entities.Task;

public class ProjectUnitTests {

	@Test
	public void constructingProjectWithDefaultValues() {

		// create Schedule object
		Project tester = new Project();

		// makes new calendar object set at today's date
		GregorianCalendar calendar = new GregorianCalendar(); 
		
		
		// assert statements
		assertEquals("the default start date should have today's year", calendar.get(Calendar.YEAR), tester.getStartTime().get(Calendar.YEAR));
		assertEquals("the default start date should have today's month", calendar.get(Calendar.MONTH), tester.getStartTime().get(Calendar.MONTH));
		assertEquals("the default start date should have today's date", calendar.get(Calendar.DAY_OF_MONTH), tester.getStartTime().get(Calendar.DAY_OF_MONTH));

		assertEquals("there should be no default name", "", tester.getProjectName());
		assertEquals("there should be no default author", "", tester.getProjectAuthor());
		
		assertEquals("there should be no tasks to start", true, tester.getTasks().isEmpty());
		assertEquals("there should be no resources to start", true, tester.getResources().isEmpty());
		assertEquals("there should be a schedule", true, tester.getSchedule() != null);
	}

} 
