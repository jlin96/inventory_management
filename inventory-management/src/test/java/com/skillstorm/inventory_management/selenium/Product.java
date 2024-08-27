package com.skillstorm.inventory_management.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Contains selenium code for test cases
public class Product {
    
    private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'side-bar-items') and text()='Products']")
    private WebElement productTab;

    @FindBy(xpath = "//div[@id='tableTitle' and text()='Products']")
    private WebElement tableTitle;

    @FindBy(xpath = "//div[contains(@class,'home-title') and text() = 'HOME']")
    private WebElement homeTitle;

    @FindBy(xpath = "//button[contains(@class,'product-upload-button')]")
    private WebElement addProductButton;

    @FindBy(xpath = "//form[contains(@class, 'product-form')]")
    private WebElement productForm;

    @FindBy(xpath ="//input[@placeholder='Name']")
    private WebElement nameInput;

    @FindBy(xpath ="//input[@placeholder='Description']")
    private WebElement descriptionInput;

    @FindBy(xpath ="//input[@placeholder='Stock']")
    private WebElement stockInput;

    @FindBy(xpath ="//input[@placeholder='Warehouse']")
    private WebElement warehouseInput;

    @FindBy(xpath ="//button[contains(@class,'product-form-button')]")
    private WebElement submitProductButton;

    @FindBy(xpath ="//table[contains(@class, \"MuiTable-root css-1q7lp8d\")]//tbody//tr")
    private List<WebElement> rows;

    @FindBy(xpath = "//p[contains(@class, 'MuiTablePagination-displayedRows css-1chpzqh')]")
    private WebElement rowCount;

    @FindBy(xpath="//button[@aria-label='Delete']")
    private List<WebElement> deleteButton;
     
    public Product(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String verifyTitle(){
        return this.driver.getTitle();
    }

    public void clickProductTab(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productTab.click();
    }
    public void clickAddProductButton(){
        
        addProductButton.click();
    }


    public void quitDriver(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public String getTableTitle(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (tableTitle != null)
            return tableTitle.getText();
        return null;
    }

    public String getHomeTitle(){
        if(homeTitle != null)
        return homeTitle.getText();
        return null;
    }

    public void fillOutForm(){
        
    }

    public boolean isProductForm(){

        if(productForm != null)
            return true;
        return false;
    }

    public void fillName(){
        nameInput.sendKeys("HP Laptop");
    }

    public void fillDescription(){
        descriptionInput.sendKeys("core i7 intel processor");
    }

    public void fillStock(){
        stockInput.sendKeys("100");
    }

    public void fillWarehouse(){
        warehouseInput.sendKeys("1");
    }

    public void submitForm(){
        submitProductButton.submit();
    }

    public int getRows(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String rowValue = rowCount.getText().substring(6).trim();
        return Integer.parseInt(rowValue);
    }

    public void clickDeleteButton(){
        //first click selects the row
        deleteButton.get(0).click();
        //second click deletes the selected row
        deleteButton.get(0).click();
    }
}
