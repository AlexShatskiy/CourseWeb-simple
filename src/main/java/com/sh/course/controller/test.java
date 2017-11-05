package com.sh.course.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class test {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, FileNotFoundException {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		try {
			InputStream input = new FileInputStream("E:/workspace-EPAM/NewFile.xml");

			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

			System.out.println(process(reader, "com"));

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { Class c = Class.forName("com.sh.course.controller.Refl");
		 * 
		 * Abstr r = (Abstr) c.newInstance();
		 * 
		 * r.prin();
		 * 
		 * 
		 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
	}

	private static String process(XMLStreamReader reader, String com) throws XMLStreamException {

		Abstr a = null;
		String path = null;
		CommandTagName elementName = null;
		
		boolean flag = false;

		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = CommandTagName.getElementTagName(reader.getLocalName());
				switch (elementName) { 
				case COMMAND:
					break;
				
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();

				if (text.isEmpty()) {
					break;
				}
				
				switch (elementName) {     
				
				case NAME: 
					if(text.equals(com)){
						flag = true;
					}
					break;
				case REALIZATION: 
					if(flag){
						path = text;
					}
					break;	
				}
			break;
			}
		}
		return path;
	}
}
