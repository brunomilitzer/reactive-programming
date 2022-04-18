package com.brunomilitzer.reactive.sec11;

import com.brunomilitzer.reactive.sec11.assignment.SlackMember;
import com.brunomilitzer.reactive.sec11.assignment.SlackRoom;
import com.brunomilitzer.reactive.util.Util;

public class Lecture08SlackDemo {

    public static void main(String[] args) {

        SlackRoom slackRoom = new SlackRoom("reactor");

        SlackMember bob = new SlackMember("bob");
        SlackMember jack = new SlackMember("jack");
        SlackMember mike = new SlackMember("mike");

        slackRoom.joinRoom(bob);
        slackRoom.joinRoom(jack);

        bob.says("Hi all...");

        Util.sleepSeconds(4);

        jack.says("Hey!");
        bob.says("I simply wanted to say hi...");

        Util.sleepSeconds(4);

        slackRoom.joinRoom(mike);
        mike.says("Hey guys.. glad to be here...");
    }
}
