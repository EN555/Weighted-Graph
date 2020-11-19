package ex1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ex1.src.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {
	
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
		
		return graph;
	}
	
	@Test
	 void empthyGraphTest()
	{
	
		WGraph_DS curr = graph(); 
		assertNull(curr.getNode(0), "the node is null!!");
		assertFalse(curr.hasEdge(0, 1), "you have a problem at edge method");
		assertFalse(curr.hasEdge(-1, -2) , "havn't esge here");
		assertNotNull(curr.getV(), "if the graph empty you need to return empty collection!");
		assertNull(curr.getNode(1));
		curr.connect(0, 1, 2);
		assertEquals(0, curr.nodeSize());
		assertEquals(0, curr.edgeSize());
		assertEquals(0, curr.getMC());
	}
	
	@Test
	 void smallGraphTest()
	{
		WGraph_DS curr = graph(); 
		curr.addNode(1);
		curr.addNode(2);
		curr.addNode(3);
		WGraph_DS curr_copy = graph();  //copy graph
		curr_copy.addNode(1);
		curr_copy.addNode(2);
		curr_copy.addNode(3);
		
		assertTrue(curr.nodeSize() == 3 , "you have bug at add method!");
		assertTrue(curr.edgeSize() == 0 , "you have bug at add method!");
		assertTrue(curr.getMC() == 3 , "you have bug at add method!");

		assertEquals(curr_copy , curr , "they worth!!");
	}
		@Test
		 void remGraphTest()
		{
		WGraph_DS curr = graph(); 
		curr.addNode(1);
		curr.addNode(2);
		curr.addNode(3);
		curr.removeEdge(0, 1);	
		assertEquals(3, curr.getMC() , "you count the remove that never happen!");
		curr.removeNode(5);
		curr.addNode(0);
		assertEquals(4, curr.getMC() , "you count the remove that never happen!");
		curr.connect(0, 1, 2);
		assertEquals(1, curr.edgeSize() , "you count the remove that never happen!");
		curr.connect(0, 1, 2);
		assertEquals(1, curr.edgeSize() , "you count the remove that never happen!");
		assertEquals(5, curr.getMC() , "you count the remove that never happen!");
		curr.connect(0, 1, 3);
		assertEquals(6, curr.getMC() , "you count the remove that never happen!");	
		assertNotNull(curr.getV(6), "yoe need to return empty collection!!");
	}
	
		@Test
		 void WeightGraphTest()
		{
			WGraph_DS curr = graph(); 
			curr.addNode(0);
			curr.addNode(1);
			curr.addNode(2);
			curr.addNode(3);
			curr.addNode(4);
			curr.connect(1, 2, 0);
			assertEquals(5 , curr.getMC() , "you connect edge with weight 0");
			curr.connect(0, 1, 1);
			curr.connect(1, 2, 2);
			assertEquals(7, curr.getMC());
		}
		@Test
		 void connectGraphTest()
		{
			WGraph_DS curr = graph(); 
			curr.addNode(0);
			curr.addNode(1);
			curr.addNode(2);
			curr.addNode(3);
			curr.addNode(4);
			curr.connect(0, 0, 1);	
			assertEquals(5, curr.getMC());
			curr.connect(5, 7, 43);
			assertEquals(0, curr.edgeSize());
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
			long end = System.currentTimeMillis();
			assertTrue((end - start) <60000 , "you did it up to 10 seconds!");
		}
	
}
