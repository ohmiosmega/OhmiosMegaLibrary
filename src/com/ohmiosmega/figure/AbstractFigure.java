package com.ohmiosmega.figure;

import java.awt.Color;
import java.awt.Graphics;

import com.ohmiosmega.persistence.DataBinRecorder;
import com.ohmiosmega.persistence.DataUtilities;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 30/03/2019
 **/
public abstract class AbstractFigure implements DataBinRecorder<AbstractFigure> {
	protected long id;
	protected float x, y, width, height;
	protected Color color;

	public AbstractFigure() {
	}

	public AbstractFigure(int x, int y, int width, int height) {
		this(x, y, width, height, null);
	}

	public AbstractFigure(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public abstract void draw(Graphics g);

	@Override
	public byte[] getBytes() {
		synchronized (this) {
			return DataUtilities.joinBytes(DataUtilities.toBytes(this.id), DataUtilities.toBytes(this.x),
					DataUtilities.toBytes(this.y), DataUtilities.toBytes(this.width),
					DataUtilities.toBytes(this.height),
					DataUtilities.toBytes(this.color == null ? (int) -1 : this.color.getRGB()));
		}
	}

	@Override
	public AbstractFigure getData(byte[] data) {
		synchronized (this) {
			DataUtilities du = new DataUtilities(data);
			this.id = du.getLong();
			this.x = du.getFloat();
			this.y = du.getFloat();
			this.width = du.getFloat();
			this.height = du.getFloat();
			int color = du.getInt();
			if (color == -1)
				this.color = null;
			else
				this.color = new Color(color);
			return this;
		}
	}

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
		this.color = color;
	}

	public synchronized long getId() {
		return id;
	}
}
