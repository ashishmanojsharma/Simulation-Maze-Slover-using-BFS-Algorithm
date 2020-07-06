
package maze_solver;

import static maze_solver.Maze_solver.al;
import static maze_solver.Maze_solver.prevMat;

import java.util.*;

public class BFS_DATA {
    int ROW; 
    int COL;
//    public static Point[][] prevMat= new Point[9][10];
    ArrayList<Point> finalPath = new ArrayList<>();
   
    
    public BFS_DATA(int ROW,int COL)
    {
        this.ROW=ROW;
        this.COL=COL;
    }

 boolean isValid(int row, int col) 
{ 
    
    return (row >= 0) && (row < ROW) && 
           (col >= 0) && (col < COL); 
} 
  

int rowNum[] = {-1, 0, 0, 1}; 
int colNum[] = {0, -1, 1, 0}; 

int BFS(int mat[][], Point src, 
                            Point dest) 
{ 
    if (mat[src.x][src.y] != 1 ||  
        mat[dest.x][dest.y] != 1) 
        return -1; 
  
    boolean [][]visited = new boolean[ROW][COL]; 
      
    
    visited[src.x][src.y] = true; 

    Queue<queueNode> q = new LinkedList<>(); 
      
    
    queueNode s = new queueNode(src, 0); 
    q.add(s);  
  
    
    z:while (!q.isEmpty()) 
    { 
        queueNode curr = q.peek(); 
        Point pt = curr.pt; 
  
         
        if (pt.x == dest.x && pt.y == dest.y) 
            return curr.dist; 
  
        
        q.remove(); 
  
        for (int i = 0; i < 4; i++) 
        { 
            int row = pt.x + rowNum[i]; 
            int col = pt.y + colNum[i]; 
              
             
            if (isValid(row, col) &&  
                    mat[row][col] == 1 &&  
                    !visited[row][col]) 
            { 
               
                prevMat[row][col] = new Point(pt.x,pt.y);
                visited[row][col] = true; 
                queueNode Adjcell = new queueNode(new Point(row, col), 
                                                      curr.dist + 1 ); 
                q.add(Adjcell);

            } 
        } 
    } 
  
    
    return -1; 
} 
}
class queueNode 
{ 
    Point pt;  
    int dist;  
  
    public queueNode(Point pt, int dist) 
    { 
        this.pt = pt; 
        this.dist = dist; 
    } 
}
class Point 
{ 
    int x; 
    int y; 
  
    public Point(int x, int y) 
    { 
        this.x = x; 
        this.y = y; 
    } 
}
