package com.brunomilitzer.reactive.sec12;

import com.brunomilitzer.reactive.sec12.helper.BookService;
import com.brunomilitzer.reactive.sec12.helper.UserService;
import com.brunomilitzer.reactive.util.Util;
import reactor.util.context.Context;

public class Lecture02CtxRateLimiterDemo {

    public static void main(String[] args) {

        BookService.getBook()
                .repeat(3)
                .contextWrite(UserService.userCategoryContext())
                .contextWrite(Context.of("user", "jack"))
                .subscribe(Util.subscriber());
    }
}
