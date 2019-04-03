package com.ohmiosmega.persistence;
/**
* @author Andrés Felipe Chaparro Rosas
* @date 30/03/2019
**/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DataBin<T extends DataBinRecorder<T>> {
	private final int recordSize;
	private long quantity;
	private RandomAccessFile raf;
	private Class<T> classType;

	public DataBin(String name, int recordSize, Class<T> classType) throws FileNotFoundException {
		this.raf = new RandomAccessFile(name, "rw");

		try {
			this.raf.seek(0);
			this.quantity = this.raf.readLong();
		} catch (IOException e) {
			this.quantity = 0;
		}

		this.recordSize = recordSize;
		this.classType = classType;
	}

	public void write(T data) throws IOException {
		this.write(quantity, data);
	}

	public void write(long index, T data) throws IOException {
		this.raf.seek(0);
		this.raf.writeLong(quantity + 1);
		this.update(index, data);
		this.quantity++;
	}

	public T read(int index) throws IOException {
		this.raf.seek((this.recordSize * index) + 8);
		byte[] b = new byte[this.recordSize];
		this.raf.read(b);
		T t = null;
		try {
			t = this.classType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		}
		return t.getData(b);
	}
	
	public void update(long index, T data) throws IOException {
		this.raf.seek((recordSize * index) + 8);
		this.raf.write(data.getBytes());
	}
	
	public long getQuantity() {
		return quantity;
	}

}
