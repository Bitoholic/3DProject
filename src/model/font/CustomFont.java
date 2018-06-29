package model.font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;


public class CustomFont {
	private static CustomFont						referer;
	
	private String									FONT		= "/font.png";
	private HashMap<Character, List<List<Double>>>	charList;
	private double									fontHeight;
	
	
	public static CustomFont getInstance() {
		if(referer == null)
			referer = new CustomFont();
		
		return referer;
	}
	
	
	private CustomFont() {
		List<Double>		fontData;
		List<List<Double>>	fontChar;
		Image				fontImg;
		PixelReader			pxr;
		int					curx;
		short				x;
		char				key = 'A';
		
		fontImg = new Image(FONT);
		pxr = fontImg.getPixelReader();
		
		fontHeight = fontImg.getHeight();
		curx = 0;
		x = 0;
		
		charList = new HashMap<>();
		
		while((curx + x) < fontImg.getWidth()) {
			Color color = pxr.getColor(curx + x, 0);
			
			if((color.getRed() == 1d && color.getGreen() == 0d && color.getBlue() == 0d) || x == (fontImg.getWidth() -1)) {
				
				fontChar = new ArrayList<>();
				
				for(int j = 0; j < fontImg.getHeight(); j++) {
					fontData = new ArrayList<Double>();
					
					for(int i = curx; i < (curx + x); i++) {
						color = pxr.getColor(i, j);
						
						if(color.getRed() == 1d && color.getGreen() == 0d && color.getBlue() == 0d)
							color = Color.color(0, 0, 0, 0);
						
						fontData.add(color.getOpacity());
					}
					
					fontChar.add(fontData);
				}
				
				charList.put(key, fontChar);
				
				key++;
				if(key == 91) { key = 32; }
				
				curx += x + 1;
				x = 0;
			}

			x++;
		}
	}
	
	
	public List<List<Double>> setText(String textString) {
		List<List<Double>> text = new ArrayList<>(new ArrayList<>());
		List<List<Double>> letter = new ArrayList<>();
		
		for(int j = 0; j < fontHeight; j++) {
			text.add(new ArrayList<Double>());
		}
		
		textString = textString.toUpperCase();
		
		for(int i = 0; i < textString.length(); i++) {
			letter = charList.get(textString.charAt(i));
			
			for(int y = 0; y < letter.size(); y++) {
				for(int x = 0; x < (letter.get(y).size()); x++) {
					double d = letter.get(y).get(x);
					text.get(y).add(d);
				}
			}
		}
		
		return text;
	}
}
