import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that creates a minimum Spanning tree
 * using Kruskals Algorithm
 * 
 * @author jovanikimble
 *
 */
public class GraphAdjMatrix implements Graph {
	int matrix[][];
	ArrayList<Edge> edges;
	
	public GraphAdjMatrix(int size) {
		this.matrix = new int[size][size];
		this.edges = new ArrayList<>();
	}
	
	public class Edge implements Comparable<Edge>{
		int v1;
		int v2;
		int weight;
		
		public Edge(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	/**
	 * Inner DisjointSets class
	 * @author jovanikimble
	 *
	 */
	public class DisjointSets {
		int[] sets;
		
		public DisjointSets(int n) {
			this.sets = new int[n];
			for(int i = 0; i < n; i++) {
				sets[i] = i;
			}
		}
		
		public int find(int elem) {
			return sets[elem];
		}
		
		public void union(int v1, int v2) {
			int set1 = sets[v1];
		    int set2 = sets[v2];
		    
		    for (int i = 0; i < sets.length; i++) {
		        if (sets[i] == set2) {
		            sets[i] = set1;
		        }
		    }
		}
	}

	@Override
	public void addEdge(int v1, int v2) {
		// TODO Auto-generated method stub
		// Method not appropriate for this assignment

	}

	@Override
	public void topologicalSort() {
		//Method not needed
	}
	

	@Override
	public void addEdge(int v1, int v2, int weight) {
		this.matrix[v1][v2] = weight;
		edges.add(new Edge(v1, v2, weight));

	}

	@Override
	public int getEdge(int v1, int v2) {
		return this.matrix[v1][v2];
	}

	@Override
	public int createSpanningTree() {
		ArrayList<Edge> currEdges = new ArrayList<>();
		DisjointSets ds = new DisjointSets(this.matrix.length);
		Collections.sort(edges);
		
		while(currEdges.size() < this.matrix.length - 1) {
			Edge e = edges.remove(0);
			System.out.println(e.weight);
			
			if(ds.find(e.v1) != ds.find(e.v2)) {
				currEdges.add(e);
				ds.union(e.v1, e.v2);
			}
		}
		
		int sum = 0;
		for(Edge e: currEdges) {
			sum += e.weight;
		}
		
		return sum;
	}

}
