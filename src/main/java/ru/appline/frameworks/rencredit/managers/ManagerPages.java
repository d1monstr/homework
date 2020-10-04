package ru.appline.frameworks.sberbank.managers;

import ru.appline.frameworks.sberbank.pages.CompleteHomePage;
import ru.appline.frameworks.sberbank.pages.StartPage;

public class ManagerPages {

    private static ManagerPages managerPages;


    private static StartPage startPage;

    private static CompleteHomePage completeHomePage;

    private ManagerPages() {
    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public CompleteHomePage getCompleteHomePage() {
        if (completeHomePage == null) {
            completeHomePage = new CompleteHomePage();
        }
        return completeHomePage;
    }

    public static void deletePages(){
        startPage = null;
        completeHomePage = null;
    }

//
//    public CartPage getCartPage() {
//        if (cartPage == null) {
//            cartPage = new CartPage();
//        }
//        return cartPage;
//    }
//
//    public ProductPage getProductPage() {
//        if (productPage == null) {
//            productPage = new ProductPage();
//        }
//        return productPage;
//    }

}
