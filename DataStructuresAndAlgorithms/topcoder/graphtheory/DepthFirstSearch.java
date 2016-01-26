package graphtheory;



public class DepthFirstSearch {

	public static void main(String[] args){
		int [][] adjMatrix = new int[2][2];
		boolean[][] visitedNodes = new boolean[2][2];
		
		
	} 
	public void dfsRecursive(int[][] adjMatrix,boolean[][] visitedNodes,int x,int y,int maxRow,int maxCol){
		if(x == maxRow || y == maxCol)
			return;
		if(visitedNodes[x][y]){
			return;
		}
		System.out.print(adjMatrix[x][y]);
		visitedNodes[x][y] = true;
		dfsRecursive(adjMatrix, visitedNodes, x, y+1, maxRow, maxCol);
		dfsRecursive(adjMatrix, visitedNodes, x, y-1, maxRow, maxCol);
		dfsRecursive(adjMatrix, visitedNodes, x+1, y, maxRow, maxCol);
		dfsRecursive(adjMatrix, visitedNodes, x-1, y, maxRow, maxCol);
	}
	public void dfsIterative(int[][] adjMatrix,boolean[][] visitedNodes,int x,int y,int maxRow,int maxCol){
		
	}
}
