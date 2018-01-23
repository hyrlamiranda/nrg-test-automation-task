package com.outfittery.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
    }

    private void goToLoginPage(){
        WebElement loginButton = driver.findElement(By.cssSelector(".login.open-login"));
        loginButton.click();
    }

    private void login(){
        goToLoginPage();
        WebElement userNameTextField = driver.findElement(By.id("username"));
        userNameTextField.clear();
        userNameTextField.sendKeys("hyrla.miranda@test.com");
        WebElement passwordTextField = driver.findElement(By.id("password"));
        passwordTextField.clear();
        passwordTextField.sendKeys("hyrla2018");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }

    public void loginButtonShouldBeAvailable() {
        WebElement loginButton = driver.findElement(By.cssSelector(".login.open-login"));
        assert loginButton.isDisplayed();
    }

    public void userNameTextFieldIsDisplayed() {
        goToLoginPage();
        WebElement userNameTextField = driver.findElement(By.id("username"));
        assert userNameTextField.isDisplayed();
    }

    public void passwordTextFieldIsDisplayed() {
        goToLoginPage();
        WebElement passwordTextField = driver.findElement(By.id("password"));
        assert passwordTextField.isDisplayed();
    }

    public void LoginButtonIsDisplayed() {
        goToLoginPage();
        WebElement loginButton = driver.findElement(By.id("submit"));
        assert loginButton.isDisplayed();
    }

    public void successLoginTest() {
        login();
        assert "https://www.outfittery.com/account/dashboard".equalsIgnoreCase(driver.getCurrentUrl());
    }

    public void LoginFailedMessageIsDisplayed() {
        goToLoginPage();
        WebElement userNameTextField = driver.findElement(By.id("username"));
        userNameTextField.sendKeys("hyrla.miranda@test.com");
        WebElement passwordTextField = driver.findElement(By.id("password"));
        passwordTextField.sendKeys("1234qwerblablabla");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        assert driver.getCurrentUrl().endsWith("login_error=1");
    }

    private WebElement findMenuItemByText(String text) {
        List<WebElement> elements = driver.findElements(By.className("header__menu_item"));


        WebElement foundElement = null;
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(text)) {
                foundElement = element;
                break;
            }
        }
        return foundElement;
    }

    public void changePhoneNumber() {
        login();
        findMenuItemByText("MY SETTINGS").click();
        driver.findElement(By.className("editProfileLink")).click();
        WebElement phoneNumberTextField = driver.findElement(By.name("phoneNumber"));

        Random rnd = new Random();
        int number = 100000 + rnd.nextInt(900000);

        String newPhoneNumber = String.valueOf(number);

        phoneNumberTextField.clear();
        phoneNumberTextField.sendKeys(newPhoneNumber);

        try {
            Thread.sleep(5000); //ininet trouble
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.name("submit")).click();
        String phoneNumberText = driver.findElement(By.className("user-content")).findElements(By.className("ng-binding")).get(3).getText();
        assert phoneNumberText.contains(newPhoneNumber);
    }

    private String GetRandomName(){

        List<String> names = new ArrayList<String>();
        names.add("Joao");
        names.add("Pedro");
        names.add("Maria");
        names.add("Ivan");
        names.add("Igor");
        names.add("Caio");
        names.add("Carla");

        int index = new Random().nextInt(names.size());
        return names.get(index);
    }


    public void ChangeName() {
        login();

        findMenuItemByText("MY SETTINGS").click();
        driver.findElement(By.className("editProfileLink")).click();
        WebElement nameTextField = driver.findElements(By.name("name")).get(1);
        String newName = GetRandomName();

        nameTextField.clear();
        nameTextField.sendKeys(newName);

        try {
            Thread.sleep(5000); //ininet trouble
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.name("userForm")).findElement(By.name("submit")).click();

        String nameText = driver.findElement(By.className("user-content")).findElements(By.className("ng-binding")).get(0).getText();
        assert nameText.contains(newName);
    }

    public void changeAddress() {
        login();
        findMenuItemByText("MY SETTINGS").click();
        //WebElement manageAddressButon = driver.findElement(By.className("manage-addresses")).findElement(By.className("ng-scope"));
        WebElement manageAddressButon = driver.findElement(By.linkText("Manage Addresses"));

        try {
            Thread.sleep(3000); //for scroll to the button
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            manageAddressButon.click();
        } catch (Exception e){
            e.printStackTrace();
        }

        driver.findElement(By.className("edit-delete")).click();

        WebElement houseNumberTextField = driver.findElement(By.name("number"));
        WebElement zipNumberTextField = driver.findElement(By.name("zip"));
        WebElement cityNumberTextField = driver.findElement(By.name("city"));
        WebElement streetNumberTextField = driver.findElement(By.name("street"));

        Random rnd = new Random();
        int number = 10 + rnd.nextInt(180);
        String zipCode = String.valueOf(number + 150);
        String houseNUmberStr = String.valueOf(number);

        houseNumberTextField.clear();
        houseNumberTextField.sendKeys(houseNUmberStr);
        zipNumberTextField.clear();
        zipNumberTextField.sendKeys(zipCode);
        cityNumberTextField.clear();
        cityNumberTextField.sendKeys("Belo Horizonte");
        streetNumberTextField.clear();
        streetNumberTextField.sendKeys("Vasco da Gama");


        try {
            Thread.sleep(5000); //scroll the screen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.name("submitBtn")).click();

        String StreetAndHouseNumberText = driver.findElement(By.className("street")).getText();
        String ZIPandCityText = driver.findElement(By.className("zip")).getText();

        assert StreetAndHouseNumberText.contains(houseNUmberStr) && StreetAndHouseNumberText.contains("Vasco da Gama");;
        assert ZIPandCityText.contains("Belo Horizonte") && ZIPandCityText.contains(zipCode);
    }
}