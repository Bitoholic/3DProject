package model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class Polygon implements MatrixTransformation {
	
	private List<Point3D>	vertices;
	private Color			color;
	
	private Point3D			v1;
	private Point3D			v2;
	
	private Point3D			cp;
	
	private double			i;
	private double			j;
	private double			k;
	
	private double			a1;
	private double			b1;
	private double			c1;
	
	private double			constant;
	
	
	public Polygon(Point3D v1, Point3D v2, Point3D v3, Color color) {
		vertices = new ArrayList<Point3D>();
		
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		
		this.color = color;
		
		getNormalVector();
	}
	
	
	public List<Point3D> getVertices() {
		return vertices;
	}
	
	
	public Color getColor() {
		return color;
	}
	
	
	@Override
	public void rotateXaxis(double alpha) {
		/*
		 * This is for rotating a vector around the X-axis.
		 * General matrix transformation. We have learned this at school.
		 * See page 486 in our math book (chapter 9).
		 */
		double[][] matrix = {
				{                1,                0,               0  },
				{                0,  Math.cos(alpha), -Math.sin(alpha) },
				{                0,  Math.sin(alpha),  Math.cos(alpha) }};
		
		doMatrixMultiplication(matrix);
	}
	
	
	@Override
	public void rotateYaxis(double alpha) {
		double[][] matrix = {
				{  Math.cos(alpha),                0,  Math.sin(alpha) },
				{                0,                1,              0   },
				{ -Math.sin(alpha),                0,  Math.cos(alpha) }};
		
		doMatrixMultiplication(matrix);
	}
	
	
	@Override
	public void rotateZaxis(double alpha) {
		double[][] matrix = {
				{  Math.cos(alpha), -Math.sin(alpha),              0   },
				{  Math.sin(alpha),  Math.cos(alpha),              0   },
				{                0,                0,              1   }};
		
		doMatrixMultiplication(matrix);
	}
	
	
	@Override
	public void scaleObject(double size) {
		double[][] matrix = {
				{             size,                0,              0   },
				{                0,             size,              0   },
				{                0,                0,           size   }};
		
		doMatrixMultiplication(matrix);
	}
	
	
	private void doMatrixMultiplication(double[][] matrix) {
		Point3D	vertex;
		
		double	x;
		double	y;
		double	z;
		
		for(int i = 0; i < vertices.size(); i++) {
			vertex = vertices.get(i);
			
			x = (matrix[0][0] * vertices.get(i).getX()) + (matrix[0][1] * vertices.get(i).getY() + (matrix[0][2] * vertices.get(i).getZ()));
			y = (matrix[1][0] * vertices.get(i).getX()) + (matrix[1][1] * vertices.get(i).getY() + (matrix[1][2] * vertices.get(i).getZ()));
			z = (matrix[2][0] * vertices.get(i).getX()) + (matrix[2][1] * vertices.get(i).getY() + (matrix[2][2] * vertices.get(i).getZ()));
			
			vertex = new Point3D(x, y, z);
			vertices.set(i, vertex);
		}
		
		getNormalVector();
	}
	
	
	private void getNormalVector() {
		/*
		 * The three coordinates we have defines the corners of a triangle.
		 * Assume we want to find the z coordinate to a point (x,y) inside 
		 * this triangle. To do this, we must first know the vectors to the
		 * triangle.
		 * 
		 * We assume the first point in space is (x1, y1, z1) and the second 
		 * point is (x2, y2, z2). The vector is then (x1-x2, y1-y2, z1-z2).
		 * 
		 * In this demonstration, I take for granted that the two vectors 
		 * define a plane.
		 */
		v1 = vertices.get(0).subtract(vertices.get(1));
		v2 = vertices.get(0).subtract(vertices.get(2));
		

		/*
		 * We must now find the crossproduct between the two planes, i.e. the
		 * vector that is perpendicular to both v1 and v2.
		 */
		cp = this.v1.crossProduct(this.v2);
		
		i = cp.getX();
		j = cp.getY();
		k = cp.getZ();
		
		
		/*
		 * This gives us the equation: i*a11 + j*a12 + k*a13 = c 
		 */
		a1 = vertices.get(0).getX();
		b1 = vertices.get(0).getY();
		c1 = vertices.get(0).getZ();
		
		constant = (i*a1) + (j*b1) + (k*c1);

		/*
		 * Now it's all about solving the equation for z:
		 * (i*x) + (j*y) + (k * z) = (i*a11) + (j*a12) + (k*a13)
		 * 
		 */
	}
	
	
	public double getZ(double x, double y) {
		return((1/k) * (constant - (i*x) - (j*y)));
	}
}
