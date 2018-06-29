package application;


import java.util.List;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import model.Camera;
import model.Mesh;
import model.font.CustomFont;


public class Main extends Application {
	
	private Stage				stage;
	private	Scene				scene;
	private Pane				root;
	private Canvas				canvas;
	
	private Camera				camera;
	private Mesh				meshBox;
	private Mesh				meshStar;
	
	private AnimationTimer		at;
	
	private double 				hFov;
	private double				distance;
	private double				previousX;
	
	/*
	 * OffsetX and OffsetY are used together with demoText for defining
	 * where the text shall start.
	 */
	private List<List<Double>>	demoText;
	private int 				offsetX;
	private double				offsetY[];
	private double 				scanline;
	
	private PixelWriter 		px;
	

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void start(Stage ps) throws Exception {
		canvas	= new Canvas(800, 600);
		root	= new Pane(canvas);
		scene	= new Scene(root);
		
		scene.setFill(Color.color(0.03, 0.06, 0.09, 1));
		ps.setScene(scene);
		ps.show();
		
		stage = ps;
		
		px = canvas.getGraphicsContext2D().getPixelWriter();
		
		initializeListeners();
		startAnimation();
	}

	
	private void initializeListeners() {
		scene.widthProperty() .addListener((obsv, oldv, newv) -> {
			canvas.setWidth (newv.doubleValue());
			camera.update();
			updateSinusTable();
		});
		
		scene.heightProperty().addListener((obsv, oldv, newv) -> {
			canvas.setHeight(newv.doubleValue());
			camera.update();
			updateSinusTable();
		});
		
		scene.setOnScroll((ScrollEvent se) -> {
			if(se.getDeltaY() < 0 ) {
				if(distance > 0)
					distance -= 10;
			} else {
				if(distance < 1000)
					distance += 10;
			}
			
			camera.setHorizontalFov(hFov, distance);
			
			stage.setTitle("View cone: " + hFov + " / distance: " + distance);
		});
		
		scene.setOnMousePressed((MouseEvent me) -> {
			previousX = me.getX();
		});
		
		scene.setOnMouseDragged((MouseEvent me) -> {
			if((me.getX() - previousX) < 0) {
				if(hFov < 180)
					hFov += 1;
			} else {
				if(hFov > 0)
					hFov -= 1;
			}
			
			camera.setHorizontalFov(hFov, distance);
			previousX = me.getX();
			
			stage.setTitle("View cone: " + hFov + " / distance: " + distance);
		});
	}
	
	private double s = 0;
	private byte t = 0;
	private void startAnimation() {
		demoText = CustomFont.getInstance().setText("Welcome to my spinning fantasy world");
		updateSinusTable();

		meshBox = model.Meshes.meshBox2();
		meshStar = model.Meshes.meshStar();
		
		distance = 350;
		hFov = 100;
		
		stage.setTitle("View cone: " + hFov + " / distance: " + distance);
		
		camera = new Camera(canvas);
		camera.addMesh(meshBox);
		camera.addMesh(meshStar);
		camera.setHorizontalFov(hFov, distance);
		camera.render();
		
		at = new AnimationTimer() {

			@Override
			public void handle(long now) {
				meshBox.rotateXaxis(Math.sin(s/200));
				meshBox.rotateYaxis(0.002);
				meshBox.rotateZaxis(0.004);
				
				meshStar.rotateYaxis(-0.001);
				
				camera.render();
				drawText();
				
				if(t == 0)
					s += 0.01;
				else
					s -= 0.01;
				
				if(s > (Math.PI)) t = 1;
				if(s < (-Math.PI)) t = 0;
				
				offsetX-= 3;
				if(offsetX < -demoText.get(0).size())
					offsetX = (int) canvas.getWidth();
			}
			
		};
		
		at.start();
	}
	
	
	private void drawText() {
		double opacity;
		
		for(int x = 0; x < demoText.get(0).size(); x++) {
			if((offsetX + x) < canvas.getWidth() && (offsetX + x) > 0) {
				for(int y = 0; y < demoText.size(); y++) {
					opacity = demoText.get(y).get(x);
					if(opacity != 0)
						px.setColor(offsetX + x, (int)(scanline + y + offsetY[offsetX + x]), Color.color(1, 1, 1, opacity));
				}
			}
		}
	}
	
	
	public void updateSinusTable() {
		double alpha = 0;
		
		scanline = canvas.getHeight() - (64+80);
		offsetY = new double[(int)canvas.getWidth()];
		
		for(int i=0;i<offsetY.length;i++) {
			offsetY[i] = 32 * Math.sin(alpha);
			alpha += ((4 * Math.PI) / canvas.getWidth());
		}
	}
}
