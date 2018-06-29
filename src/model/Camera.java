package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Point3D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Camera {
	
	private Canvas				canvas;
	private GraphicsContext		gc;
	private PixelWriter			px;
	
	private List<Mesh>			meshes;
	
	private double				ratio;
	
	private double				hFov;
	private double				hAlpha;
	private double				hBeta;
	
	private double				vFov;
	private double				vAlpha;
	
	private double				distance;
	private double				viewLimit = 500d;
	
	private double[][]			zBuffer;
	
	private int					centerX;
	private int 				centerY;
	
	
	
	
	public Camera(Canvas canvas) {
		meshes = new ArrayList<Mesh>();
		
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
		px = gc.getPixelWriter();
		
		centerX = (int) (canvas.getWidth() / 2);
		centerY = (int) (canvas.getHeight() / 2);
		
		zBuffer = new double[(int) canvas.getHeight()][(int) canvas.getWidth()];
	}
	
	
	public void setHorizontalFov(double hFov, double distance) {
		/*
		 * We convert degree into radians. This is because the Java uses radians when
		 * we call on the tangent method from the Math library.
		 */
		this.hFov = (hFov * (Math.PI * 2)) / 360;
		
		
		/*
		 * We divide FOV in two to get the angle between the leg from the point of view (POV)
		 * and the horizontal x-axis. 
		 */
		hAlpha = this.hFov / 2;
		
		
		/*
		 * Since Pi/2 is equal to 90 degree, the angle between the edge of the x-axis and the
		 * POV (the angle between the adjacent and the hypotenuse), is equal to Pi/2 - hAlpha. 
		 */
		hBeta = (Math.PI / 2) - hAlpha;
		
		
		/*
		 * Ratio between the height and the width. 
		 */
		ratio = canvas.getHeight() / canvas.getWidth();
		
		
		/*
		 * We find the vertical FOV and the vertical alpha.
		 */
		vFov = 2 * Math.atan(ratio / Math.tan(hBeta));
		vAlpha = vFov / 2;
		
		
		/*
		 * We store the distance from the camera and the origin where x=0, y=0 and z=0.
		 */
		this.distance = distance;
	}
	
	
	public void update() {
		/*
		 * When canvas changes size, we want the aspect ratio of the spinning object to
		 * remain the same. Hence, we need to calculate the new values for the ratio.
		 */
		ratio = canvas.getHeight() / canvas.getWidth();
		vFov = 2 * Math.atan(ratio / Math.tan(hBeta));
		vAlpha = vFov / 2;

		centerX = (int) (canvas.getWidth() / 2);
		centerY = (int) (canvas.getHeight() / 2);
		
		zBuffer = new double[(int) canvas.getHeight()][(int) canvas.getWidth()];
	}
	
	
	public void addMesh(Mesh mesh) {
		meshes.add(mesh);
	}
	
	
	private void sortVertices(List<Point3D> vertices) {
		List<Point3D>	buffer;
		boolean			addedToList;
		int				index;
		
		buffer = new ArrayList<Point3D>();

		
		/*
		 * We sort the vertices so that the point with the lowest y value
		 * is first in the list, and the point with the highest y value last.
		 * 
		 * If any points have same y value, it gets sorted ascending to x.
		 * 
		 * The sorting is done to simplify the algorithm for drawing.
		 */
		while(vertices.size() != 0) {
			index = 0;
			addedToList = false;
			
			while(!addedToList) {
				if(index == buffer.size()) {
					buffer.add(vertices.get(0));
					addedToList = true;
				} else {
					if(vertices.get(0).getY() == buffer.get(index).getY()) {
						if(vertices.get(0).getX() < buffer.get(index).getX()) {
							buffer.add(index,vertices.get(0));
							addedToList = true;
						}
					} else if(vertices.get(0).getY() < buffer.get(index).getY()) {
						buffer.add(index, vertices.get(0));
						addedToList = true;
					}
				}
				index++;
			}
			
			vertices.remove(0);
		}
		
		for(int i = 0; i < buffer.size(); i++) {
			vertices.add(buffer.get(i));
		}
	}
	
	
	public void render() {
		List<Polygon>	polygons;
		List<Point3D>	vertices;
		List<Point3D>	buffer;
		
		double x;
		double y;
		double z;
		double width;
		double height;
		double r;
		
		
		/*
		 * Clears the z-buffer.
		 */
		for(int i = 0; i < zBuffer.length; i++) {
			Arrays.fill(zBuffer[i], 9999);
		}
		
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		
		/*
		 * Grabs each mesh from the list with meshes
		 */
		for(Mesh mesh : meshes) {
			
			
			/*
			 * Grab all polygons in the mesh 
			 */
			polygons = mesh.getPolygons();
			
			
			/*
			 * Go through all the polygons
			 */
			for(int i = 0; i < polygons.size(); i++) {
				
				vertices = polygons.get(i).getVertices();
				
				
				/*
				 * This buffer is for holding the vertices after applying the FOV because
				 * we don't want to overwrite its original values. 
				 */ 
				buffer = new ArrayList<Point3D>();
				
				for(int j = 0; j < vertices.size(); j++) {
					/*
					 * z holds the distance from the camera to the z-point vectors
					 * in the shape object.
					 * 
					 * X is the horizontal point.
					 * Y is the altitude point.
					 * Z is the depth/distance point.
					 */
					z = distance + vertices.get(j).getZ();
					
					
					/*
					 * We calculate how much of the width is visible to our eye
					 * at the given z position. 
					 */
					width = z * Math.tan(hAlpha);
					
					
					/*
					 * Then we calculate the ratio between the x-points in the vectors 
					 * and the visible width.
					 */
					r = vertices.get(j).getX() / width;
					
					
					/*
					 * Since the screen is 2 dimensional, we need to find the x-point's
					 * position relative to the width of the canvas.
					 * Since origins is at the center of the screen, we must divide the
					 * width of the canvas by 2.
					 */
					x = r * (canvas.getWidth() / 2);
					
					
					/*
					 * The same operations are repeated again for the y-points.
					 */
					height = z * Math.tan(vAlpha);
					
					r = vertices.get(j).getY() / height;
					y = r * (canvas.getHeight() / 2);
					
					buffer.add(new Point3D(x,y,z));
				}
				
				draw(buffer, new Polygon(buffer.get(0), buffer.get(1), buffer.get(2), polygons.get(i).getColor()));
			}
		}
	}
	
	
	public void draw(List<Point3D> vertices, Polygon polygon) {
		sortVertices(vertices);
		
		
		/*
		 * It the first vertex has an y equal the second vertex on the (sorted) list,
		 * it means the triangle has its flat side up. 
		 */
		if(vertices.get(0).getY() == vertices.get(1).getY()) {
			drawFlatTop(polygon, vertices);
		
			
		/*
		 * If the second and third vertices y is equal each other, then the triangle
		 * has its flat side down.
		 */
		} else if(vertices.get(1).getY() == vertices.get(2).getY()) {
			drawFlatBottom(polygon, vertices);
			
			
		/*
		 * If the flat side is neither up or down, we split the triangle into two
		 * new pieces by finding a point v4.
		 */
		} else {
			double x4			= (vertices.get(0).getX() + ((vertices.get(1).getY() - vertices.get(0).getY()) / (vertices.get(2).getY() - vertices.get(0).getY())) * (vertices.get(2).getX() - vertices.get(0).getX()));
			double z4			= polygon.getZ(x4, vertices.get(1).getY());
			
			Point3D v4			= new Point3D(x4, vertices.get(1).getY(), z4);
			
			List<Point3D> temp	= new ArrayList<>();
			
			temp.add(vertices.get(0));
			temp.add(vertices.get(1));
			temp.add(v4);
			
			sortVertices(temp);
			drawFlatBottom(polygon, temp);
			
			temp.set(0, vertices.get(1));
			temp.set(1, v4);
			temp.set(2, vertices.get(2));
			
			sortVertices(temp);
			drawFlatTop(polygon, temp);
		}
	}
	
	
	private void drawFlatTop(Polygon polygon, List<Point3D> vertices) {
		/*
		 * curx holds the x value to the two legs of the triangle.   
		 */
		double curx1	= vertices.get(0).getX();
		double curx2	= vertices.get(1).getX();
		
		double deltaY	= vertices.get(2).getY() - vertices.get(0).getY();
		
		
		/*
		 * The slope is the ratio on how much curx changes in each direction for each y.
		 */
		double slopeX1	= (vertices.get(2).getX() - vertices.get(0).getX()) / deltaY;
		double slopeX2	= (vertices.get(2).getX() - vertices.get(1).getX()) / deltaY;
		
		Color color = polygon.getColor();
		
		double opacity;
		
		double x;
		double z;
		double y;

		double scanlineX;
		double scanlineY;
		
		
		/*
		 * scanlineY is the vertical coordinate to the pixel we want to draw.
		 * The coordinate is relative to origin being in the center of the canvas.
		 * y variable holds the position relative to the canvas.
		 */
		scanlineY = vertices.get(0).getY();
		y = centerY + scanlineY;
		if(y < 0){
			scanlineY = -centerY;
			y = 0;
		}
		
		/*
		 * We don't want to draw the pixels outside the canvas.
		 * 
		 * This method is not 100% optimal as it skips the whole vertex once it's outside the viewport.
		 * This means that when you zoom close in on a vertex, and one of it's corners is outside the 
		 * canvas, it is not drawn. The vertices behind might be drawn, making a graphical glitch where 
		 * it seems like the mesh is starting to dissolve.
		 */
		while(y > -1 && y < canvas.getHeight() && scanlineY < vertices.get(2).getY()) {
			
			
			/*
			 * scanlineX is the horizontal pixel coordinate. 
			 */
			scanlineX = curx1;
			x = Math.floor(centerX + scanlineX);
			if(x < 0) {
				scanlineX = -centerX;
				x = 0;
			}
			
			
			/*
			 * We don't want to draw outside  the canvas.
			 */
			while(x > -1 && x < canvas.getWidth() && scanlineX < curx2) {
				
				
				/*
				 * We request the z coordinate given the x,y position.
				 */
				z = polygon.getZ(scanlineX, scanlineY);
				
				
				/*
				 * We make the color weaker (transparent) if the distance is too far. 
				 */
				opacity = z / viewLimit;
				if(opacity > 1) opacity = 1;
				if(opacity < 0) opacity = 0;
				
				
				/*
				 * We check that another pixel closer to our view has not already been draw.
				 * If not, we can draw the pixel.
				 */
				if(z < zBuffer[(int)y][(int)x]) {
					zBuffer[(int)y][(int)x] = z;
//					gc.setFill(Color.color(color.getRed(), color.getGreen(), color.getBlue(),1 - opacity));
//					gc.fillRect(x, y, 1, 1);
					px.setColor((int)x, (int)y, Color.color(color.getRed(), color.getGreen(), color.getBlue(),1 - opacity));
				}
				
				scanlineX++;
				x++;
			}
			
		    curx1 += slopeX1;
			curx2 += slopeX2;

			scanlineY++;
			y++;
		}
	}
	
	
	private void drawFlatBottom(Polygon polygon, List<Point3D> vertices) {
		double curx1	= vertices.get(0).getX();
		double curx2	= vertices.get(0).getX();
		
		double deltaY	= vertices.get(2).getY() - vertices.get(0).getY();
		
		double slopeX1	= (vertices.get(1).getX() - vertices.get(0).getX()) / deltaY;
		double slopeX2	= (vertices.get(2).getX() - vertices.get(0).getX()) / deltaY;
		
		Color color = polygon.getColor();
		
		double opacity;
		
		double x;
		double z;
		double y;
		
		double scanlineX;
		double scanlineY;
		
		
		scanlineY = vertices.get(0).getY();
		y = centerY + scanlineY;
		if(y < 0) {
			scanlineY = -centerY;
			y = 0;
		}
		
		while(y > -1 && y < canvas.getHeight() && scanlineY < vertices.get(2).getY()) {
			
			scanlineX = curx1;
			x = Math.floor(centerX + scanlineX);
			if(x < 0) {
				scanlineX = -centerX;
				x = 0;
			}
			
			while(x > -1 && x < canvas.getWidth() && scanlineX < curx2) {
				z = polygon.getZ(scanlineX, scanlineY);
				opacity = z / viewLimit;
				if(opacity > 1) opacity = 1;
				if(opacity < 0) opacity = 0;
				
				if(z < zBuffer[(int)y][(int)x]) {
					zBuffer[(int)y][(int)x] = z;
//					gc.setFill(Color.color(color.getRed(), color.getGreen(), color.getBlue(),1 - opacity));
//					gc.fillRect(x, y, 1, 1);
					px.setColor((int)x, (int)y, Color.color(color.getRed(), color.getGreen(), color.getBlue(),1 - opacity));
				}
				
				scanlineX++;
				x++;
			}
			
		    curx1 += slopeX1;
			curx2 += slopeX2;

			scanlineY++;
			y++;
		}
	}
}
