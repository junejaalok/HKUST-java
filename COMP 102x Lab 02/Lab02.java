import comp102x.IO;
import java.lang.Math;

public class Lab02
{
    
    public static void multiply()
    {
        // Please write your code after this line
        
        int x,y;
        
        IO.output("Enter an integer, x: ");
        x = IO.inputInteger( );
        IO.output("Enter an integer, y: ");
        y = IO.inputInteger( );
        
        IO.outputln("Answer = " + x*y);
        
        
    }
    
    public static void calculateTriangleArea()
    {
        // Please write your code after this line
        
        double x,y;
        
        IO.output("Enter the width of the triangle: ");
        x = IO.inputDouble( );
        IO.output("Enter the height of the triangle: ");
        y = IO.inputDouble( );
        
        IO.outputln("The triangle area = " + (x*y)/2);
        
        
        
        
    }
    
    public static void solveQuadraticEquation()
    {
        // Please write your code after this line
        
        double a,b,c;
        
        IO.output("Enter a: ");
        a = IO.inputDouble( );
        IO.output("Enter b: ");
        b = IO.inputDouble( );
        IO.output("Enter c: ");
        c = IO.inputDouble( );
        
        double complex_val=Math.sqrt(Math.pow(b,2) - (4*a*c));
        
        IO.outputln("First solution for x = " + (-b + complex_val)/(2*a));
        IO.outputln("Second solution for x = " + (-b - complex_val)/(2*a));
        
        
        
        
        
    }
}
