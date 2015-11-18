package graph;

import org.jgraph.graph.*;
import org.jgraph.*;

/**
An arrow is a directed link between two nodes and
may contain a data label.

@author pearce
*/
public class Arrow extends Link {

	private static final long serialVersionUID = 1L;

	/**
	Possible arrow types: CIRCLE, LINE, DOUBLELINE,
	DIAMOND, SIMPLE, TECHNICAL, NONE, CLASSIC
	*/
	public int arrowType = GraphConstants.ARROW_SIMPLE;
	/**
	Should the arrowhead be empty or hollow?
	*/
	public boolean filled = true;

	/**
	Creates a directed link between the specified nodes with
	the specified label.

	@param source a node to be connected
	@param target the other node to be connected.
	@param label the data object labeling this node.
	*/
	public Arrow(Node source, Node target, Object label) {
		super(source, target, label);
		GraphConstants.setLineEnd(getAttributes(), arrowType);
		GraphConstants.setEndFill(getAttributes(), filled);
	}
	/**
	Use to change the type of the arrow.

	But first, redefine the arrow types in this class to
	hide GraphConstants.

	@param type the type of arrowhead to use
	*/
	public void setType(int type) {
		GraphConstants.setLineEnd(getAttributes(), arrowType);
	}
	/**
	to create filled or hollow arrowheads.

	@param filled filled if true, hollowotherwise.
	*/
	public void setFill(boolean filled) {
		GraphConstants.setEndFill(getAttributes(), filled);
	}

}