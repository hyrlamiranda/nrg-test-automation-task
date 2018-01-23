package com.outfittery.tests;

import com.outfittery.base.BaseTest;
import com.outfittery.pages.HomePage;
import org.junit.Test;


public class RegressionTests extends BaseTest{

    private HomePage homePage;

    @Test
    public void loginButtonShouldBeAvailable(){
        homePage = new HomePage(driver);
        homePage.loginButtonShouldBeAvailable();
    }

    @Test
    public void loginButtonIsDisplayed(){
        homePage = new HomePage(driver);
        homePage.LoginButtonIsDisplayed();

    }

    @Test
    public void userNameTextFieldIsDisplayed(){
        homePage = new HomePage(driver);
        homePage.userNameTextFieldIsDisplayed();

    }

    @Test
    public void passwordTextFieldIsDisplayed(){
        homePage = new HomePage(driver);
        homePage.passwordTextFieldIsDisplayed();

    }

    @Test
    public void successLoginTest(){
        homePage = new HomePage(driver);
        homePage.successLoginTest();

    }

    @Test
    public void loginFailedMessageIsDisplayed(){
        homePage = new HomePage(driver);
        homePage.LoginFailedMessageIsDisplayed();
    }

    @Test
    public void changePhoneNumberTest(){
        homePage = new HomePage(driver);
        homePage.changePhoneNumber();
    }

    @Test
    public void changeNameTest(){
        homePage = new HomePage(driver);
        homePage.ChangeName();
    }

    @Test
    public void changeAddressTest(){
        homePage = new HomePage(driver);
        homePage.changeAddress();
    }
}