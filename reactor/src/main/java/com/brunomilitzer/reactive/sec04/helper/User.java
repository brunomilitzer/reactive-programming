package com.brunomilitzer.reactive.sec04.helper;

import com.brunomilitzer.reactive.util.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private int userId;

    private String username;

    public User(int userId) {
        this.userId = userId;
        this.username = Util.faker().name().username();
    }
}
