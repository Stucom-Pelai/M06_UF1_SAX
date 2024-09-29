package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import model.Product;
import sax.SaxReader;
import sax.SaxWriter;

public class main {

	public static void main(String[] args) {
		ArrayList<Product> products = null;

		// Read an existing xml document
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			File file = new File ("files/productsInput.xml");
			SaxReader saxReader = new SaxReader();
			parser.parse(file, saxReader);
			products = saxReader.getProducts();
			
		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
		} catch (IOException e) {
			System.out.println("ERROR file not found");
		}
		
		// Create a new xml document by stock in descending order
		products.sort(Comparator.comparingInt(Product::getStock).reversed());
		SaxWriter saxWriter = new SaxWriter();
		saxWriter.generateDocument(products);
	}

}
