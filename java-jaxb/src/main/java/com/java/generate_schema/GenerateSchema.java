package com.java.generate_schema;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.java.domain_annotated.PurchaseOrder;

public class GenerateSchema {
    public static void main() {
        try {
            JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

            context.generateSchema(new SchemaOutputResolver() {

                @Override
                public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                    File outputFile = new File("src/main/res/generated/purchaseOrder.xsd");
                    System.out.println("Generating file: " + outputFile + " for namespace: " + namespaceUri);
                    return new StreamResult(outputFile);
                }
                
            });
        }catch (JAXBException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
