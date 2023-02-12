package com.moonglow.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class BrowserUtils {

    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }

    public static void removeRandomItem(List<WebElement> items){
        Random random = new Random();
        int index = random.nextInt(items.size());
        items.get(index).click();
    }

}

