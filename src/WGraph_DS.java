package ex1.src;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;



public class WGraph_DS implements weighted_graph ,Serializable {
	
	private HashMap<Integer, node_info> nodes;	
	private int NumberOfedges=0;
	private int NumberOfnodes=0;
	private int NumberOfmodes=0;
	
	//constructor
	public WGraph_DS()
	{  											
		this.nodes= new HashMap<Integer, node_info>();	
	}

	/**
     * return the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
	@Override
	public node_info getNode(int key) {
		return this.nodes.get(key);
	}
    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public boolean hasEdge(int node1, int node2) {
		NodeInfo l1= (NodeInfo)this.nodes.get(node1);
		NodeInfo l2= (NodeInfo)this.nodes.get(node2);
		
		if(l1 != null && l2 != null && node1 != node2)		//check if this node1 exist
		{									
			if(l1.GetNi().containsKey(node2))				//check if node2 is neighbor of node1
				return true;
		} 
			return false;
	}
	 /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public double getEdge(int node1, int node2) 	
	{
		if(this.hasEdge(node1, node2)) {						//check if have edge between them 
			NodeInfo l1= (NodeInfo)this.nodes.get(node1);
			return l1.GetNi_W().get(node2);
		}
		return -1;
	}

	/**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     * @param key
     */
	@Override
	public void addNode(int key) {
		if(this.nodes.get(key) == null) 					//check if have there node
		{
			this.nodes.put(key, new NodeInfo(key));
			this.NumberOfnodes++;			//update the number of nodes
			this.NumberOfmodes++;			//update the number of modes
		}
		return;	
	}
	 /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     */
	@Override
	public void connect(int node1, int node2, double w)
	{
		NodeInfo l1= (NodeInfo)this.nodes.get(node1);
		NodeInfo l2= (NodeInfo)this.nodes.get(node2);


		if(l1 != null && l2 !=null && node1 != node2 && w >=0)		//check if the node not exist and not create edge between the node
		{	//it's check too that the weight greater than 0
			double weight = 0;
			if(l1.GetNi_W().get(l2.getKey()) != null)
				weight = l1.GetNi_W().get(l2.getKey());
			Boolean hasHedge= false;
			if(l1.GetNi().get(l2.getKey()) != null)			//check if has an edge and he only update the weight 
			{
				hasHedge =true;
			}
			l1.GetNi().put(node2, (node_info)l2);
			l1.GetNi_W().put(node2, w);
			l2.GetNi().put(node1,(node_info)l1);
			l2.GetNi_W().put(node1, w);
			if( w != weight)			//check if he update same 
			{
			NumberOfmodes++;
			}
			if(!hasHedge) 
			{
			NumberOfedges++;				//update the number of edges
			}
		}
	}
	/**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     * @return Collection<node_data>
     */
	@Override
	public Collection<node_info> getV() 
	{
		return this.nodes.values();
	}
	 /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: this method should run in O(1) time.
     * @return Collection<node_data>
     */
	@Override
	public Collection<node_info> getV(int node_id) { 
		NodeInfo l1= (NodeInfo)this.nodes.get(node_id);
		
		if(l1==null) 			//return empty collection when the getV is empty
			return new LinkedList<node_info>();				
		
		return l1.GetNi().values();
	}
	/**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method  run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
	@Override
	public node_info removeNode(int key) 
	{
		NodeInfo l1= (NodeInfo)this.nodes.get(key);
		if(l1 != null)       //first of all check if the node is null
		{
		for(int i: l1.GetNi().keySet()) 				//move on all his neighbors
		{
			NodeInfo l2= (NodeInfo)this.nodes.get(i);
			l2.GetNi().remove(key);			//remove the neighbors
			NumberOfmodes++;            
			NumberOfedges--;
		}
		NumberOfmodes++;					//else update because of the node himself
		NumberOfnodes--;
		return this.nodes.remove(key);
		}
		return null;
	}
	/**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     */
	@Override
	public void removeEdge(int node1, int node2) 
	{	
		NodeInfo l1= (NodeInfo)this.nodes.get(node1);
		NodeInfo l2= (NodeInfo)this.nodes.get(node2);
		
		if(this.hasEdge(node1, node2)) 
		{
			l1.GetNi().remove(node2);		//remove the node and her weight
			l1.GetNi_W().remove(node2);
			l2.GetNi().remove(node1);		//remove the other node and her weight 
			l2.GetNi().remove(node1);
			NumberOfedges--;
			NumberOfmodes++;
		}
	}
	 /** return the number of vertices (nodes) in the graph.
     * Note: this method run in O(1) time.
     * @return
     */
	@Override
	public int nodeSize() {
		return this.NumberOfnodes;
	}
	 /**
     * return the number of edges (undirectional graph).
     * Note: this method run in O(1) time.
     * @return
     */
	@Override
	public int edgeSize() {
		return this.NumberOfedges;
	}
	 /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * @return
     */
	@Override
	public int getMC() {
		return this.NumberOfmodes;
	}
	
