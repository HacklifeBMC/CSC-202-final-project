//Christian BASUBI
//CSC 202 
//Final project Section 1.A
//Professor Anwari 
//Copy right Fall 2016 
//Last updated: 11/27/16
package CSC_202_Project;

/*Write a program to read two 3x3 matrices, and compute the sum
*   and the product of the two matrices. Then, compute the transpose matrix,
*   co-factor matrix, and the determinant of the resultant matrix. Then find
*   the inverse of the first matrix then multiply it by the first column of
*   the second matrix to get a third matrix. Test the program using matrices
*   given in class.
*   
*   In this program write a different method to perform the operations and
*   use proper notation to pass the arguments and to access the elements.
*   
*   Use files for input and output. Make sure to use a class and objects.
*   Use constructor and over load constructor to initialise the object.
*   */

import java.util.Scanner; 
import java.io.IOException;
import java.io.*; 
public class Part_A {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			    BufferedReader input
			            = new BufferedReader(new InputStreamReader(System.in));
			    
			    //Creates the input FileReader and BufferedReader
			    System.out.println("Please enter the input file path: ");
			    String s1 = input.readLine(); //Try-catch?
			    FileReader fr = new FileReader(s1); //Could this be combined with line [x]?
			    BufferedReader fileIn = new BufferedReader(fr);
			    
	 //Creates the output FileWriter and BufferedWriter
			    System.out.println("Please enter the output file path: ");
			    String s2 = input.readLine(); //Try-catch?
			    FileWriter fw = new FileWriter(s2); //Could this be combined with line [x]?
			   BufferedWriter fileOut = new BufferedWriter(fw);
			    
			    //Creates two Matrix objects, reads data from file
			    Matrix A = new Matrix();
			    A.fileInput(fileIn);
			    fileIn.skip(1);
			    Matrix B = new Matrix();
			    B.fileInput(fileIn);
			    
			    //Creates Matrix objects to be created during testing of Matrix methods
			    Matrix C = new Matrix();
			    Matrix D = new Matrix();
			    Matrix traD = new Matrix();
			    Matrix cofD = new Matrix();
			    Matrix invA = new Matrix();
			    Matrix E = new Matrix();
			    
			    //Calculates matrices and values specified in the problem description
			    C = A.add(B);
			    D = A.multiply(B);
			    traD = D.transpose();
			    cofD = D.cofactor();
			    double detD = D.det();
			    invA = A.inverse();
			    E = invA.multiply(B.colMatrix(0));
			    
			    //Prints out all calculations
			    fileOut.write(C.toString());
			    fileOut.newLine();
			    fileOut.write(D.toString());
			    fileOut.newLine();
			    fileOut.write(traD.toString());
			    fileOut.newLine();
			    fileOut.write(cofD.toString());
			    fileOut.newLine();
			    fileOut.write("Determinant: "+detD+"\n");
			    fileOut.newLine();
			    fileOut.write(invA.toString());
			    fileOut.newLine();
			    fileOut.write(E.toString());
			    fileOut.flush();
			    fileOut.close();
			    
			    System.exit(0);
			  }

}
//Input 

/*3
3
2
-1
0
1
0
-3
1
1
2

3
3
1
3
-2
2
0
5
0
-1
-10

 */


//Output 
/*
|   3.00    2.00   -2.00 |
|   3.00    0.00    2.00 |
|   1.00    0.00   -8.00 |

|   4.00    5.00   -9.00 |
|  -1.00   -4.00   -8.00 |
|  -6.00   -2.00  -35.00 |

|   4.00   -1.00   -6.00 |
|   5.00   -4.00   -2.00 |
|  -9.00   -8.00  -35.00 |

| 124.00  193.00  -76.00 |
|  13.00 -194.00   41.00 |
| -22.00  -22.00  -11.00 |

Determinant: 759.0

|   0.27   -0.45    0.09 |
|   0.18    0.36   -0.27 |
|   0.27    0.55    0.09 |

|   0.64 |
|   0.27 |
|  -0.45 |
 */
