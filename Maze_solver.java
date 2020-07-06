
package maze_solver;
import static maze_solver.Maze_solver.al;
import static maze_solver.Maze_solver.destination;
import static maze_solver.Maze_solver.finalpath;
import static maze_solver.Maze_solver.prevMat;
import static maze_solver.Maze_solver.source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.*;
import javax.swing.*;

public class Maze_solver {

    public static ArrayList<Point> al=new ArrayList<>();
    public static ArrayList<Integer> finalpath=new ArrayList<>();
    public static int source,destination;
    public static Point[][] prevMat;
    public static void main(String[] args) {
        int mat[][] = {
                   { 1, 0, 1, 1, 0, 1, 0, 1, 1, 1 }, 
                   { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, 
                   { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
                   { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 }, 
                   { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
                   { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
                   { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
                   { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
                   { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0 },
                   { 0, 1, 1, 0, 0, 1, 1, 1, 0, 1 },
         };
         prevMat=new Point[mat.length][mat[0].length];
         for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                prevMat[i][j] = new Point(-1,-1);
            }
        }
         ArrayList<Integer> free=new ArrayList<Integer>();
         ArrayList<Integer> obst=new ArrayList<Integer>();
         int t=0;
         for(int i=0;i<mat.length;i++)
         {
             for(int j=0;j<mat[0].length;j++)
             {
                 t++;
                 if(mat[i][j]==1)
                 {
                     free.add(t);
                 }
                 else
                 {
                     obst.add(t);
                 }
             }
         }

        Maze obj=new Maze(free,obst,mat);
    }
    
}
class Maze
{
    JFrame f=new JFrame("AI ASSIGNMENT");
    JPanel p1,p2,p3,p4;
    JLabel lh,ls,ld;
    JButton btn[],start,blocks,ok,rst;
    JTextField ts,td;
    public Maze(ArrayList<Integer> free,ArrayList<Integer> obst,int mat[][])
    {
        lh=new JLabel("MAZE SOLVER");
        lh.setForeground(Color.WHITE);
        p1=new JPanel(new FlowLayout());
        p1.add(lh);
        p1.setBackground(Color.GRAY);
        f.add(p1);
        btn=new JButton[mat.length*mat[0].length];
//        System.out.println(source+" "+destination);
        p2=new JPanel(new FlowLayout());
        ls=new JLabel("SOURCE: ");
        ls.setForeground(Color.WHITE);
        ld=new JLabel("DESTINATION: ");
        ld.setForeground(Color.WHITE);
        ts=new JTextField(20);
        td=new JTextField(20);
        ok=new JButton("OK");
        ok.setBackground(Color.DARK_GRAY);
        ok.setForeground(Color.CYAN);
        p2.add(ls);
        p2.add(ts);
        p2.add(ld);
        p2.add(td);
        p2.add(ok);
        p2.setBackground(Color.GRAY);
        f.add(p2);
        p3=new JPanel(new GridLayout(mat.length,mat[0].length));
        for(int i=0;i<btn.length;i++)
        {
            btn[i]=new CreateRoundButton(String.valueOf(i+1));
        }
        for(int i=0;i<btn.length;i++)
        {
            p3.add(btn[i]);
        }
        p3.setBackground(Color.GRAY);
        f.add(p3);
        
        
        p4=new JPanel(new FlowLayout());
        start=new JButton("START");
        start.setBackground(Color.DARK_GRAY);
        start.setForeground(Color.CYAN);
        blocks=new JButton("OBSTRACLES");
        blocks.setBackground(Color.DARK_GRAY);
        blocks.setForeground(Color.CYAN);
        rst=new JButton("RESET");
        rst.setBackground(Color.DARK_GRAY);
        rst.setForeground(Color.CYAN);
        p4.add(start);
        p4.add(blocks);
        p4.add(rst);
        p4.setBackground(Color.GRAY);
        f.add(p4);
        ok.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    al.clear();
                    finalpath.clear();
                    source=Integer.parseInt(ts.getText());
                    destination=Integer.parseInt(td.getText());
                    for(int i=0;i<btn.length;i++)
                    {
                        
                        if(i==source-1)
                        {
                            btn[i].setBackground(Color.GREEN);
                        }
                        else if(i==destination-1)
                        {
                            btn[i].setBackground(Color.YELLOW);
                        }
                        else
                        {
                            btn[i].setBackground(Color.BLACK);
                        }

                    }
                    BFS_DATA b=new BFS_DATA(mat.length,mat[0].length);
                    Point src=getcordinates(mat,source);
                    Point dest=getcordinates(mat,destination);
                    System.out.println("Source: "+source);
                    System.out.println("Destination: "+destination);
                    int dist = b.BFS(mat, src, dest);
                     if (dist != Integer.MAX_VALUE){
                    System.out.println("Shortest Path distance is: " + dist);
                    int tempX=dest.x,tempY=dest.y;
                    while(tempX!=-1 && tempY!=-1){
                        al.add(new Point(tempX,tempY));
                        finalpath.add(getpathno(mat,tempX,tempY));
                        int xx,yy;
                        xx = prevMat[tempX][tempY].x;
                        yy = prevMat[tempX][tempY].y;
                        tempX=xx;
                        tempY=yy;
            }
                         System.out.println(al);
                         System.out.println(finalpath);
                }
                     else {
            System.out.println("Shortest Path doesn't exist");
            
        }
                }
        });
        
        System.out.println(al);
        start.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Thread(new Runnable(){
                    public void run()
                    {
                        for(int i=finalpath.size()-1;i>=0;i--)
                        {
                            btn[finalpath.get(i).intValue()-1].setBackground(Color.red);
                            try{Thread.sleep(500);}catch(Exception e){};
                        }
                    }
                }).start();
                
            }
                        
                        });
        
        blocks.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Thread(new Runnable(){
                    public void run()
                    {
                        for(int i=0;i<obst.size();i++)
                        {
                            btn[obst.get(i).intValue()-1].setBackground(Color.BLUE);
                            try{Thread.sleep(300);}catch(Exception e){};
                        }
                    }
                }).start();
                
            }
                        
                        });
        
        rst.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                al.clear();
                finalpath.clear();
                ArrayList<Integer> free=new ArrayList<Integer>();
                ArrayList<Integer> obst=new ArrayList<Integer>();
                int t=0;
                for(int i=0;i<mat.length;i++)
                {
                    for(int j=0;j<mat[0].length;j++)
                    {
                        t++;
                        if(mat[i][j]==1)
                        {
                            free.add(t);
                        }
                        else
                        {
                            obst.add(t);
                        }
                    }
                }

                new Maze(free,obst,mat);
                
            }
                        
                        });
        
        f.setSize(800,600);
        f.setLayout(new GridLayout(4,1));
        f.setVisible(true);
        f.getContentPane().setBackground(Color.BLUE);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Point getcordinates(int mat[][],int t)
    {
        int i=0,j=0,z=0;
        l:for(i=0;i<mat.length;i++)
        {
            for(j=0;j<mat[0].length;j++)
            {
                z++;
                if(z==t)
                {
                    return new Point(i,j);
                }
            }
        }
        return new Point(i,j);
    }
    
    public int getpathno(int mat[][],int x,int y)
    {
        int z=0;
        l:for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                z++;
                if(i==x && j==y)
                {
                    break l;
                }
            }
        }
        return z;
    }
}

class CreateRoundButton extends JButton {
  public CreateRoundButton(String label) {
    super(label);
    Dimension size = getPreferredSize();
    size.width = size.height = Math.max(size.width,size.height);
    setPreferredSize(size);

    setContentAreaFilled(false);
  }

  protected void paintComponent(Graphics g) {
    if (getModel().isArmed()) {
      g.setColor(Color.lightGray);
    } else {
      g.setColor(getBackground());
    }
    g.fillOval(0, 0, getSize().width-1,getSize().height-1);

    super.paintComponent(g);
  }

  protected void paintBorder(Graphics g) {
    g.setColor(getForeground());
    g.drawOval(0, 0, getSize().width-1,     getSize().height-1);
  }

  Shape shape;
  public boolean contains(int x, int y) {
    if (shape == null || 
      !shape.getBounds().equals(getBounds())) {
      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
    }
    return shape.contains(x, y);
  }

}
