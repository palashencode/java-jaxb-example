
package com.java.app;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.java.app.domain.Address;
import com.java.app.domain.Customer;
import com.java.app.domain.Item;
import com.java.app.domain.Loyalty;
import com.java.app.domain.ObjectFactory;
import com.java.app.domain.PurchaseOrder;
import com.java.app.domain.PurchaseOrderService;
import com.java.app.domain.PurchaseOrders;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class PurchaseOrderClient {
    
    public static void main(){
        PurchaseOrderService service = new PurchaseOrderService();
        PurchaseOrders port = service.getPurchaseOrderPort();

        
        try {
            long id;
            id = port.addOrder(createPurchaseOrder());
            System.out.println("Order created with id: " + id);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        

        for (PurchaseOrder purchaseOrder : port.getOrders()) {
            System.out.println("Found order: " + purchaseOrder.getId());
            System.out.println("  Number of items: " + purchaseOrder.getItems().getItem().size());
            for (Item item : purchaseOrder.getItems().getItem()) {
                System.out.println("    Item: " + item.getProductName() + ", quantity: " + item.getQuantity());
            }
            System.out.println("  Customer: " + purchaseOrder.getCustomer().getName());
        }
    }

    private static PurchaseOrder createPurchaseOrder() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();

        PurchaseOrder purchaseOrder = objectFactory.createPurchaseOrder();

        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar calendar =
                datatypeFactory.newXMLGregorianCalendar(new GregorianCalendar());

        purchaseOrder.setOrderDate(calendar);

        Item item1 = objectFactory.createItem();
        item1.setProductName("Ballpoint Pen");
        item1.setQuantity(20);
        item1.setPrice(new BigDecimal("8.95"));
        item1.setComment("Blue ink");

        Item item2 = objectFactory.createItem();
        item2.setProductName("Pencil");
        item2.setQuantity(10);
        item2.setPrice(new BigDecimal("2.95"));

        PurchaseOrder.Items items = objectFactory.createPurchaseOrderItems();
        items.getItem().add(item1);
        items.getItem().add(item2);

        purchaseOrder.setItems(items);

        Customer customer = objectFactory.createCustomer();
        customer.setName("Jack Doe");

        Address address = objectFactory.createAddress();
        address.setStreet("123 Main Street");
        address.setCity("Exampleville");
        address.setPostalCode("12345");
        address.setCountry("USA");

        customer.setShippingAddress(address);
        customer.setBillingAddress(address);
        customer.setLoyalty(Loyalty.SILVER);

        purchaseOrder.setCustomer(customer);

        return purchaseOrder;
    }   

}