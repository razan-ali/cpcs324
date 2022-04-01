/*
//--------this class used for analysis the result-----
only used to print the time taken by each algorithm to files 
 */
package cpcs324_project1_phase1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class ToPrint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
       
       
        PrintWriter PQPrimFile = new PrintWriter("PQPrim.txt");
        PrintWriter PQPrimFile2 = new PrintWriter("PQPrim2.txt");
        PrintWriter MHPrimFile  = new PrintWriter("MHPrim.txt");
        PrintWriter KurskalFile = new PrintWriter("Kurskal.txt");
        
        //intilize number of vertices and edges by zero 
        int verticesNo = 0;
        int edgesNo = 0;
        int userChoice = 0;
        int caseNumber =0;
        //----------------------
        // print menu and ask user to choice 
        System.out.println("-----------------Test and compare diffrent Minimum Spanning Tree Algorithms---------------");
        System.out.println("\t1-Kruskal's Algorithm & Prim's Algorithm (based on Priority Queue)");
        System.out.println("\t2-Prim's Algorithm (based on Priority Queue)");
        // ensure that user enter 1 or 2 
        
        //printthe menue of casses
        System.out.println("\tCases (where n represnt # of vertices and m represent # of edges):");
        System.out.println("1-  n =  1,000  , m =   10,000");
        System.out.println("2-  n =  1,000  , m =   15,000");
        System.out.println("3-  n =  1,000  , m =   25,000");
        System.out.println("4-  n =  5,000  , m =   15,000");
        System.out.println("5-  n =  5,000  , m =   25,000");
        System.out.println("6-  n = 10,000  , m =   15,000");
        System.out.println("7-  n = 10,000  , m =   25,000");
        System.out.println("8-  n = 20,000  , m =  200,000");
        System.out.println("9-  n = 20,000  , m =  300,000");
        System.out.println("10- n = 50,000  , m =1,000,000");
       for(caseNumber=1;caseNumber<11;caseNumber++){
           
            System.out.println("**case number :"+caseNumber);
            PQPrimFile.print("case number "+caseNumber+"-");
            PQPrimFile2.print("case number "+caseNumber+"-");
            MHPrimFile.print("case number "+caseNumber+"-");
            KurskalFile.print("case number "+caseNumber+"-");
        
       switch(caseNumber){
            case 1:
                verticesNo = 1000;
                edgesNo = 10000;
                break;
            case 2:
                verticesNo = 1000;
                edgesNo = 15000;
                break;
            case 3:
                verticesNo = 1000;
                edgesNo = 25000;
                break;
            case 4:
                verticesNo = 5000;
                edgesNo = 15000;
                break;
            case 5:
                verticesNo = 5000;
                edgesNo = 25000;
                break;
            case 6:
                verticesNo = 10000;
                edgesNo = 15000;
                break;
            case 7:
                verticesNo = 10000;
                edgesNo = 25000;
                break;
            case 8:
                verticesNo = 20000;
                edgesNo = 200000;
                break;
            case 9:
                verticesNo = 20000;
                edgesNo = 300000;
                break;
            case 10:
                verticesNo = 50000;
                edgesNo = 1000000;
                break;
        }//end of switvh 
       
        //----------------------------------
        userChoice=1;
        for(int j =1;j<11;j++){
        System.out.println("**iteration :"+j);
        Graph newGhraph = new Graph(verticesNo,edgesNo, false); //create new graph object 
        newGhraph.makeGraph();//create  graph
        if(userChoice==1){
            KruskalAlg kruskalObj = new KruskalAlg();//create new object 
            double KruskalStartTime = System.currentTimeMillis();//start time of kruskal 
            kruskalObj.Kurskal(newGhraph);//send graph to that kruskal method to start finding the MST
            double KruskalFinishTime = System.currentTimeMillis();//start time of kruskal 
            double totalTimeKruskal = KruskalFinishTime - KruskalStartTime;//find the time taken by kruskal algorithm 
            System.out.println("_____________________________________________________________");
            System.out.println("Minimum Spanning Tree cost: "+kruskalObj.getCost());
            System.out.println("Total runtime of kruskal Algorithm: "+ totalTimeKruskal);
            
            System.out.println("");
            PQprimAlg pqPrimObj = new PQprimAlg();
            double PrimStartTime = System.currentTimeMillis();//start time of kruskal 
            pqPrimObj.PrimPQ(newGhraph);//send graph to that kruskal method to start finding the MST
            double PrimFinishtime = System.currentTimeMillis();//start time of kruskal 
            double totalTimePrim = PrimFinishtime - PrimStartTime;//find the time taken by kruskal algorithm 
            System.out.println("Minimum Spanning Tree cost: "+pqPrimObj.getCost());
            System.out.println("Total runtime of Prim Algorithm(based on Priority Queue): "+ totalTimePrim);
            KurskalFile.print(totalTimeKruskal+" ");
            PQPrimFile.print(totalTimePrim+" ");
            
        
        
        }
        
        else if(userChoice==2)
        {
            MHPrimAlg MHObject = new MHPrimAlg(verticesNo);//create new object 
            double minHeapStartTime = System.currentTimeMillis();//start time of kruskal 
            MHObject.PrimMH(newGhraph);
            double minHeapFinishTime = System.currentTimeMillis();//start time of kruskal 
            double totalTimeMinHeap = minHeapFinishTime - minHeapStartTime;//find the time taken by kruskal algorithm 
            System.out.println("_____________________________________________________________");
            System.out.println("Prim's Algorithm(using Min Heap):");
            System.out.println("Minimum Spanning Tree cost: "+MHObject.getCost());
            System.out.println("Total runtime of Prim Algorithm(based on  MinHeap): "+ totalTimeMinHeap);
            System.out.println("");
            PQprimAlg pqPrimObj = new PQprimAlg();
            double PrimStartTime = System.currentTimeMillis();//start time of kruskal 
            pqPrimObj.PrimPQ(newGhraph);//send graph to that kruskal method to start finding the MST
            double PrimFinishtime = System.currentTimeMillis();//start time of kruskal 
            double totalTimePrim = PrimFinishtime - PrimStartTime;//find the time taken by kruskal algorithm 
            System.out.println("Prim's Algorithm(using Priority Queue):");
            System.out.println("Minimum Spanning Tree cost: "+pqPrimObj.getCost());
            System.out.println("Total runtime of Prim Algorithm(based on Priority Queue): "+ totalTimePrim);
          
            PQPrimFile2.print(totalTimePrim+" ");
            MHPrimFile.print(totalTimeMinHeap+" ");
        
        }
         
       
        }
    
        
        
      
      
        PQPrimFile.print("\n\n");
        MHPrimFile.print("\n\n");
        KurskalFile.print("\n\n");
        PQPrimFile2.print("\n\n\n");
        
       }
     
       
        
     
    PQPrimFile.close();
    MHPrimFile.close();
    KurskalFile.close();
    PQPrimFile2.close();
    }
   
 }
    

