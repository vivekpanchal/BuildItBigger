package com.vivek.panchal.javajoker;

import java.util.Random;

public class Joker {

    /*
   Jokes copied and pasted from https://top-funny-jokes.com/
    */

    private static final String[] jokesList = new String[]
            {"Where did Lucy go after the explosion? Everywhere.",
                    "What´s the stupidest animal in the jungle? The polar bear.",
                    "My grandfather had the heart of lion and a lifetime ban from the New your city zoo.",
                    "Two mice chewing on a film roll. One of them says. I think the book was better.",
                    "What kind of bagel can fly? A plain bagel."
            };

    public String tellJoke() {
        return jokesList[new Random().nextInt(jokesList.length)];
    }

}
