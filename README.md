# OOP
### main goal
The main goal of the program is to create udirected weighted graph.
The program composed from two classes and inner class that inplements three interfaces.
### First Class- WGraph_DS
The create of nodes did after the creation of the graph:
```java
public void addNode(int key) 
```
At the base class you can create graph, and you can crate nodes only from the graph, the create of the node executed by the inner class.
For built wighted graph i used at two hashmap, the first hashmap uses for to enter the neighbros according to their key and
the second hashmap uses for enter the same keys the weights of the edges.
at this class have else function to add. remove node, remove edge and etc.
###Second Class- WGraph_Algo
For work with this method you need to init a graph to the class:
```java
public void init(weighted_graph g)
```
After that yo can execute some algorithms:
 #### Copy
 
```java
	public weighted_graph copy() 
  `````
First of all I move on all the collection of the graph and I create the same nodes without their neighbors, after that I move on all the origin hashmap and check the neighbors of every node and enter them in the copy graph and update tpp the weights.

 #### Shortest path distance
 ```` java
 	public double shortestPathDist(int src, int dest) 
````
I create a function that implement dijkstra algorithm . The function use at tag- it's represent the distance from the source, and use at info to know if he visit there (I uses at three colors, WHITE- mean i didnt visit there, GREY- mean i visit there but not at all the neighbors and BLACK- i visit there and in all the neighbors. The function check the neighbors of the source node (that is tag is 0) and put in their tags the minimum weight and etc. 
After that function finished I checked the dest node if there have BLACK in the info its says that have path between the src to dest, else the distance it's the number that in the tag.  
 #### Shortest path
 ```` java
public List<node_info> shortestPath(int src, int dest)
````
it's operate the dist function that sighned the nodes, and the function move on the dest node and check if have node that is distance lower than the current node at 1 after that she find one like that she kept him and countinue to check at the new node and etc. the path keep in list.
 #### connected
  ```` java
public boolean isConnected()
````
It's work too on the dist function, I take randomly node and sighned all her neighbors  and the neighbors of the neighbors, after that check if have node that the function didn't succeed to reach her, it's says that that he didn't 
connect to her.
The run time of this function is o(v+e).
#### save
  ```` java
public boolean save(String file)
````
The goal of the function is to create file that contain the graph, I used at StreamObjectWriter , a java function that automatically create the file from the object.
#### load
  ```` java
public boolean load(String file)
````
The goal of the function is to take file and to crate a graph from the file, i used at StreamObjectReader, a java function that create from her save file and create an object.









