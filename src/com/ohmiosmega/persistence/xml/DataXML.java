package com.ohmiosmega.persistence.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 3/04/2019
 **/
public class DataXML<T extends DataXMLRecorder<T>> {
	protected Class<T> classType;
	protected File file;
	protected Document document;
	protected Element element;
	protected String elementId;
	protected ArrayList<T> objects;

	public DataXML(String path, String elementId, Class<T> classType) {
		this(new File(path), elementId, classType);
	}

	public DataXML(File file, String elementId, Class<T> classType) {
		this.file = file;
		this.objects = new ArrayList<>();
		this.elementId = elementId;
		this.classType = classType;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			if (file.exists()) {
				this.document = db.parse(this.file);
				this.element = (Element) this.document.getElementById(elementId + "s");
				this.read();
			} else {
				this.document = db.newDocument();
				this.element = this.document.createElement(elementId + "s");
				this.document.appendChild(this.element);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
		}
	}

	public void write(T data) {
		this.element.appendChild(data.getElement(this.elementId, this.document));
		this.save();
	}

	public void read() {
		this.document.getDocumentElement().normalize();
		NodeList list = this.document.getElementsByTagName(elementId);
		T t = null;
		try {
			t = this.classType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		}
		for (int i = 0; i < list.getLength(); i++) {
			this.objects.add(t.getData((Element) list.item(i)));
		}
	}

	private void save() {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult sr = new StreamResult(file);
			transformer.transform(source, sr);
		} catch (TransformerException e) {
		}
	}
}
