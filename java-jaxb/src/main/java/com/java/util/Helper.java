package com.java.util;

import java.math.BigDecimal;
import java.util.Arrays;

import com.java.domain.Address;
import com.java.domain.Customer;
import com.java.domain.Item;
import com.java.domain.Loyalty;
import com.java.domain.PurchaseOrder;

public class Helper {
    public static PurchaseOrder createPurchaseOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        Item item1 = new Item();
        item1.setProductName("Ballpoint Pen");
        item1.setQuantity(20);
        item1.setPrice(new BigDecimal("8.95"));
        item1.setComment("Blue ink");

        Item item2 = new Item();
        item2.setProductName("Pencil");
        item2.setQuantity(10);
        item2.setPrice(new BigDecimal("2.95"));
        // item2.setComment("its Pencil");

        purchaseOrder.setItems(Arrays.asList(item1, item2));

        Customer customer = new Customer();
        customer.setName("John Doe");

        Address address = new Address();
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
