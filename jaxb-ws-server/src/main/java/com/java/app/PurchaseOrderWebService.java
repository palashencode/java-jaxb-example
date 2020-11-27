
package com.java.app;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.java.app.domain.PurchaseOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@WebService(name = "PurchaseOrders",
        serviceName = "PurchaseOrderService",
        portName = "PurchaseOrderPort",
        targetNamespace = "http://www.example.com/java-jaxb")
public class PurchaseOrderWebService {

    private final AtomicLong ID_GENERATOR = new AtomicLong();

    private final Map<Long, PurchaseOrder> purchaseOrders = new HashMap<>();

    @WebMethod
    @WebResult(name = "orders")
    public List<PurchaseOrder> getOrders() {
        System.out.println(getTime()+"call to getOrders()");
        return new ArrayList<>(purchaseOrders.values());
    }

    @WebMethod
    @WebResult(name = "order")
    public PurchaseOrder getOrder(@WebParam(name = "id") long id) {
        System.out.println(getTime()+"call to getOrders("+id+")");

        if (purchaseOrders.containsKey(id)) {
            return purchaseOrders.get(id);
        } else {
            throw new IllegalArgumentException("Order not found: " + id);
        }
    }

    @WebMethod
    @WebResult(name = "id")
    public long addOrder(@WebParam(name = "order") PurchaseOrder purchaseOrder) {
        System.out.println(getTime()+"call to addOrder() for "+purchaseOrder.getCustomer().getName());

        long id = ID_GENERATOR.incrementAndGet();
        purchaseOrder.setId(id);
        purchaseOrders.put(id, purchaseOrder);
        return id;
    }

    @WebMethod
    public void deleteOrder(@WebParam(name = "id") long id) {
        System.out.println(getTime()+"call to deleteOrder("+id+")");

        purchaseOrders.remove(id);
    }

    public static void main() {
        Endpoint.publish("http://localhost:8080/ws", new PurchaseOrderWebService());
        System.out.println(getTime()+"Running at \"http://localhost:8080/ws\"...");
    }

    private static String getTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter)+": ";
    }
}
