package com.ohmiosmega.persistence.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 3/04/2019
 **/
public interface DataXMLRecorder<T> {

	public T getData(Element element);

//	public Element getElement(Document document);

	public Element getElement(String elementId, Document document);

}
