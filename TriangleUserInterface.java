import java.awt.geom.Point2D;
import java.util.Scanner;

public class TriangleUserInterface {
	public static String printPoint (Point2D.Double p) {
		return "(" + p.getX() + ", " + p.getY() + ")";
		
	}
	
	public static void main(String[] args) {
		Point2D.Double p1, p2, p3;
		double x,y;
		Scanner input= new Scanner(System.in);
		System.out.println("What is the X coordinate of the 1st point?");
		x = input.nextDouble();
		
		System.out.println("WHat is the Y coordinate of the 1st point?");
		y=input.nextDouble();
		
		p1=new Point2D.Double(x,y); 
		
		System.out.println("What is the X coordinate of the 2nd point?");
		x = input.nextDouble();
		
		System.out.println("WHat is the Y coordinate of the 2nd point?");
		y=input.nextDouble();
		
		p2=new Point2D.Double(x,y); 
		
		System.out.println("What is the X coordinate of the 3rd point?");
		x = input.nextDouble();
		
		System.out.println("WHat is the Y coordinate of the 3rd point?");
		y=input.nextDouble();
		
		p3=new Point2D.Double(x,y); 
		
		
		Triangle one = new Triangle(p1, p2, p3);
		System.out.println(one);
		System.out.println(one.allSides());
		System.out.println(one.allAngles());
		System.out.println(one.Perimeter());
		System.out.println(one.areaHerons());
		System.out.println("\n Triangle is Isosceles? \t" + one.isIsosceles()
		+ "\n Triangle is Equilateral? \t" + one.isEquilateral()
		+ "\n Triangle is Scalene? \t" + one.isScalene()
		+ "\n Triangle is Right? \t" + one.isRight()
		+ "\n Triangle is Acute? \t" + one.isAcute()
		+ "\n Triangle is Obtuse? \t" + one.isObtuse());
		
	System.out.println("The circumcenter lies at \t"
			+ printPoint(one.circumcenter()));
	System.out.println("The centriod lies at \t\t"
			+ printPoint(one.centroid()));
	System.out.println("The orthocenter at \t"
			+ printPoint(one.orthocenter())+ "\n - - - - - - - - - - - - - - - - -");

	}

}
