	import java.awt.geom.Point2D;
	import java.util.Scanner;
	
	public class Triangle {
		
		//declare instance variables
		Point2D.Double PointA, PointB, PointC;
		
		// no-arg constructor
		public Triangle() {
			

		}
		//overloaded constructor
		public Triangle(Point2D.Double A, Point2D.Double B, Point2D.Double C) {
			PointA = A;
			PointB = B;
			PointC = C;
		}
		
		public static double roundTwoDecimals (double d) {
			return (int) (d * 100 + 0.5) / 100.0;
		}
		
		public static String printPoint (Point2D.Double p) {
			return "(" + p.getX() + ", " + p.getY() + ")";
			
		}
		
		public String allSides() {
			double AB = roundTwoDecimals(sideLength(PointA, PointB));
			double BC = roundTwoDecimals(sideLength(PointB, PointC));
			double AC = roundTwoDecimals(sideLength(PointA, PointC));
			return "\nLength of side AB\t" + AB + " units\nLengths of side BC\t" + BC
					+ " units\nLength of side AC\t" + AC + " units";
		}
		
		public String allAngles() {
			double A = roundTwoDecimals(vertexAngle(PointA, PointB, PointC));
			double B = roundTwoDecimals(vertexAngle(PointB, PointC, PointA));
			double C = roundTwoDecimals(vertexAngle(PointC, PointA, PointB));
			return "\nMeasure of Angle A\t" + A + " degrees\nMeasure of angle B\t"
					+ B + " degrees\nMeasure of angle C\t" + C + " degrees"; 
		}
		
		
		
		
		public double sideLength(Point2D.Double a, Point2D.Double b) {
			
			return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) 
					+ Math.pow(a.getY() - b.getY(), 2));
		}
		
		public double vertexAngle (Point2D.Double requestAngle, Point2D.Double other1, 
				Point2D.Double other2) {
			double oppositeSide = sideLength(other1, other2);
			double adjacentSide1 = sideLength(other1, requestAngle);
			double adjacentSide2 = sideLength(requestAngle, other2);
			return Math.toDegrees(Math.acos((Math.pow(adjacentSide1, 2)
					+ Math.pow(adjacentSide2, 2) - Math.pow(oppositeSide, 2))
					/ (2.0 * adjacentSide1 * adjacentSide2)));
			
			
		}
		
		public static Point2D.Double midpoint(Point2D.Double a, Point2D.Double b){
			return new Point2D.Double ((a.getX() + b.getX()) / 2.0,
					(a.getY() + b.getY()) / 2.0);
			
		}
		
		public static double mySlope (Point2D.Double a, Point2D.Double b) {
			return ((a.getY() - b.getY()) / (a.getX() - b.getX()));
			
		}
		
		public String toString() {
			
			return "\n" + " - - - - - - - - - - - - - - "
					+ "\nThe triangle with verticesat:" + "\n	Point A:\t"
					+ printPoint(PointA) + "\n	Point B:\t" + printPoint(PointB)
					+ "\n	Point C:\t" + printPoint(PointC)
					+"\nhas the following properties";
		}
		
		public static LinearEQ perpendicularBisector(Point2D.Double a, Point2D.Double b) {
			if (mySlope(a, b) == 0 ) {
				double midX = (a.getX() + b.getX()) / 2.0;
				return new LinearEQ(1, 0, midX);
			} else {
				double slope = -1.0 / mySlope(a, b);
				Point2D.Double mid = midpoint(a, b);
				return new LinearEQ(-slope, 1, mid.getX() * slope - mid.getY());
			}
		}
		
		public static LinearEQ median(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
			Point2D.Double mid = midpoint(c,b);
			double slope = mySlope(a, mid);
			
			return new LinearEQ(-slope, 1 , mid.getX() * slope - mid.getY());
		}
		
		public static LinearEQ altitude(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
			if (mySlope(c, b) == 0 ) {
				return new LinearEQ(0, 1, c.getY());
			} else {
				double slope = -1.0 / mySlope(c, b);
				return new LinearEQ(-slope, 1, a.getX() * slope - a.getY());
			}
		}
		
		
		
		
		public Point2D.Double circumcenter() {
			
			LinearEQ one = perpendicularBisector(PointA, PointB);
			LinearEQ two = perpendicularBisector(PointC, PointB);
			return new Point2D.Double(one.xSol(two), one.ySol(two));
		}
		
		public Point2D.Double centroid() {
			LinearEQ one = median(PointA, PointB, PointC);
			LinearEQ two = median(PointC, PointB, PointA);
			return new Point2D.Double(one.xSol(two), one.ySol(two));
			
		}
		
		public Point2D.Double orthocenter() {
			
			LinearEQ one = altitude(PointA, PointB, PointC);
			LinearEQ two = altitude(PointC, PointB, PointA);
			
			return new Point2D.Double(one.xSol(two), one.ySol(two));
		}
			
			
		public double Perimeter(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
			return sideLength(a,b) + sideLength(b,c) +sideLength(a,c);
			
		}
		public String Perimeter() {
			double perim = roundTwoDecimals(sideLength(PointA, PointB) 
					+ sideLength(PointB, PointC) + sideLength(PointA, PointC));
			return "\nThe triangle has a perimeter of " + perim + " units";
			
		}
		
		public String areaHerons() {
			double s = (roundTwoDecimals(sideLength(PointA, PointB) 
					+ sideLength(PointB, PointC) + sideLength(PointA, PointC))) / 2.0;
			
			double area = Math.sqrt(s * (s - sideLength(PointA, PointB))
					* (s -  sideLength(PointB, PointC))
					* (s -  sideLength(PointA, PointC)));
			area = roundTwoDecimals(area);
			return "and an area of " + area + " square units";
					
		}
		
		
		
		public boolean isIsosceles() {
			double sideC = roundTwoDecimals(sideLength(PointA, PointB));
			double sideB = roundTwoDecimals(sideLength(PointA, PointC));
			double sideA = roundTwoDecimals(sideLength(PointB, PointC));
			
			return (sideA == sideB) || (sideB == sideC) || (sideA == sideC);
			
		}
		
		public boolean isScalene() {
			double sideC = roundTwoDecimals(sideLength(PointA, PointB));
			double sideB = roundTwoDecimals(sideLength(PointA, PointC));
			double sideA = roundTwoDecimals(sideLength(PointB, PointC));
			
			return (sideA != sideB) && (sideB != sideC) && (sideA != sideC);
			
		}
		
		
		public boolean isEquilateral() {
			double sideC = roundTwoDecimals(sideLength(PointA, PointB));
			double sideB = roundTwoDecimals(sideLength(PointA, PointC));
			double sideA = roundTwoDecimals(sideLength(PointB, PointC));
			
			return (sideA == sideB) && (sideB == sideC);
			
		}
		
		public boolean isRight() {
			double angleA = roundTwoDecimals(vertexAngle(PointA, PointB, PointC));
			double angleB = roundTwoDecimals(vertexAngle(PointB, PointA, PointC));
			double angleC = roundTwoDecimals(vertexAngle(PointC, PointA, PointB));
			return ((angleA == 90.0) || (angleB == 90.0) || (angleC == 90.0));
		}

		public boolean isAcute() {
			double angleA = roundTwoDecimals(vertexAngle(PointA, PointB, PointC));
			double angleB = roundTwoDecimals(vertexAngle(PointB, PointA, PointC));
			double angleC = roundTwoDecimals(vertexAngle(PointC, PointA, PointB));
			
			return angleA < 90.0 && angleB < 90.0 && angleC < 90.0;
		}
		
		public boolean isObtuse() {
			double angleA = roundTwoDecimals(vertexAngle(PointA, PointB, PointC));
			double angleB = roundTwoDecimals(vertexAngle(PointB, PointA, PointC));
			double angleC = roundTwoDecimals(vertexAngle(PointC, PointA, PointB));
			
			return angleA > 90.0 || angleB > 90.0 || angleC > 90.0;
		}
		
		
	
		public static void main(String[] args) {
			Point2D.Double p1 = new Point2D.Double(3,9);
			Point2D.Double p2 = new Point2D.Double(8,0);
			Point2D.Double p3 = new Point2D.Double(5,10);
			
			Triangle one = new Triangle(p1, p2, p3);
			System.out.println(midpoint(p1, p2));
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
