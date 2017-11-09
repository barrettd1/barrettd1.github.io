//Danyelle Barrett
//Programming Assignment 2: N-Queens
//Due: 9/28/16

import java.util.Stack;
import java.util.Scanner;

public class NQueens 
{
     public static void main(String[] args) 
     {
         Scanner kb = new Scanner(System.in);
         System.out.println("\nEnter an integer > 1");
         System.out.println("This will be the size of the chess board & the number of queens on it: ");
         int n = kb.nextInt();

         System.out.println("\nSolution:\n");
         int number = solve(n);
         System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
     }
    
     //method solve(int n) will print out and return the total number of solutions to the n-queens problem.
     public static int solve(int n) 
     {
         Stack<Integer> s = new Stack<Integer>();  //stack to hold placement of queens

         int  current = 0; //current column of queen
         int numSolutions = 0; //total number of solutions
         s.push(current); //begin by placing the queen at column 0 to begin finding the solutions
         while(!(current>n)) //loop will execute as long as the queen in row 0 is still on the board
         {
             if(current == n)   //if the queen is in the last spot on the board
             {
                 if(s.isEmpty()) //finished!
                    break;
                 else if(s.peek() == n)
                 {
                     s.pop();
                     current = s.pop()+1;
                 }
                 else
                     current = s.pop()+1;
             }
             else if(validPositionChecker(s, current))  //if the position is safe for the queen,
             {
                 s.push(current);  //place it on the stack
                 current = 0;  //begin the next row at column zero
             }
             else   //if the position isnt safe, move the queen over a column
                 current++;
          
             /*If all of the queens are safely on the board, then print out the solution,
               increase the count of the number of solutions, and then pop the last 
               queens placement off of the stack to backtrack and find another solution. */
             if(s.size()==n)
             {
                 printSolution(s);
                 numSolutions++;
                 s.pop();
                 current = s.pop() + 1;
             }
         }

         return numSolutions;    //return total number of solutions 
     }
      
     //method validPositionChecker will check if the placement of the queen is safe or not, and will return 
     //true or false.
     public static boolean validPositionChecker(Stack<Integer> s, int currentPosition) 
     {
         for (int i = 0; i < s.size(); i++) 
         {
             if (s.get(i) == currentPosition)
                 return false;
             if ((s.get(i) - currentPosition) == (s.size() - i))
                 return false;   
             if ((currentPosition - s.get(i)) == (s.size() - i))
                 return false;   
         }
         return true;
     }
  
     //the method printSolution will print out the solution from the current stack.
     private static void printSolution(Stack<Integer> s) 
     {
         for (int i = 0; i < s.size(); i ++) 
         {
             for (int j = 0; j < s.size(); j ++) 
             {
                 if (j == s.get(i))
                   System.out.print("Q ");
                 else
                   System.out.print("* ");
             }
             System.out.println();
         }
         System.out.println();  
     }
}