package Testing;

import graph.Arrow;
import graph.Graph;
import graph.GraphUtils;
import graph.Node;

import java.util.ArrayList;


public class TestingScheduleGraph {
	
	public static Graph testingScheduleGraph()
	   {
		   Graph scheduleGraph = new Graph();
			
			String dataValues[][] =
			{
				{ "12", "task1,task2" },
				{ "-123", "task1,task2" },
				{ "93", "task1,task3" },
				{ "279", "task4,task5" },
				{ "12", "task4,task6" },
				{ "-123", "task7" },
				{ "93", "task8,task9,task10" },
				{ "279", "task8,task9,task11" },
				{ "93", "task8,task9" },
				{ "279", "task12" }
			};
			
			// Get the Starting task(s)
			String getTasks = dataValues[0][1];
			String[] taskIDList = getTasks.split(",");
			
			//Create the starting node(s)
			ArrayList<Node> currentNodeList = new ArrayList<Node>();
			
			int xindex = 100;
			int yindex = 100;
			for (int s = 0; s < taskIDList.length; s++)
			{
				Node n = new Node(taskIDList[s]);
				currentNodeList.add(n);
				
				scheduleGraph.add(n, xindex, yindex);
				yindex += 100;
			}
			
			//convert to ArrayList
			ArrayList<String> aList = convertToArrayList(taskIDList);
			
			//Go through rest of the dataValues
			for (int i = 1; i < dataValues.length; i++)
			{
				getTasks = dataValues[i][1];
				taskIDList = getTasks.split(",");
				
				ArrayList<String> bList = convertToArrayList(taskIDList);
				ArrayList<String> tempAList = (ArrayList<String>) aList.clone();
				ArrayList<String> tempBList = (ArrayList<String>) bList.clone();
				
				// Remove the Already existing ID in the previous list
				aList.removeAll(bList);
				bList.removeAll(tempAList);
				
				if (!aList.isEmpty() && !bList.isEmpty())
				{
					//Create nodes for the Blist and then hook them with the nodes already created
					// from the AList
					xindex += 100;
					yindex = 100;
					

					for (int b = 0; b < bList.size(); b++)
					{
						Node n = new Node(bList.get(b));
						scheduleGraph.add(n, xindex, yindex);
						
						for (int y = 0; y < aList.size(); y++)
						{
							for (Node checkNode: scheduleGraph.getNodes())
							{
								if (aList.get(y).equals(checkNode.getLabel()))
								{
									Arrow a = new Arrow(checkNode, n, "");
									scheduleGraph.add(a);
									break;
								}
							}
						}
						
						yindex += 100;
					}
				}
				
				aList = tempBList;
			}
			
			return scheduleGraph;
	   }
	   
		
		public static ArrayList<String> convertToArrayList(String[] list)
		{
			ArrayList<String> aList = new ArrayList<String>();
			
			for (int i = 0; i < list.length; i++)
			{
				aList.add(list[i]);
			}
			
			return aList;
		}

	   public static void main(String[] args) {
		   Graph graph = testingScheduleGraph();
		   GraphUtils.print(graph);
		   GraphUtils.display(graph);
	      
	   }
}
