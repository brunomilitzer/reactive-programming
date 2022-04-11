package com.brunomilitzer.reactive.sec04;

import com.brunomilitzer.reactive.sec04.helper.OrderService;
import com.brunomilitzer.reactive.sec04.helper.UserService;
import com.brunomilitzer.reactive.util.Util;

public class Lecture13ConcatMap {

    public static void main(String[] args) {

        UserService.getUsers().concatMap(user -> OrderService.getOrders(user.getUserId())) // mono or flux if is a map
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
}
