package graph;


import java.util.*;
import java.awt.*;
import javax.swing.*;
import org.jgraph.graph.*;
import org.jgraph.*;

/**
Utilities for printing and displaying graphs.

@author pearce
*/
public class GraphUtils  {
	/**
	Creates a JGraph component with a specified graph
	as its component.

	This method hides an ugly hack involving the
	graph layout cache.

	@param model the graph model of this component.
	*/
	public static JGraph makeJGraph(Graph model) {
		JGraph graphView = new JGraph(model);
		/*
		Next is the only line I don't like. The layout cache
		is like an awt layout manager, which is fine, layout
		is hard work and needs to be handled by a special
		object. But as far as jgraph is concerned, this is
		the official place where cells are inserted into the
		graph model. To make matters worse, it must be done
		after the JGraph component is created!
		*/
		graphView.getGraphLayoutCache().insert(model.getCells());
		return graphView;
	}

	/**
	Creates a JGraph component with a graph as its model. The
	component is placed in a scroll pane which is added to the
	content pane of a JFrame. The JFrame is then displayed.

	This code can be used as a guide for how to draw graphs.

	@param model the graph to be displayed.
	*/
	public static void display(Graph model) {
		JFrame frame = new JFrame("Graph Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JGraph graphView = makeJGraph(model);
		frame.getContentPane().add(new JScrollPane(graphView));
		frame.pack();
		frame.setVisible(true);
	}

	/**
	prints the nodes and links of a graph.

	This code can be used as a guide for how to do graph analysis.

	@param g the graph to be printed.
	*/
	public static void print(Graph g) {
		Set<Node> nodes = g.getNodes();
		for(Node node: nodes) {
			System.out.println("Node = " + node);
			Set<Link> links = node.getLinks();
			for (Link link: links) {
				System.out.print("   link: " + link);
				Node src = link.getSourceNode();
				Node target = link.getTargetNode();
				if (node == src) {
					System.out.println(", target = " + target);
				} else {
					System.out.println(", source = " + src);
				}
			}
		}
	}
}