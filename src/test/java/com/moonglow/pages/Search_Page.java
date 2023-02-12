package com.moonglow.pages;

import com.moonglow.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search_Page {

    public Search_Page(){
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "(//div[@class='wd-add-btn wd-add-btn-replace'])[1]")
    public WebElement firstItem_AfterSearch;

}
