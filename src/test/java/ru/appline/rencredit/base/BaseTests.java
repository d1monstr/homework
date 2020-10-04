package ru.appline.rencredit.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.appline.frameworks.rencredit.managers.InitManager;
import ru.appline.frameworks.rencredit.managers.ManagerPages;

public class BaseTests {

    protected ManagerPages app = ManagerPages.getManagerPages();

    @BeforeClass
    public static void before() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach(){
        InitManager.initUrl();
    }

    @AfterClass
    public static void after() {
        InitManager.quitFramework();
    }
}
