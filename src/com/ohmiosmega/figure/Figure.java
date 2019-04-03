package com.ohmiosmega.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 30/03/2019
 **/
public abstract class Figure {
	protected Figure mainFigure;
	protected float x, y, width, height;
	protected Color color;

	public abstract void draw(Graphics g);

	public synchronized float getX() {
		return x;
	}

	public synchronized float getY() {
		return y;
	}

	public synchronized float getWidth() {
		return width;
	}

	public synchronized float getHeight() {
		return height;
	}

	public synchronized void setX(float x) {
		this.x = x;
	}

	public synchronized void setY(float y) {
		this.y = y;
	}

	public synchronized void setWidth(float width) {
		this.width = width;
	}

	public synchronized void setHeight(float height) {
		this.height = height;
	}

	public synchronized Color getColor() {
		return color;
	}

	public synchronized void setColor(Color color) {
		this.color=color;
	}
	
	public synchronized void setMainFigure(Figure mainFigure) {
		this.mainFigure = mainFigure;
	}
	
	public synchronized Figure getMainFigure() {
		return mainFigure;
	}

}