	public String toString() {
		StringBuffer mText = new StringBuffer();
		for (Integer keys : this.nodes.keySet())  
		{
			NodeInfo l1= (NodeInfo)this.nodes.get(keys);
			mText.append("TheKey= "+ l1.getKey() +" TheInfo= "+l1.getInfo());
			if(l1.GetNi().isEmpty() ==false)
				mText.append(" TheNeigbor= ");
				for(node_info iter: l1.GetNi().values())
				mText.append(" The key= "+ iter.getKey() +" The Weight= "+ this.getEdge(l1.getKey(), iter.getKey())+ ",");
				mText.append("\n");
		}
		return mText.toString();
	}
	@Override
	public boolean equals(Object o)
	{	
		boolean worth = true;
		if(o instanceof weighted_graph) {
			weighted_graph l1= (weighted_graph)o;
			int count= 0; 
			for(node_info iter : l1.getV())
			{	
				if(iter.equals(this.getNode(iter.getKey()))) 
				{
					
				}
				else {
					worth = false;
				}
				count++;
			}
			
		}
		return worth;
	}
	
	
	////       inner class		/////

	
	private class NodeInfo implements node_info , Comparable<node_info> ,Serializable{
		int key;
		String info;
		double tag;
		private HashMap<Integer, node_info> neighbor;
		private HashMap<Integer, Double> w_neighbor;
		
		
		// constructor
		public NodeInfo(int key) 
		{
			this.neighbor= new HashMap<Integer, node_info>();
			this.w_neighbor= new HashMap<Integer, Double>();
			this.key =key;
			this.info="";
			this.tag=0;
		}
		 /**
	     * Return the key (id) associated with this node.
	     * Note: each node_data should have a unique key.
	     * @return
	     */
		@Override
		public int getKey() {
			// TODO Auto-generated method stub
			return this.key;
		}
		 /**
	     * return the remark (meta data) associated with this node.
	     * @return
	     */
		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return this.info;
		}
		 /**
	     * Allows changing the remark (meta data) associated with this node.
	     * @param s
	     */
		@Override
		public void setInfo(String s) {
			// TODO Auto-generated method stub
			this.info=s;
		}
		/**
	     * Allows changing the remark (meta data) associated with this node.
	     * @param s
	     */
		@Override
		public double getTag() {
			// TODO Auto-generated method stub
			return this.tag;
		}
		 /**
	     * Temporal data (aka distance, color, or state)
	     * which can be used be algorithms
	     * @return
	     */
		@Override
		public double setTag(double t) {					
			// TODO Auto-generated method stub
			this.tag=t;
			return t;
		}
		/**
		 *only to the outer class have access to this method
		 * @return the neighbors of the specific node
		 */
		
		public HashMap<Integer, node_info> GetNi() 
		{
			return this.neighbor;
		}
		/**
		 *only to the outer class have access to this method
		 * @return the weight of the neighbors of the specific node
		 */
		
		public HashMap<Integer, Double> GetNi_W() 
		{
			return this.w_neighbor;
		}
		
		public String toString() 
		{
			return "key="+ " "+ this.getKey() +" info=" +this.getInfo() ;
		
		}
		/**
		 * the dest of this function is to the priority queue
		 */
		@Override
		public int compareTo(node_info l1) {			//this method for the priority queue
			if(this.getTag()> l1.getTag())
				return 1;
			if(this.getTag()< l1.getTag())
				return -1;
				return 0;
		}
		@Override
		public boolean equals(Object o)
		{
			if(o instanceof node_info)
			{	
				node_info l1 = (node_info)o;
				if(this.key == l1.getKey() && this.getInfo().equals(l1.getInfo()))
					return true;
			}
				return false;
			
		}

	}
}


