package model;

import java.util.ArrayList;
import java.util.List;

public class Mesh implements MatrixTransformation {

	private List<Polygon>	mesh;
	
	
	public Mesh() {
		mesh = new ArrayList<Polygon>();
	}
	
	
	public void addPolygons(Polygon...polygons) {
		for(Polygon polygon : polygons) {
			mesh.add(polygon);
		}
	}
	
	
	public List<Polygon> getPolygons() {
		return mesh;
	}
	
	
	@Override
	public void rotateXaxis(double alpha) {
		 for(Polygon polygon : mesh) {
			 polygon.rotateXaxis(alpha);
		 }
	}


	@Override
	public void rotateYaxis(double alpha) {
		 for(Polygon polygon : mesh) {
			 polygon.rotateYaxis(alpha);
		 }
	}


	@Override
	public void rotateZaxis(double alpha) {
		 for(Polygon polygon : mesh) {
			 polygon.rotateZaxis(alpha);
		 }
	}


	@Override
	public void scaleObject(double size) {
		 for(Polygon polygon : mesh) {
			 polygon.scaleObject(size);
		 }
	}
}
