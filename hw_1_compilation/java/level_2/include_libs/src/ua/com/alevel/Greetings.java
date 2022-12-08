
package ua.com.alevel;

import ua.com.alevel.test.Congratulation;

import org.apache.commons.lang3.StringUtils;

class Greetings {
    public static void main(String[] args) {
        Congratulation msg = new Congratulation();
        msg.showCongratulation();
        String phrase = "Hello, dear friends !!!";
        String phrase_new = StringUtils.capitalize(phrase);
        System.out.println(phrase_new);
        System.out.println(StringUtils.capitalize(phrase_new));
    }
}