package graph;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import org.jgraph.graph.*;
import org.jgraph.*;

/**
Instances represent graph nodes.

@author pearce

*/
public class Node extends DefaultGraphCell {

	private static final long serialVersionUID = 1L;

	private Set<Link> links;

	/**
	Use this for safely traversing the links associated
	with this node.

	@return an iterator for accessing this node's links
	*/
	public Iterator<Link> iterator() { return links.iterator(); }

	/**
	A dangerous method because it invites users to add and
	remove links through a back door.

	@return links associated with this node.
	*/
	public Set<Link> getLinks() { return links; }
	/**
	Creates a node labeled with a specified data object

	@param data the node's label
	*/
	public Node(Object data) {
		super(data);
		links = new HashSet<Link>();
		Map props = getAttributes();
		setBorderColor(Color.black);
		GraphConstants.setOpaque(props, true);
		addPort();
	}

	/**
	Creates a node labeled by null
	*/
	public Node() { this(null); }

	/**
	Gets the data label of this node.

	@return the data label of this node.
	*/
	public Object getLabel() {
		return getUserObject();
	}

	/**
	Sets the data label of this node.

	@param data the new data label.
	*/
	public void setLabel(Object data) {
		setUserObject(data);
	}

	/**
	Sets the color of this node.

	Color should be part of the view, not the model, but
	this method hides jraph's loathsome GraphConstants
	hack.

	@param c the new color
	*/
	public void setBackgroundColor(Color c) {
		Map props = getAttributes();
		GraphConstants.setBackground(props, c);
	}

	/**
	Sets the color of this node's border.

	Color should be part of the view, not the model, but
	this method hides jraph's loathsome GraphConstants
	hack.

	@param c the new color
	*/
	public void setBorderColor(Color c) {
		Map props = getAttributes();
		GraphConstants.setBorderColor(props, c);
	}

	/**
	Gets the bounding box of this node.

	Position and size should be part of the view, not
	the model, but this method hides jraph's loathsome
	GraphConstants hack.

	@return the bounding box of this node
	*/
	public Rectangle2D.Double getBounds() {
		Map props = getAttributes();
		return (Rectangle2D.Double)GraphConstants.getBounds(props);
	}

	/**
	Sets the bounding box of this node.

	Position and size should be part of the view, not
	the model, but this method hides jraph's loathsome
	GraphConstants hack.

	This method can be used to dynamically move nodes
	around the screen in case users want to do their own'
	layout.

	@param box the new bounding box.
	*/

	public void setBounds(Rectangle2D.Double box) {
		Map props = getAttributes();
		GraphConstants.setBounds(props, box);
	}

	/**
	Used by Link constructor to add newly created links
	to this node's links list.

	This shouldn't be necessary, but I can't figure out
	where jgraph stores information about a node's links.

	Note package scope. This method shouldn't be called by users.
	*/
	void addLink(Link l) { links.add(l); }
	/**
	Another hack needed by the Link constructor to get a
	port it can connect to.

	Note package scope. This method shouldn't be called by users.

	@return the first DefaultPort associated with this node, or null
	*/
	DefaultPort getPort() {
		java.util.List kids = getChildren();
		for(Object kid: kids) {
			if (kid instanceof DefaultPort)
				return (DefaultPort)kid;
		}
		return null;
	}

	/*

	This didn't work:

	public 	Set<Link> getLinks() {
		java.util.List kids = getChildren();
		System.out.println("kids = " + kids);
		Set<Link> result = new HashSet<Link>();
		for(Object kid: kids) {
			if (kid instanceof DefaultPort) {
				Set edges = ((DefaultPort)kid).getEdges();
				for(Object e: edges) {
					if (e instanceof Link) {
						System.out.println("adding an edge");
						result.add((Link) e);
					}
				}
			}
		}
		return result;
	}

	*/


}
