package com.skillstorm.inventory_management.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// comment
public class Product {
    
    private WebDriver driver;
    private static final String url = "http://team-6-frontend.s3-website-us-east-1.amazonaws.com/";

    @FindBy(xpath = "//*[contains(text(), 'Products')]")
    private WebElement productTab;
}
