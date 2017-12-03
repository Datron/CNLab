import java.util.Scanner;


public class BellmanFord {

	private int distance[];
	private int numb_vert;
	public static final int MAX = 999;
	public BellmanFord(int numb_vert)
	{
		this.numb_vert = numb_vert;
		distance = new int[numb_vert + 1];
	}
	public void BellmanFordEvaluation(int source,int adj_matrix[][])
	{
		for(int node = 1;node <= numb_vert; node++)
		{
			distance[node] = MAX;
		}
		distance[source] = 0;
		for (int node = 1; node <= numb_vert - 1; node++) {
			for (int src_node = 1; src_node <= numb_vert; src_node++) {
				for (int dest_node = 1; dest_node <= numb_vert; dest_node++) {
					if (adj_matrix[src_node][dest_node] != MAX)
					{
						if(distance[dest_node] > distance[src_node] + adj_matrix[src_node][dest_node])
							distance[dest_node] = distance[src_node] + adj_matrix[src_node][dest_node];
							
					}
				}
				
			}
		}
		for (int src_node = 1; src_node < numb_vert; src_node++) {
			for (int dest_node = 1; dest_node <= numb_vert; dest_node++) {
				if (adj_matrix[src_node][dest_node] != MAX)
				{
					if(distance[dest_node] > distance[src_node] + adj_matrix[src_node][dest_node])
						System.out.println("Negative edges detected");
				}
			}
		}
		System.out.println("Routing table for router = "+source+"i");
		System.out.println("Destination device/cost \t");
		for (int vertex = 1; vertex < numb_vert; vertex++) {
			System.out.println(""+vertex+"\t\t\t"+distance[vertex]);
		}
	}
	public static void main(String[] args) {
		int numb_vert = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of vertices");
		numb_vert = s.nextInt();
		int[][] adj_matrix = new int[numb_vert+1][numb_vert+1];
		System.out.println("Enter the adjacency matrix");
		for (int src_node = 1; src_node <= numb_vert; src_node++) {
			for (int dest_node = 1; dest_node <= numb_vert; dest_node++) {
				adj_matrix[src_node][dest_node] = s.nextInt();
				if(src_node == dest_node)
					adj_matrix[src_node][dest_node] = 0;
				if (adj_matrix[src_node][dest_node] == 0)
					adj_matrix[src_node][dest_node] = MAX;
					
			}
		}
		for (int i = 1; i < numb_vert; i++) {
			BellmanFord bf = new BellmanFord(numb_vert);
			bf.BellmanFordEvaluation(i, adj_matrix);
		}
	}
}
