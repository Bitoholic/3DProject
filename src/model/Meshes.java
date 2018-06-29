package model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class Meshes {
	
	public static Mesh meshBox() {
		Mesh box = new Mesh();
		Polygon	polygon1;
		Polygon polygon2;
		
		// FRONT
		polygon1 = new Polygon(new Point3D(-50, -50, -50), new Point3D( 50, -50, -50), new Point3D( 50, 50, -50), Color.CHARTREUSE);
		polygon2 = new Polygon(new Point3D(-50, -50, -50), new Point3D(-50,  50, -50), new Point3D( 50, 50, -50), Color.CHARTREUSE);
		box.addPolygons(polygon1, polygon2);
		
//		// BACK
//		polygon1 = new Polygon(new Point3D(-50, -50,  50), new Point3D( 50, -50,  50), new Point3D( 50, 50,  50), Color.PALEVIOLETRED);
//		polygon2 = new Polygon(new Point3D(-50, -50,  50), new Point3D(-50,  50,  50), new Point3D( 50, 50,  50), Color.PALEVIOLETRED);
//		box.addPolygons(polygon1, polygon2);
//		
//		// LEFT
//		polygon1 = new Polygon(new Point3D(-50, -50, -50), new Point3D(-50, -50,  50), new Point3D(-50, 50,  50), Color.AQUAMARINE);
//		polygon2 = new Polygon(new Point3D(-50, -50, -50), new Point3D(-50,  50, -50), new Point3D(-50, 50,  50), Color.AQUAMARINE);
//		box.addPolygons(polygon1, polygon2);
//
//		// RIGHT
//		polygon1 = new Polygon(new Point3D( 50, -50, -50), new Point3D( 50, -50,  50), new Point3D( 50, 50,  50), Color.CORAL);
//		polygon2 = new Polygon(new Point3D( 50, -50, -50), new Point3D( 50,  50, -50), new Point3D( 50, 50,  50), Color.CORAL);
//		box.addPolygons(polygon1, polygon2);
//		
//		// TOP
//		polygon1 = new Polygon(new Point3D(-50, -50, -50), new Point3D( 50, -50, -50), new Point3D( 50,-50,  50), Color.CORNFLOWERBLUE);
//		polygon2 = new Polygon(new Point3D(-50, -50, -50), new Point3D(-50, -50,  50), new Point3D( 50,-50,  50), Color.CORNFLOWERBLUE);
//		box.addPolygons(polygon1, polygon2);
//		
//		// BOTTOM
//		polygon1 = new Polygon(new Point3D(-50,  50, -50), new Point3D( 50,  50, -50), new Point3D( 50, 50,  50), Color.ORANGERED);
//		polygon2 = new Polygon(new Point3D(-50,  50, -50), new Point3D(-50,  50,  50), new Point3D( 50, 50,  50), Color.ORANGERED);
//		box.addPolygons(polygon1, polygon2);

		return box;
	}
	
	
	public static Mesh meshStar() {
		Mesh plane = new Mesh();
		Polygon polygon1;
		Polygon polygon2;
		
		// BOTTOM
		polygon1 = new Polygon(new Point3D(   0, 100,   0), new Point3D( -60, 100, -20), new Point3D( -60, 100,  20), Color.GREEN);
		polygon2 = new Polygon(new Point3D(   0, 100,   0), new Point3D( -60, 100,  20), new Point3D( -20, 100,  60), Color.LIMEGREEN);
		plane.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(   0, 100,   0), new Point3D( -20, 100,  60), new Point3D(  20, 100,  60), Color.YELLOW);
		polygon2 = new Polygon(new Point3D(   0, 100,   0), new Point3D(  20, 100,  60), new Point3D(  60, 100,  20), Color.ORANGE);
		plane.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(   0, 100,   0), new Point3D(  60, 100,  20), new Point3D(  60, 100, -20), Color.RED);
		polygon2 = new Polygon(new Point3D(   0, 100,   0), new Point3D(  60, 100, -20), new Point3D(  20, 100, -60), Color.MAGENTA);
		plane.addPolygons(polygon1, polygon2);
		
		polygon1 = new Polygon(new Point3D(   0, 100,   0), new Point3D(  20, 100, -60), new Point3D( -20, 100, -60), Color.PURPLE);
		polygon2 = new Polygon(new Point3D(   0, 100,   0), new Point3D( -20, 100, -60), new Point3D( -60, 100, -20), Color.ROYALBLUE);
		plane.addPolygons(polygon1, polygon2);
		
		polygon1 = new Polygon(new Point3D( -60, 100, -20), new Point3D(-140, 100,   0), new Point3D( -60, 100,  20), Color.GREEN);
		polygon2 = new Polygon(new Point3D( -60, 100,  20), new Point3D( -80, 100,  80), new Point3D( -20, 100,  60), Color.LIMEGREEN);
		plane.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( -20, 100,  60), new Point3D(   0, 100, 140), new Point3D(  20, 100,  60), Color.YELLOW);
		polygon2 = new Polygon(new Point3D(  20, 100,  60), new Point3D(  80, 100,  80), new Point3D(  60, 100,  20), Color.ORANGE);
		plane.addPolygons(polygon1, polygon2);
		
		polygon1 = new Polygon(new Point3D(  60, 100,  20), new Point3D( 140, 100,   0), new Point3D(  60, 100, -20), Color.RED);
		polygon2 = new Polygon(new Point3D(  60, 100, -20), new Point3D(  80, 100, -80), new Point3D(  20, 100, -60), Color.MAGENTA);
		plane.addPolygons(polygon1, polygon2);
		
		polygon1 = new Polygon(new Point3D(  20, 100, -60), new Point3D(   0, 100,-140), new Point3D( -20, 100, -60), Color.PURPLE);
		polygon2 = new Polygon(new Point3D( -20, 100, -60), new Point3D( -80, 100, -80), new Point3D( -60, 100, -20), Color.ROYALBLUE);
		plane.addPolygons(polygon1, polygon2);
		
		return plane;
	}
	
	
	public static Mesh meshBox2() {
		Mesh box = new Mesh();
		Polygon	polygon1;
		Polygon polygon2;
		
		// FRONT
		polygon1 = new Polygon(new Point3D(-40, -40, -40), new Point3D( 40, -40, -40), new Point3D( 40, 40, -40), Color.LIGHTSTEELBLUE);
		polygon2 = new Polygon(new Point3D(-40, -40, -40), new Point3D(-40,  40, -40), new Point3D( 40, 40, -40), Color.LIGHTSTEELBLUE);
		box.addPolygons(polygon1, polygon2);
		
		// FRONT
		polygon1 = new Polygon(new Point3D(-25, -25, -50), new Point3D( 25, -25, -50), new Point3D( 25, -20, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(-25, -25, -50), new Point3D(-25, -20, -50), new Point3D( 25, -20, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-25, -20, -50), new Point3D(-15, -20, -50), new Point3D(-15, -15, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(-25, -20, -50), new Point3D(-25, -15, -50), new Point3D(-15, -15, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-15, -20, -50), new Point3D(-05, -20, -50), new Point3D(-05, -15, -50), Color.GRAY);
		polygon2 = new Polygon(new Point3D(-15, -20, -50), new Point3D(-15, -15, -50), new Point3D(-05, -15, -50), Color.GRAY);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-05, -20, -50), new Point3D(  5, -20, -50), new Point3D(  5, -10, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(-05, -20, -50), new Point3D(-05, -10, -50), new Point3D(  5, -10, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(  5, -20, -50), new Point3D( 15, -20, -50), new Point3D( 15, -15, -50), Color.GRAY);
		polygon2 = new Polygon(new Point3D(  5, -20, -50), new Point3D(  5, -15, -50), new Point3D( 15, -15, -50), Color.GRAY);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( 15, -20, -50), new Point3D( 25, -20, -50), new Point3D( 25, -15, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D( 15, -20, -50), new Point3D( 15, -15, -50), new Point3D( 25, -15, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-25, -15, -50), new Point3D(-20, -15, -50), new Point3D(-20,  25, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(-25, -15, -50), new Point3D(-25,  25, -50), new Point3D(-20,  25, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-20, -15, -50), new Point3D(-15, -15, -50), new Point3D(-15, -10, -50), Color.GRAY);
		polygon2 = new Polygon(new Point3D(-20, -15, -50), new Point3D(-20, -10, -50), new Point3D(-15, -10, -50), Color.GRAY);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-15, -15, -50), new Point3D(-05, -15, -50), new Point3D(-05, -10, -50), Color.WHITE);
		polygon2 = new Polygon(new Point3D(-15, -15, -50), new Point3D(-15, -10, -50), new Point3D(-05, -10, -50), Color.WHITE);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(  5, -15, -50), new Point3D( 15, -15, -50), new Point3D( 15, -10, -50), Color.WHITE);
		polygon2 = new Polygon(new Point3D(  5, -15, -50), new Point3D(  5, -10, -50), new Point3D( 15, -10, -50), Color.WHITE);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( 15, -15, -50), new Point3D( 20, -15, -50), new Point3D( 20, -10, -50), Color.GRAY);
		polygon2 = new Polygon(new Point3D( 15, -15, -50), new Point3D( 15, -10, -50), new Point3D( 20, -10, -50), Color.GRAY);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( 20, -15, -50), new Point3D( 25, -15, -50), new Point3D( 25,  25, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D( 20, -15, -50), new Point3D( 20,  25, -50), new Point3D( 25,  25, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-20, -10, -50), new Point3D(-10, -10, -50), new Point3D(-20,   0, -50), Color.WHITE);
		polygon2 = new Polygon(new Point3D(-20,   0, -50), new Point3D(-15,   0, -50), new Point3D(-15,  -5, -50), Color.WHITE);
		box.addPolygons(polygon1, polygon2);
		
		polygon1 = new Polygon(new Point3D(-15,  -5, -50), new Point3D(-10,  -5, -50), new Point3D(-10, -10, -50), Color.BLUE);
		box.addPolygons(polygon1);

		polygon1 = new Polygon(new Point3D(-10, -10, -50), new Point3D( -5, -10, -50), new Point3D( -5,  -5, -50), Color.BLUE);
		polygon2 = new Polygon(new Point3D(-10, -10, -50), new Point3D(-10,  -5, -50), new Point3D( -5,  -5, -50), Color.BLUE);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-15,  -5, -50), new Point3D(-10,  -5, -50), new Point3D(-10,   0, -50), Color.BLUE);
		polygon2 = new Polygon(new Point3D(-15,  -5, -50), new Point3D(-15,   0, -50), new Point3D(-10,   0, -50), Color.BLUE);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-10,  -5, -50), new Point3D( -5,  -5, -50), new Point3D( -5,   0, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D(-10,  -5, -50), new Point3D(-10,   0, -50), new Point3D( -5,   0, -50), Color.BLACK);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( -5, -10, -50), new Point3D(  0, -10, -50), new Point3D( -5,   0, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D( -5,   0, -50), new Point3D(  0,   0, -55), new Point3D(  0, -10, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(  0, -10, -50), new Point3D(  5, -10, -50), new Point3D(  5,   0, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(  0, -10, -50), new Point3D(  0,   0, -55), new Point3D(  5,   0, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( -5,   0, -50), new Point3D(  0,   0, -50), new Point3D(  0,   0, -55), Color.GOLD);
		polygon2 = new Polygon(new Point3D(  0,   0, -55), new Point3D(  0,   0, -50), new Point3D(  5,   0, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(  5, -10, -50), new Point3D( 10, -10, -50), new Point3D( 10,  -5, -50), Color.BLUE);
		polygon2 = new Polygon(new Point3D(  5, -10, -50), new Point3D(  5,  -5, -50), new Point3D( 10,  -5, -50), Color.BLUE);
		box.addPolygons(polygon1, polygon2);
		
		polygon1 = new Polygon(new Point3D( 10, -10, -50), new Point3D( 10,  -5, -50), new Point3D( 15,  -5, -50), Color.BLUE);
		box.addPolygons(polygon1);

		polygon1 = new Polygon(new Point3D( 10,  -5, -50), new Point3D( 15,  -5, -50), new Point3D( 15,   0, -50), Color.BLUE);
		polygon2 = new Polygon(new Point3D( 10,  -5, -50), new Point3D( 10,   0, -50), new Point3D( 15,   0, -50), Color.BLUE);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(  5,  -5, -50), new Point3D( 10,  -5, -50), new Point3D( 10,   0, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D(  5,  -5, -50), new Point3D(  5,   0, -50), new Point3D( 10,   0, -50), Color.BLACK);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( 10, -10, -50), new Point3D( 20, -10, -50), new Point3D( 20,   0, -50), Color.WHITE);
		polygon2 = new Polygon(new Point3D( 15,  -5, -50), new Point3D( 15,   0, -50), new Point3D( 20,   0, -50), Color.WHITE);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-20,   0, -50), new Point3D( 20,   0, -50), new Point3D( 20,   5, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(-20,   0, -50), new Point3D(-20,   5, -50), new Point3D( 20,   5, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);
		
		polygon1 = new Polygon(new Point3D(-20,   5, -50), new Point3D(-10,   5, -50), new Point3D(-20,  10, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(-20,  10, -50), new Point3D(-10,  10, -50), new Point3D(-10,   5, -50), Color.BLACK);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-10,   5, -50), new Point3D(  0,   5, -50), new Point3D(  0,  10, -50), Color.WHITE);
		polygon2 = new Polygon(new Point3D(-10,   5, -50), new Point3D(-10,  10, -50), new Point3D(  0,  10, -50), Color.WHITE);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(  0,   5, -50), new Point3D( 10,   5, -50), new Point3D( 10,  10, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D(  0,   5, -50), new Point3D(  0,  10, -50), new Point3D( 10,  10, -50), Color.BLACK);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( 10,   5, -50), new Point3D( 20,   5, -50), new Point3D( 20,  10, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D( 10,   5, -50), new Point3D( 10,  10, -50), new Point3D( 20,  10, -50), Color.BLACK);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-20,  10, -50), new Point3D( 20,  10, -50), new Point3D( 20,  15, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D(-20,  10, -50), new Point3D(-20,  15, -50), new Point3D( 20,  15, -50), Color.BLACK);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-20,  15, -50), new Point3D(-10,  15, -50), new Point3D(-10,  20, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D(-20,  15, -50), new Point3D(-20,  20, -50), new Point3D(-10,  20, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-10,  15, -50), new Point3D( -5,  15, -50), new Point3D(-10,  20, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D(-10,  20, -50), new Point3D( -5,  20, -50), new Point3D( -5,  15, -50), Color.RED);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( -5,  15, -50), new Point3D(  5,  15, -50), new Point3D(  5,  20, -50), Color.RED);
		polygon2 = new Polygon(new Point3D( -5,  15, -50), new Point3D( -5,  20, -50), new Point3D(  5,  20, -50), Color.RED);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(  5,  15, -50), new Point3D( 10,  15, -50), new Point3D( 10,  20, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D(  5,  15, -50), new Point3D(  5,  20, -50), new Point3D( 10,  20, -50), Color.RED);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D( 10,  15, -50), new Point3D( 20,  15, -50), new Point3D( 10,  20, -50), Color.BLACK);
		polygon2 = new Polygon(new Point3D( 10,  20, -50), new Point3D( 20,  20, -50), new Point3D( 20,  15, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		polygon1 = new Polygon(new Point3D(-20,  20, -50), new Point3D( 20,  20, -50), new Point3D( 20,  25, -50), Color.GOLD);
		polygon2 = new Polygon(new Point3D(-20,  20, -50), new Point3D(-20,  25, -50), new Point3D( 20,  25, -50), Color.GOLD);
		box.addPolygons(polygon1, polygon2);

		// BACK
		polygon1 = new Polygon(new Point3D(-50, -50,  50), new Point3D( 50, -50,  50), new Point3D( 50, 50,  50), Color.SADDLEBROWN);
		polygon2 = new Polygon(new Point3D(-50, -50,  50), new Point3D(-50,  50,  50), new Point3D( 50, 50,  50), Color.SADDLEBROWN);
		box.addPolygons(polygon1, polygon2);
		
		// LEFT
		polygon1 = new Polygon(new Point3D(-50, -50, -50), new Point3D(-50, -50,  50), new Point3D(-50, 50,  50), Color.SADDLEBROWN);
		polygon2 = new Polygon(new Point3D(-50, -50, -50), new Point3D(-50,  50, -50), new Point3D(-50, 50,  50), Color.SADDLEBROWN);
		box.addPolygons(polygon1, polygon2);

		// RIGHT
		polygon1 = new Polygon(new Point3D( 50, -50, -50), new Point3D( 50, -50,  50), new Point3D( 50, 50,  50), Color.SADDLEBROWN);
		polygon2 = new Polygon(new Point3D( 50, -50, -50), new Point3D( 50,  50, -50), new Point3D( 50, 50,  50), Color.SADDLEBROWN);
		box.addPolygons(polygon1, polygon2);
		
		// TOP
		polygon1 = new Polygon(new Point3D(-50, -50, -50), new Point3D( 50, -50, -50), new Point3D( 50,-50,  50), Color.SADDLEBROWN);
		polygon2 = new Polygon(new Point3D(-50, -50, -50), new Point3D(-50, -50,  50), new Point3D( 50,-50,  50), Color.SADDLEBROWN);
		box.addPolygons(polygon1, polygon2);
		
		// BOTTOM
		polygon1 = new Polygon(new Point3D(-50,  50, -50), new Point3D( 50,  50, -50), new Point3D( 50, 50,  50), Color.SADDLEBROWN);
		polygon2 = new Polygon(new Point3D(-50,  50, -50), new Point3D(-50,  50,  50), new Point3D( 50, 50,  50), Color.SADDLEBROWN);
		box.addPolygons(polygon1, polygon2);
		return box;
	}
}
