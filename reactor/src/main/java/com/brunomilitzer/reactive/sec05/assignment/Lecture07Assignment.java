package com.brunomilitzer.reactive.sec05.assignment;

import com.brunomilitzer.reactive.util.Util;

public class Lecture07Assignment {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        // revenue and inv - observes the order stream
        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream().subscribe(Util.subscriber("inventory"));
        revenueService.revenueStream().subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(60);
    }
}
