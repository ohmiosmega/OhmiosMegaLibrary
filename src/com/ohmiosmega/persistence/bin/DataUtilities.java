package com.ohmiosmega.persistence.bin;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 2/04/2019
 **/
public class DataUtilities {
	private int index;
	private byte[] data;

	public DataUtilities(byte[] data) {
		this.data = data;
		this.index = 0;
	}

	public static <T extends DataBinRecorder<T>> byte[] toBytes(T data) {
		return data.getBytes();
	}

	public static <T extends DataBinRecorder<T>> byte[] toBytes(ArrayList<T> data) {
		byte[][] bytes = new byte[data.size()][];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = data.get(i).getBytes();
		}
		return joinBytes(bytes);
	}

	public static <T extends DataBinRecorder<T>> byte[] toBytes(T[] data) {
		byte[][] bytes = new byte[data.length][];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = data[i].getBytes();
		}
		return joinBytes(bytes);
	}

	public static byte[] toBytes(boolean data) {
		return new byte[] { (byte) (data ? 1 : 0) };
	}

	public static byte[] toBytes(String data) {
		byte[] b = new byte[data.length()];
		for (int i = 0; i < data.length(); i++) {
			b[i] = (byte) data.charAt(i);
		}
		return b;
	}

	public static byte[] toBytes(byte data) {
		return new byte[] { data };
	}

	public static byte[] toBytes(short data) {
		byte[] result = new byte[2];
		for (int i = 1; i >= 0; i--) {
			result[i] = (byte) (data & 0xFF);
			data >>= 2;
		}
		return result;
	}

	public static byte[] toBytes(int data) {
		byte[] result = new byte[4];
		for (int i = 3; i >= 0; i--) {
			result[i] = (byte) (data & 0xFF);
			data >>= 4;
		}
		return result;
	}

	public static byte[] toBytes(long data) {
		byte[] result = new byte[8];
		for (int i = 7; i >= 0; i--) {
			result[i] = (byte) (data & 0xFF);
			data >>= 8;
		}
		return result;
	}

	public static byte[] toBytes(float data) {
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putFloat(data);
		return bytes;
	}

	public static byte[] toBytes(double data) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(data);
		return bytes;
	}

	public static byte[] toBytes(char data) {
		return new byte[] { (byte) data };
	}

	public static byte[] joinBytes(byte[]... data) {
		int size = 0;
		for (byte[] bs : data) {
			size += bs.length;
		}

		byte[] bytes = new byte[size];
		byte[] aux = null;
		for (int i = 0, c = 0; i < data.length; i++) {
			aux = data[i];
			for (int j = 0; j < aux.length; j++) {
				bytes[c] = aux[j];
				c++;
			}
		}
		return bytes;
	}

	public static String toString(byte[] data) {
		return new String(toChars(data));
	}

	public static boolean toBoolean(byte[] data) {
		return data[0] == 1;
	}

	public static boolean toBoolean(byte data) {
		return data == 1;
	}

	public static short toShort(byte[] data) {
		return ByteBuffer.wrap(data).getShort();
	}

	public static int toInt(byte[] data) {
		return ByteBuffer.wrap(data).getInt();
	}

	public static long toLong(byte[] data) {
		return ByteBuffer.wrap(data).getLong();
	}

	public static float toFloat(byte[] data) {
		return ByteBuffer.wrap(data).getFloat();
	}

	public static double toDouble(byte[] data) {
		return ByteBuffer.wrap(data).getDouble();
	}

	public static char[] toChars(byte[] data) {
		char[] cs = new char[data.length];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = toChar(data[i]);
		}
		return cs;
	}

	public static char toChar(byte b) {
		return (char) (b < 0 ? b + 256 : b);
	}

	public static byte[] fractionOf(byte[] bytes, int indexInit, int size) {
		byte[] aux = new byte[size];
		for (int i = 0; i < aux.length; i++)
			aux[i] = bytes[i + indexInit];
		return aux;
	}

	public static String cutString(String data, int cut, char c) {
		if (data.length() == cut)
			return data;
		String out = "";
		out += data;

		if (out.length() < cut)
			return cutString(out += c, cut, c);

		return out.substring(0, cut);
	}

	public static byte[] toBytes(String string, int cut, char c) {
		return toBytes(cutString(string, cut, c));
	}

	public byte getByte() {
		byte b = this.data[index];
		index++;
		return b;
	}

	public boolean getBoolean() {
		boolean b = data[index] == 1;
		index++;
		return b;
	}

	public short getShort() {
		short s = toShort(fractionOf(data, index, 2));
		index += 2;
		return s;
	}

	public int getInt() {
		int i = toInt(fractionOf(data, index, 4));
		index += 4;
		return i;
	}

	public long getLong() {
		long l = toLong(fractionOf(data, index, 8));
		index += 8;
		return l;
	}

	public float getFloat() {
		float f = toFloat(fractionOf(data, index, 4));
		index += 4;
		return f;
	}

	public double getDouble() {
		double d = toDouble(fractionOf(data, index, 8));
		index += 8;
		return d;
	}

	public String getString(int length) {
		String s = toString(fractionOf(data, index, length));
		index += length;
		return s;
	}

	public void seek(int sesk) {
		this.index = sesk;
	}
}
