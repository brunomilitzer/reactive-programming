package com.brunomilitzer.reactive.sec01;

import com.brunomilitzer.reactive.assignment.sec01.FileService;
import com.brunomilitzer.reactive.util.Util;

public class Lecture09Assignment {

    public static void main(String[] args) {

        FileService.read("file03.txt").subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());

        /*FileService.write("file03.txt", "This is file3").subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());*/

        FileService.delete("file03.txt").subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());
    }
}
