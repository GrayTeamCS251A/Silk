package graph;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import org.jgraph.graph.*;
import org.jgraph.*;
/**
Instances are semi-logical representations of graphs.

The "semi-" prefix is used because graphs allow
users to specify the positions of nodes, which, strictly
speaking, belongs more to the layout of a graph rather
than its logical structure.

@author pearce
*/
public class Graph extends DefaultGraphModel {

	private static final long serialVersionUID = 1L;

	/**
	The default height of a node
	*/
	public static double NODE_HEIGHT = 30;
	/**
	The default width of a node
	*/
	public static double NODE_WIDTH = 100;

	private Set<Node> nodes
		= new HashSet<Node>();
	private Set<Link> links
		= new HashSet<Link>();
	/**
	Fetches the set of nodes belonging to this graph.

	An iterator might be a better idea, because users
	might try to add nodes directly to this set.

	@return ths graph's nodes
	*/
	public Set<Node> getNodes() { return nodes; }
	/**
	Fetches the set of links belonging to this graph.

	An iterator might be a better idea, because users
	might try to add links directly to this set.

	@return ths graph's links
	*/
	public Set<Link> getLinks() { return links; }
	/**
	Adds a node at a specified position

	@param node the node to add
	@param xc the x-coordinate of the node's upper left corner
	@param yc the y-coordinate of the node's upper left corner
	*/
	public void add(Node node, double xc, double yc) {
		nodes.add(node);
		Map props = node.getAttributes();
		GraphConstants.setBounds(props,
			new Rectangle2D.Double(xc,yc,NODE_WIDTH,NODE_HEIGHT));
	}
	/**
	Adds a link to this graph

	@param link the link to be added
	*/
	public void add(Link link) {
		links.add(link);
	}

	/**
	Used in GraphUtils.makeJGraph() to initialize the
	graph layout manager.

	Note package scope, this shouldn't be called by users.
	*/
	DefaultGraphCell[] getCells() {
		java.util.List<Object> elems = new java.util.LinkedList<Object>();
		elems.addAll(nodes);
		elems.addAll(links);
		DefaultGraphCell[] cells = new DefaultGraphCell[elems.size()];
		for(int i = 0; i < cells.length; i++) {
			cells[i] = (DefaultGraphCell)elems.get(i);
		}
		return cells;
	}

}