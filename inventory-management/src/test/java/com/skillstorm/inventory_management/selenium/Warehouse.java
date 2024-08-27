package com.skillstorm.inventory_management.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Warehouse {

    private WebDriver driver;
    private static final String url = "http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com";

    @FindBy(xpath = "//*[contains(text(), 'Warehouses')]")
    private WebElement warehouseTab;

    @FindBy(className = "MuiBox-root")
    private WebElement warehouseTable;

    @FindBy(className = "warehouse-upload-button")
    private WebElement addWarehouseButton;

    @FindBy(className = "warehouse-form")
    private WebElement form;

    @FindBy(className = "warehouse-form-button")
    private WebElement formSubmitButton;

    public Warehouse(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        PageFactory.initElements(driver, this);
    }

    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
        warehouseTab.click();
    }

    public void addWarehouse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addWarehouseButton.click();
    }

    public boolean onForm() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElements(By.className("warehouse-form")).size() > 0;
    }

    public void addInputs() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> formInputs = driver.findElements(By.className("warehouse-form-input"));
        WebElement nameInput = formInputs.get(0);
        WebElement addressInput = formInputs.get(1);

        nameInput.sendKeys("Walgreens");
        addressInput.sendKeys("765 Jones Street");
    }

    public void submitForm() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formSubmitButton.click();
    }

    public boolean hasWarehouse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (driver.getPageSource().contains("Walgreens") && driver.getPageSource().contains("765 Jones Street"));
    }

    public void clickEdit() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<WebElement> rows = driver.findElements(By.cssSelector("tr"));

        for(int i = 0; i < rows.size(); i++) {
            WebElement header = rows.get(i).findElement(By.cssSelector("th"));
            if(header.getText().toString().equals("Walgreens")) {
                WebElement testRow = rows.get(i);
                List<WebElement> tdRows = testRow.findElements(By.cssSelector("td"));
                WebElement editButton = tdRows.get(2).findElement(By.cssSelector("button"));
                WebElement editIcon = editButton.findElement(By.cssSelector("svg"));
                editIcon.click();
                break;
            }
        }
    }

    public void editInputs() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> formInputs = driver.findElements(By.className("warehouse-form-input"));
        WebElement nameInput = formInputs.get(0);
        WebElement addressInput = formInputs.get(1);

        nameInput.sendKeys("New Walgreens");
        addressInput.sendKeys("567 Jones Street");
    }

    public boolean hasEditedWarehouse() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (driver.getPageSource().contains("New Walgreens") && driver.getPageSource().contains("567 Jones Street"));
    }

    public void clickDelete() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> rows = driver.findElements(By.cssSelector("tr"));

        for (int i = 0; i < rows.size(); i++) {
            WebElement header = rows.get(i).findElement(By.cssSelector("th"));
            if (header.getText().toString().equals("New Walgreens")) {
                WebElement testRow = rows.get(i);
                List<WebElement> tdRows = testRow.findElements(By.cssSelector("td"));
                WebElement deleteButton = tdRows.get(3).findElement(By.cssSelector("button"));
                deleteButton.click();
                break;
            }
        }
    }
}
