package ex1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ex1.src.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

class GraphAlgoTest {

	@BeforeAll
	static void runOnceBeforeClass()
	{
		System.out.println("Graph_Tests");
	}
	@AfterAll
	static void runOnceAfterAll()
	{
		System.out.println("The Test Finished!");
	}
	@BeforeEach
	void runBeforeEach()
	{
		System.out.println("The Next Test");
	}
	@AfterEach
	void runAfterEach()
	{
		System.out.println("The  Current Test Finished!");
	}
	
	WGraph_DS graph() {
		WGraph_DS graph= new WGraph_DS(); 		//initial empty graph
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.connect(1,2, 1);
		graph.connect(2,3, 1);
		graph.connect(3,4, 2);
		graph.connect(4,5, 3);
		graph.connect(2,5, 10);
		return graph;
	}
	WGraph_Algo alg()
	{
		WGraph_Algo graph = new WGraph_Algo();
		return graph;
	}

	@Test
	void nullGraphTest()
	{
		weighted_graph_algorithms h =  this.alg();
		assertNotNull(h.getGraph());
		h.init(null);
		assertNull(h.getGraph() , "the graph is null!");
		assertFalse(h.isConnected());
		assertNull(h.shortestPath(1, 2), "you need to reaturn null!");
		assertTrue(h.shortestPathDist(0, 1) == -1, "no path!");
//		assertFalse(h.save("graph.txt"), "you can't need save null!!");
//		assertFalse(h.load("graph.txt"), "the folder not exist!");
	}
	
	@Test
	void emptyGraphTest()
	{
		weighted_graph_algorithms h =  this.alg();
		weighted_graph g = new WGraph_DS();
		h.init(g);
		assertTrue(h.shortestPathDist(0, 5) == -1);
		assertNull(h.shortestPath(0, 5) , "need to return null!");
		assertTrue(h.isConnected() , "the graph is connected!");
		assertTrue(h.shortestPathDist(0,5) == -1);	
	}
	
	@Test
	void connectGraphTest()
	{
		weighted_graph inner = this.graph();
		weighted_graph_algorithms h =  this.alg();
		h.init(inner);
		assertTrue(h.isConnected() , "the graph is connect!");
		h.getGraph().removeEdge(1, 2);
		assertFalse(h.isConnected() , "the graph isn't connect!");
		h.getGraph().connect(1,2, 1);
		h.getGraph().removeEdge(3, 2);
		assertTrue(h.isConnected());
		LinkedList<node_info> list = new LinkedList<node_info>();
		h.getGraph().connect(2, 3, 1);
		list.add(h.getGraph().getNode(1));
		list.add(h.getGraph().getNode(2));
		list.add(h.getGraph().getNode(3));
		list.add(h.getGraph().getNode(4));
		list.add(h.getGraph().getNode(5));
		assertEquals(7 ,h.shortestPathDist(1, 5));
		assertEquals(list, h.shortestPath(1, 5));

	}
	@Test
	void connectGraphsTest()
	{
		weighted_graph inner = this.graph();
		weighted_graph_algorithms h =  this.alg();
		h.init(inner);
		assertNull(h.shortestPath(0, 0) ," you not need to return null!!" );
		assertNotNull(h.shortestPath(1, 1) ," you not need to return null!!" );
		h.getGraph().connect(2, 4, 2.99);
		LinkedList<node_info> list = new LinkedList<node_info>();
		list.add(h.getGraph().getNode(1));
		list.add(h.getGraph().getNode(2));
		list.add(h.getGraph().getNode(4));
		assertEquals(3.99 , h.shortestPathDist(1, 4), "the shortest path is 3.99!");
		assertEquals(list , h.shortestPath(1, 4), "this isn't the shortest path!");
		h.getGraph().connect(2,4 ,3.0001);
		assertEquals(4 , h.shortestPathDist(1, 4), "the shortest path is 3.99!");
	}
	
	@Test
	void copyMethodTest()
	{
		weighted_graph inner = this.graph();
		weighted_graph_algorithms h =  this.alg();
		h.init(inner);
//		assertEquals(inner , h.copy(), "this isn't the shortest path!");
		h.init(null);
		assertNull(h.copy(), "the graph is null!!");
		assertFalse(h.isConnected());
	}
	
	@Test
	void SaveLoadMethodTest()
	{
		weighted_graph_algorithms h =  this.alg();
		weighted_graph inner = this.graph();
		h.init(inner);
		assertTrue(h.save("graphsave.txt"));
		weighted_graph_algorithms dup =  new WGraph_Algo();
		dup.load("graphsave.txt");
//		assertEquals(dup.getGraph(), h.getGraph());
	}
	
	@Test
	void timeTest()
	{
		long start = System.currentTimeMillis();
		weighted_graph h = new WGraph_DS();
		for(int i= 0 ; i <1000000 ; i++)
		{
		h.addNode(i);
		}
		for(int j= 0 ; j <1000001 ; j++)
		{
			h.connect(j , j+1 , j+1);
		}
		weighted_graph_algorithms g = new WGraph_Algo(h);
		assertTrue(g.isConnected());
		long end = System.currentTimeMillis();
		assertTrue((end - start) <60000 , "you did it up to 10 seconds!");
		g.getGraph().removeEdge(1,2);
		assertFalse(g.isConnected() , "you have bug in renove function");
	}

}
