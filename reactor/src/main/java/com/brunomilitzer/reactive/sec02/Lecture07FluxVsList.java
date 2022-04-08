package com.brunomilitzer.reactive.sec02;

import com.brunomilitzer.reactive.sec02.helper.NameGenerator;
import com.brunomilitzer.reactive.util.Util;

import java.util.List;

public class Lecture07FluxVsList {

    public static void main(String[] args) {

        //List<String> names = NameGenerator.getNames(5);

        //System.out.println(names);

        NameGenerator.getNames(5).subscribe(Util.onNext());
    }
}
