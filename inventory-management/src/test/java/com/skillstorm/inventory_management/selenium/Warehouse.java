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
    
    @FindBy(xpath = "//div[contains(@class, 'side-bar-items') and text()='Warehouses']")
    private WebElement warehouseTab;

    @FindBy(className = "MuiBox-root")
    private WebElement warehouseTable;

    @FindBy(className = "warehouse-upload-button")
    private WebElement addWarehouseButton;

    @FindBy(className = "warehouse-form")
    private WebElement form;

    @FindBy(className = "warehouse-form-button")
    private WebElement formSubmitButton;

    @FindBy(xpath = "//div[contains(@class,'home-title') and text() = 'HOME']")
    private WebElement homeTitle;

    @FindBy(xpath = "//div[@id='tableTitle' and text()='Warehouses']")
    private WebElement tableTitle;

    @FindBy(xpath = "//input[contains(@class, 'warehouse-form-input') and @placeholder='Name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[contains(@class, 'warehouse-form-input') and @placeholder='Address']")
    private WebElement addressInput;

    @FindBy(xpath = "//button[contains(@class, 'warehouse-form-button')]")
    private WebElement submitWarehouseButton;

    @FindBy(xpath = "//p[contains(@class, 'MuiTablePagination-displayedRows css-1chpzqh')]")
    private WebElement rowCount;

    @FindBy(xpath ="//span[contains(@class, 'warehouse-form-title') and text()='Edit Warehouse ']")
    private WebElement editFormTitle;

    /* 
    @FindBy(xpath="//button[@aria-label='Delete']")
    private List<WebElement> deleteButton;
    */
    public Warehouse(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickWarehouseTab() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouseTab.click();
    }

    public void addWarehouseButtonClick() {
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

    public void addNameInput(String name){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nameInput.sendKeys(name);

    }

    public void addAddressInput(String address){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addressInput.sendKeys(address);
    }

    public void clickSubmitWarehouseButton(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        submitWarehouseButton.submit();
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

    public boolean hasWarehouse(String name, String address) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (driver.getPageSource().contains(name) && driver.getPageSource().contains(address));
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
        return (driver.getPageSource().contains("East Warehouse") && driver.getPageSource().contains("1111 Main st"));
    }
    public String getHomeTitle(){
        if(homeTitle != null)
        return homeTitle.getText();
        return null;
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

    public void clickDeleteButton(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //@FindBy(xpath="//button[@aria-label='Delete']")
        List<WebElement> deleteButton = driver.findElements(By.xpath("//button[@aria-label='Delete']"));
        //first click selects the row
        deleteButton.get(1).click();
        //second click deletes the selected row
        deleteButton.get(1).click();
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


    public boolean isEditFormTitle(){

        if (editFormTitle != null)
            return true;
        else 
            return false;
}
}
