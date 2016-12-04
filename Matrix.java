package CSC_202_Project;
//
import java.io.*; 
public class Matrix {
	 private double data[][];
	  private int    rows;
	  private int    cols;

	  public Matrix() {
	    data = new double[0][0];
	    rows = 0;
	    cols = 0;
	  }
	  
	  public Matrix(int rows, int cols) {
	    data = new double[rows][cols];
	    rows = rows;
	    cols = cols;
	  }
	  

	  public Matrix(double data[][]) {
	    data = data;
	    rows = data.length;
	    cols = rows > 0 ? data[0].length : 0;
	  }

	  public void setSize(int rows, int cols) {
	    data = new double[rows][cols];
	    rows = rows;
	    cols = cols;
	  }

	  public int getRows() {
	    return rows;
	  }

	  public int getColumns() {
	    return cols;
	  }
	  
	  public void set(double data[][]) {
		  data = data;
		  rows = data.length;
		  cols = rows > 0 ? data[0].length : 0;
	  }

	  public double[][] get() {
	    return data;
	  }

	  //Returns this array with row r and column c removed
	  private Matrix remove(int r, int c) {
	    Matrix A = new Matrix(rows-1, cols-1);
	    for(int i=0; i< rows-1; i++) {
	      for(int j=0; j< cols-1; j++) {
	        A.set(i, j, get(i<r ? i : i+1, j<c ? j : j+1));
	        //No need for output
	      }
	    }
	    return A;
	  }

	  //Returns the determinant of this Matrix
	  public double det() {
	    System.out.println("\nDet:");
	    double d = det(0, "", 1, false, false);
	    
	    System.out.printf("  Det = %8.2f\n", d);
	    return d;
	  }
	  
	  private double det(int indent, String prefix, double mult,
	                     boolean threeXthree, boolean bindent) {
	    if(rows != cols)
	      throw new RuntimeException("This matrix can not be computed");
	    if(rows == 0) {
	      for(int i=0; i<=indent; i++) {
	        System.out.print("  ");
	      }
	      System.out.printf("det( [] ) = 1\n");
	      return 1;
	    }
	    if(rows == 1) {
	      for(int i=0; i<=indent; i++) {
	        System.out.print("  ");
	      }
	      System.out.printf("det( [%8.2f] ) = %8.2f\n",
	        get(0, 0), get(0, 0));
	      return get(0, 0);
	    }
	    if(rows == 2) {
	      for(int i=0; i<=indent; i++) {
	        System.out.print("  ");
	      }
	      if(threeXthree)
	        System.out.printf("%s((%8.2f * %8.2f) - (%8.2f * %8.2f))",
	                 prefix,
	                 get(0, 0), get(1, 1),
	                 get(1, 0), get(0, 1),
	                 (get(0, 0)*get(1, 1)-get(1, 0)*get(0, 1))*mult);
	      else
	        System.out.printf("det( %c ) = %s((%8.2f * %8.2f) - (%8.2f * %8.2f)) = %8.2f\n",
	                 indent+'A' + (bindent ? -1 : 0),
	                 prefix,
	                 this.get(0, 0), this.get(1, 1),
	                 this.get(1, 0), this.get(0, 1),
	                 (this.get(0, 0)*this.get(1, 1)-this.get(1, 0)*this.get(0, 1))*mult);
	      return this.get(0, 0)*this.get(1, 1) - this.get(1, 0)*this.get(0, 1);
	    }

	    double sum = 0;
	    for(int i=0; i<this.rows; i++) {
	      double a = ((i)%2 == 0 ? 1 : -1);
	      double b = this.get(i, 0);
	      for(int k=0; k<=indent; k++) {
	        System.out.print("  ");
	      }
	      if(this.rows==3)
	        System.out.printf("%c[%02d][%02d] = %s(-1^(%d))*(%8.2f *",
	                        indent+'A', i+1, 1, prefix,
	                        i+2, b, indent+'B');
	      else
	        System.out.printf("%c[%02d][%02d] = %s(-1^(%d))*(%8.2f*det( %c )) = ?\n",
	                        indent+'A', i+1, 1, prefix,
	                        i+2, b, indent+'B');
	      double c = this.remove(i, 0).det(indent+1, "", 1, this.rows==3, false);
	      for(int k=0; k<indent+1; k++) {
	        System.out.print("  ");
	      }
	      if(this.rows==3)
	        System.out.printf(") = %8.2f\n",
	                          a*b*c*mult);
	      else
	        System.out.printf("? = %8.2f\n",
	                          a*b*c*mult);
	      sum += a*b*c;
	    }
	    return sum;
	  }

