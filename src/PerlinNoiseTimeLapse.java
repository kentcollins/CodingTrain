
/***
 * Adapted from Dan Shiffman Coding Train Coding Challenge #24
 * https://thecodingtrain.com/CodingChallenges/024-perlinnoiseflowfield.html
 */

import processing.core.PApplet;

public class PerlinNoiseTimeLapse extends PApplet {

	int numRows, numCols;
	int scale;
	float increment;
	float zOffset;

	public static void main(String[] args) {
		PApplet.main(new String[] { "PerlinNoiseTimeLapse" });
	}

	public void settings() {
		size(800, 800);
		scale = 20;
		increment = 0.1f;
		zOffset = 0f;
	}

	public void setup() {
		numRows = height / scale;
		numCols = width / scale;
	}

	public void draw() {
		float xOffset = 0;
		for (int r = 0; r < numRows; r++) {
			float yOffset = 0;
			for (int c = 0; c < numCols; c++) {
				pushMatrix();
				translate(c * scale, r * scale);
				fill(noise(xOffset, yOffset, zOffset) * 255);
				rect(0, 0, scale, scale);
				popMatrix();
				yOffset += increment;
			}
			xOffset += increment;
		}
		zOffset += increment / 50;
	}

	public void keyPressed() {
	}

	public void mousePressed() {
	}

	// other methods from processing.core.PApplet

}
