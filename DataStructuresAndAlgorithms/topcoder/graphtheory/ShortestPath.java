package graphtheory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ShortestPath {
   public static List<ArrayList<String>> pathList = new ArrayList<ArrayList<String>>();	
   public static void main(String []args){
	   String[][] pathMatrix = new String[2][3];
	   pathMatrix[0][0] = "6";
	   pathMatrix[0][1] = "5";
	   pathMatrix[0][2] = "4";
	   pathMatrix[1][0] = "3";
	   pathMatrix[1][1] = "2";
	   pathMatrix[1][2] = "1";
	   
	   ShortestPath aShortestPath = new ShortestPath();
	   Stack<String> pathStack = new Stack<String>();
	   aShortestPath.paths(pathMatrix,0,0,2,3,pathStack);
	   System.out.print(aShortestPath.lexicographicPath(pathList));
	   
	   
   }
   
   public void paths(String[][] pathMatrix,int startX,int startY,int maxWidth,int maxCol,Stack<String> pathStack){
	   if(startX == maxWidth-1 && startY == maxCol-1){
		   pathStack.push(pathMatrix[startX][startY]);
		   List<String> path = new ArrayList<String>();
		   System.out.print("PathStack:"+pathStack.toString());
		   for(int i=0;i<pathStack.size();i++){
			   path.add(pathStack.get(i));
		   }
		   pathList.add((ArrayList<String>) path);
		   pathStack.pop();
		   return;
	   }
	   if(startX==maxWidth)
		   return;
	   if(startY == maxCol)
		   return;
	   pathStack.push(pathMatrix[startX][startY]);
	   paths(pathMatrix,startX,startY+1,maxWidth,maxCol,pathStack);
	   paths(pathMatrix, startX+1, startY, maxWidth, maxCol, pathStack);
	   pathStack.pop();
   }
   public String lexicographicPath(List<ArrayList<String>> pathList){
	   List<String> paths = new ArrayList<String>();
	   for(List<String> path:pathList){
		   //System.out.println("path"+path.toString());
		   path.sort(null);
		   StringBuffer sBuffer = new StringBuffer();
		   for (int i = 0; i < path.size(); i++) {
			   sBuffer.append(path.get(i));
		   }
		   paths.add(sBuffer.toString());
	   }
	   paths.sort(null);
	   return paths.get(0);   
   }
}
