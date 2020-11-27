package com.java.simple_marshall_unmarshall;

import com.java.domain.PurchaseOrder;
import com.java.util.Helper;

import java.io.File;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stream.StreamSource;

public class MarshallUnMarshallTester {
    
    public static void main(){
        try {
            marshall();
            // marshallIntoStAXApi();
            // unMarshall();
        } catch (JAXBException 
            // | XMLStreamException 
                                      e) {
            e.printStackTrace();
        }
    }

    private static void marshall() throws JAXBException{

        // Populate the Purchase Order
        PurchaseOrder purchaseOrder = Helper.createPurchaseOrder();

        // Create JAXBContext
        JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

        // Create the Marshaller
        Marshaller marshaller = context.createMarshaller();

        // Configure Marshaller to make output XML formattable
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Create a JAXB Element wrapper for the root element
        QName rootElementName = new QName(null,"purchaseOrder");
        JAXBElement<PurchaseOrder> rootElement = 
                new JAXBElement<PurchaseOrder>(rootElementName, PurchaseOrder.class, purchaseOrder);

        // Marshall the o/p
        marshaller.marshal(rootElement, System.out);
    }

    private static void marshallIntoStAXApi() throws JAXBException, XMLStreamException{

        // Populate the Purchase Order
        PurchaseOrder purchaseOrder = Helper.createPurchaseOrder();

        // Create JAXBContext
        JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

        // Create the Marshaller
        Marshaller marshaller = context.createMarshaller();

        // Configure Marshaller to make output XML formattable
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        // Create a JAXB Element wrapper for the root element
        QName rootElementName = new QName(null,"purchaseOrder");
        JAXBElement<PurchaseOrder> rootElement = 
                new JAXBElement<PurchaseOrder>(rootElementName, PurchaseOrder.class, purchaseOrder);

        // Use the StAX API to create an XML document
        XMLStreamWriter writer = 
            XMLOutputFactory.newInstance().createXMLStreamWriter(System.out, "UTF-8");

        // writer.writeStartDocument("UTF-8", "1.0");
        // writer.writeStartDocument("orders");

        // Marshall PurchaseOrder into the StaX stream
        marshaller.marshal(rootElement, writer);
        writer.close();

    }

    private static void unMarshall() throws JAXBException{
        // Create a JAXB context
        JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

        // Create an unmarshaller
        Unmarshaller unmarshaller = context.createUnmarshaller();

        JAXBElement<PurchaseOrder> rootElement = unmarshaller.unmarshal(
                new StreamSource(new File("src/main/res/sample_xsd/purchaseOrder.xml")), PurchaseOrder.class);

        PurchaseOrder purchaseOrder = rootElement.getValue();        

    }


}
