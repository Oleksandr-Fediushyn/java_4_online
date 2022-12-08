
package ua.com.alevel;

import ua.com.alevel.test.Congratulation;

import org.apache.commons.lang3.StringUtils;

class Greetings {
    public static void main(String[] args) {
        Congratulation msg = new Congratulation();
        msg.showCongratulation();
        String phrase = "hello, DEAR FRIENDS !!!";
        String phrase_new = StringUtils.capitalize(phrase);
        System.out.println(phrase);
        System.out.println(phrase_new);
        String phrase_new2 = StringUtils.replaceOnce(phrase_new, "!!", "");
        System.out.println(phrase_new2);
    }
}