	  //Returns a Matrix of only the specified column
	  public Matrix colMatrix(int col){
		  Matrix A = new Matrix(this.rows, 1);
		  for(int i=0; i<this.rows; i++){
			  A.set(i, 0, get(i, col));
		  }
		  return A;
	  }
	  
	//Returns a Matrix of only the specified row
	  public Matrix rowMatrix(int row){
		  Matrix A = new Matrix(1, this.cols);
		  for(int i=0; i<this.cols; i++){
			  A.set(0, i, get(row, i));
		  }
		  return A;
	  }
	  
	  //Returns the transpose of the cofactor (adjoint)
	  public Matrix adj() {
	    System.out.println("\nAdj: cofactor(A)^T");
	    return this.cofactor().transpose();
	  }

	  public void input(BufferedReader input) throws IOException {
	    System.out.print("Rows in matrix A: ");//get rows
	    int rows = Integer.valueOf(input.readLine()).intValue();
	    System.out.print("Columns in matrix A: ");//get columns
	    int cols = Integer.valueOf(input.readLine()).intValue();
	    this.setSize(rows, cols);//and create empty
	    //fill
	    for(int i=0; i<rows; i++) {
	      for(int j=0; j<cols; j++) {
	        System.out.printf("A[%02d][%02d] = ", i+1, j+1);
	        this.set(i, j, Double.valueOf(input.readLine()).doubleValue());
	      }
	    }
	  }

	  public void fileInput(BufferedReader input) throws IOException {
		int rows = Integer.valueOf(input.readLine()).intValue();
		int cols = Integer.valueOf(input.readLine()).intValue();
		this.setSize(rows, cols);//and create empty
		//Fills matrix
		for(int i=0; i<rows; i++) {
	      for(int j=0; j<cols; j++) {
		    this.set(i, j, Double.valueOf(input.readLine()).doubleValue());
		  }
		}
	  }
	  
	  //Returns the cofactor Matrix of this Matrix
	  public Matrix cofactor() {
	    System.out.println("\nCofactor:");
	    if(this.rows != this.cols)
	      throw new RuntimeException("Illegal matrix dimensions");
	    Matrix A = new Matrix(this.rows, this.cols);
	    for (int i=0; i<this.rows; i++) {
	      for (int j=0; j<this.cols; j++) {
	        
	        double d = this.remove(i, j).det(1,
	                                         String.format("(-1^(%d))*", i+j+2),
	                                         (i+j)%2 == 0 ? 1 : -1, false, true);
	        A.set(j, i, ((i+j)%2 == 0 ? 1 : -1) * d);
	      }
	    }
	    return A;
	  }

	  //Returns the inverse Matrix of this Matrix
	  public Matrix inverse() {
	    if(this.rows != this.cols) //A^-1 = A.transpose()*(A*A^T)^-1
	      return this.transpose().multiply(this.multiply(this.transpose()).inverse());
	    
	    Matrix tmp         = this.adj();
	    double determinant = this.det();
	    System.out.println("\nInverse: adj(A)/det(A):");
	    tmp.print("/"+determinant);
	    
	    return tmp.divide(determinant);
	  }

	  //Returns the Matrix of minors
	  public Matrix minors() {
	    System.out.println("\nMinors:");
	    if(this.rows != this.cols)
	      throw new RuntimeException("Illegal matrix dimensions");
	    Matrix A = new Matrix(this.rows, this.cols);
	    for(int i=0; i<this.rows; i++) {
	      for(int j=0; j<this.cols; j++) {
	        A.set(i, j, this.remove(i, j).det(1, "", 1, false, false));
	      }
	    }
	    return A;
	  }

