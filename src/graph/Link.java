package graph;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import org.jgraph.graph.*;
import org.jgraph.*;

/**
A link connects two nodes and may contain a data label.

@author pearce
*/
public class Link extends DefaultEdge {

	private static final long serialVersionUID = 1L;

	/**
	Creates a link between the specified nodes with
	the specified label.

	Note that although source and target nodes are distinguished,
	this is not intended to imply that the link is directed. Arrows
	should be used to represent directed links.

	@param source a node to be connected
	@param target the other node to be connected.
	@param label the data object labeling this node.
	*/
	public Link(Node source, Node target, Object label) {
		super(label);
		DefaultPort port1 = source.getPort();
		if (port1 == null) {
			port1 = new DefaultPort();
			source.add(port1);
		}
		setSource(port1);
		source.addLink(this);

		DefaultPort port2 = target.getPort();
		if (port2 == null) {
			port2 = new DefaultPort();
			target.add(port2);
		}
		setTarget(port2);
		target.addLink(this);
		//Map props = getAttributes();
		//GraphConstants.setConnectable(props, true);
	}

	/**
	Calls Link(source, target, "")

	@param source a node to be connected
	@param target the other node to be connected.
	*/
	public Link(Node source, Node target) {
		this(source, target, "");
	}
	/**
	Gets the data label of this link

	@return the data label
	*/
	public Object getLabel() {
		return getUserObject();
	}
	/**
	Sets the data label of this link.

	@param data the new label of this link
	*/
	public void setLabel(Object data) {
		setUserObject(data);
	}

	/**
	Gets the source node of this link.

	For non-arrow links, this should not be construed as
	implying a link direction.

	@return the source node of this link
	*/
	public Node getSourceNode() {
		DefaultPort port = (DefaultPort)super.getSource();
		return (Node) port.getParent();
	}

	/**
	Gets the target node of this link.

	For non-arrow links, this should not be construed as
	implying a link direction.

	@return the target node of this link
	*/

	public Node getTargetNode() {
		DefaultPort port = (DefaultPort)super.getTarget();
		return (Node) port.getParent();
	}
}
