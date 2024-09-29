package sax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;
import org.xml.sax.helpers.AttributesImpl;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerFactory;

import model.Product;

public class SaxWriter {
	private StringBuffer buffer;
	private StringWriter writer;

	public SaxWriter() {
		this.buffer = new StringBuffer();

		// Prepare the output writer
		this.writer = new StringWriter();

	}

	public void generateDocument(ArrayList<Product> products) {
		// Create a SAX Transformer Factory and TransformerHandler
		try {
			SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			TransformerHandler handler = saxTransformerFactory.newTransformerHandler();

			// Set transformer properties (like output format)
			Transformer transformer = handler.getTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			// Set the result to the writer
			handler.setResult(new StreamResult(writer));

			// Start writing the XML document
			handler.startDocument();

			// Prepare attributes and start <products> root element
			AttributesImpl atts = new AttributesImpl();
			handler.startElement("", "", "products", atts);

			// Iterate over each product
			for (Product product : products) {
				// Start <product> element with a 'name' attribute
				atts.clear();
				atts.addAttribute("", "", "name", "CDATA", product.getName());
				handler.startElement("", "", "product", atts);

				// Add <currency> element
				handler.startElement("", "", "currency", null);
				handler.characters(product.getBadge().toCharArray(), 0, product.getBadge().length());
				handler.endElement("", "", "currency");

				// Add <price> element
				String priceStr = String.valueOf(product.getPrice());
				handler.startElement("", "", "price", null);
				handler.characters(priceStr.toCharArray(), 0, priceStr.length());
				handler.endElement("", "", "price");

				// Add <stock> element
				String stockStr = String.valueOf(product.getStock());
				handler.startElement("", "", "stock", null);
				handler.characters(stockStr.toCharArray(), 0, stockStr.length());
				handler.endElement("", "", "stock");

				// Add <color> element
				handler.startElement("", "", "color", null);
				handler.characters(product.getColor().toCharArray(), 0, product.getColor().length());
				handler.endElement("", "", "color");

				// Add <storage> element
				String storageStr = String.valueOf(product.getStorage());
				handler.startElement("", "", "storage", null);
				handler.characters(storageStr.toCharArray(), 0, storageStr.length());
				handler.endElement("", "", "storage");

				// End <product> element
				handler.endElement("", "", "product");
			}

			// End <products> root element
			handler.endElement("", "", "products");

			// End the document
			handler.endDocument();

			// Print the generated XML
			System.out.println(writer.toString());

			generateXml();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void generateXml() {
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			File file = new File("files/productsOutput.xml");
			FileWriter fw = new FileWriter(file);

			PrintWriter pw = new PrintWriter(fw);
			pw.write(writer.toString());
			pw.close();
		} catch (IOException e) {
			System.out.println("Error when creating writter file");
		}
	}
}