	  //Sets specified element in this Matrix to 'value'
	  public void set(int i, int j, double value) {
	    if(i >= this.rows || j >= this.cols)
	      throw new RuntimeException("Illegal matrix coordinates");
	    this.data[i][j] = value;
	  }

	  //Gets the value of the specified element from this Matrix
	  public double get(int i, int j) {
	    if(i >= this.rows || j >= this.cols)
	      throw new RuntimeException("Illegal matrix coordinates");
	    return data[i][j];
	  }

	  //Adds a Matrix A to this Matrix
	  public Matrix add(Matrix A) {
	    if(A.getRows() != this.rows || A.getColumns() != this.cols)
	      throw new RuntimeException("Illegal matrix dimensions");
	    Matrix B = new Matrix(this.rows, this.cols);
	    for(int i=0; i<this.rows; i++)
	      for(int j=0; j<this.cols; j++)
	        B.set(i, j, this.get(i, j) + A.get(i, j));
	    return B;
	  }

	  //Returns the transpose of this Matrix
	  public Matrix transpose() {
	    Matrix A = new Matrix(this.cols, this.rows);
	    for(int i=0; i<this.rows; i++)
	      for(int j=0; j<this.cols; j++)
	        A.set(j, i, this.get(i, j));
	    return A;
	  }

	  //Subtracts a Matrix A from this Matrix
	  public Matrix subtract(Matrix A) {
	    if(A.getRows() != this.rows || A.getColumns() != this.cols)
	      throw new RuntimeException("Illegal matrix dimensions");
	    Matrix B = new Matrix(this.rows, this.cols);
	    for(int i=0; i<this.rows; i++)
	      for(int j=0; j<this.cols; j++)
	        B.set(i, j, this.get(i, j) - A.get(i, j));
	    return B;
	  }

	  //Multiplies this Matrix by a Matrix A
	  public Matrix multiply(Matrix A) {
	    System.out.println("\nMultiply by matrix:");
	    //This check is unnecessary for this assignment
	    if(this.cols != A.getRows())
	      throw new RuntimeException("Illegal matrix dimensions");
	    Matrix B = new Matrix(this.rows, A.getColumns());
	    for(int i=0; i<B.getRows(); i++)
	      for(int j=0; j<B.getColumns(); j++) {
	        for(int k=0; k<this.cols; k++) {
	          System.out.printf("  C[%02d][%02d] = %8.2f + %8.2f *%8.2f = %8.2f\n",
	            i+1, j+1, B.get(i, j), this.get(i, k), A.get(k, j),
	            B.get(i, j) + this.get(k, i)*A.get(k, j));
	          B.set(i, j, B.get(i, j) + this.get(k, i)*A.get(k, j));
	        }
	        System.out.println();
	      }
	    return B;
	  }
	  

	  //Multiplies this Matrix by a scalar k
	  public Matrix multiply(double k) {
	    Matrix A = new Matrix(this.rows, this.cols);
	    for(int i=0; i<this.rows; i++)
	      for(int j=0; j<this.cols; j++)
	        A.set(i, j, this.get(i, j) * k);
	    return A;
	  }

	  //Divides each element of this Matrix by a scalar k
	  public Matrix divide(double k) {
	    Matrix A = new Matrix(this.rows, this.cols);
	    for(int i=0; i<this.rows; i++)
	      for(int j=0; j<this.cols; j++)
	        A.set(i, j, this.get(i, j) / k);
	    return A;
	  }

	  //OUTPUT
	  //Prints the Matrix
	  public void print() {
	    System.out.print(this.toString());
	  }
	  
	  public void print(String sufix) {
	    System.out.print(this.toString(sufix));
	  }
	  
	  public String toString() {
	    return toString("");
	  }
	  
	  public String toString(String sufix) {
	    String out = "";
	    for(int i=0; i<this.rows; i++) {
	      out += "|";
	      for(int j=0; j<this.cols; j++)
	        out += String.format("%7.2f%s ", this.get(i, j),  sufix);
	      out += "|\n";
	    }
	    return out;
}
}
