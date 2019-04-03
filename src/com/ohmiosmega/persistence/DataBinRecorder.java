package com.ohmiosmega.persistence;

/**
 * @author Andr�s Felipe Chaparro Rosas
 * @date 30/03/2019
 **/
public interface DataBinRecorder<T> {
	
	public byte[] getBytes();

	public T getData(byte[] data);
}
