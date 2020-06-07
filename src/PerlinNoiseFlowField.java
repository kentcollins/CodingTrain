
/***
 * Adapted from Dan Shiffman Coding Train Coding Challenge #24
 * https://thecodingtrain.com/CodingChallenges/024-perlinnoiseflowfield.html
 * 
 * This adaptation does not render the entire flow field; instead the flow
 * vector is calculated only for the relevant particle.
 */

import processing.core.PApplet;
import processing.core.PVector;

public class PerlinNoiseFlowField extends PApplet {

	Particle[] particles;
	int scale;
	float increment;
	float zOffset;
	int numRows;
	int numCols;
	boolean colorize;

	public static void main(String[] args) {
		PApplet.main(new String[] { "PerlinNoiseFlowField" });
	}

	public void settings() {
		size(800, 800);
		scale = 1;
		increment = 0.01f;
		zOffset = 0f;
	}

	public void setup() {
		numRows = height / scale;
		numCols = width / scale;
		particles = new Particle[10000];
		for (int i = 0; i < particles.length; i++) {
			particles[i] = new Particle();
		}
		background(255);
		colorMode(HSB, 360, 255, 255);
		colorize = true;
	}

	public void draw() {
		zOffset += increment/10;
		for (int i = 0; i < particles.length; i++) {
			particles[i].update();
			particles[i].display();
		}
	}

	public PVector getNoiseAt(Particle p) {
		int gridX = (int) p.loc.x / scale;
		int gridY = (int) p.loc.y / scale;
		return PVector.fromAngle(noise(gridX * increment, gridY * increment, zOffset) * TWO_PI * 2);
	}

	public void keyPressed() {
		if (key == 's') {
			save("output.png");
		}
		if (key == 'c') {
			colorize = !colorize;
		}
	}

	public void mousePressed() {

	}

	class Particle {
		PVector loc, vel, acc, prev;
		float hue;

		Particle() {
			loc = new PVector(random(width), random(height));
			vel = PVector.random2D();
			acc = new PVector(0, 0);
			prev = loc.copy();
			hue = random(360);
		}

		void update() {
			prev = loc.copy();
			try {
				PVector nearest = getNoiseAt(this);
				acc.add(nearest);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Error: loc.x " + loc.x + ", loc.y " + loc.y);
			}
			vel.add(acc);
			loc.add(vel);
			acc.mult(0);
			vel.normalize();
			wrap();
		}

		void display() {
			if (colorize) {
				stroke(hue, 255, 255, 5);
			} else {
			stroke(0, 5);
			}
			strokeWeight(1);
			line(loc.x, loc.y, prev.x, prev.y);
		}

		void wrap() {
			if (loc.x >= width) {
				loc.x = 0;
				prev.x = 0;
			}
			if (loc.x < 0) {
				loc.x = width - 1;
				prev.x = width - 1;
			}
			if (loc.y >= height) {
				loc.y = 0;
				prev.y = 0;
			}
			if (loc.y < 0) {
				loc.y = height - 1;
				prev.y = height - 1;
			}
		}
	}

}
