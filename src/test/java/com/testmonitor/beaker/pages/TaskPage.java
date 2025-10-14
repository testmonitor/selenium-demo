package com.testmonitor.beaker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TaskPage extends BasePage {
    // Locators
    private final By appHeading = By.xpath("//div[@id='appPage']//h1");
    private final By logoutButton = By.xpath("//button[contains(text(), 'Logout')]");
    private final By taskList = By.id("taskList");
    private final By taskInput = By.id("taskInput");
    private final By addTaskButton = By.id("addTaskBtn");
    private final By taskCount = By.id("taskCount");
    private final By taskItems = By.cssSelector(".task-item");
    private final By prioritySelect = By.id("prioritySelect");
    private final By clearCompletedButton = By.id("clearCompleted");
    private final By activeFilterButton = By.cssSelector("button[data-filter='active']");

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    public String getAppHeadingText() {
        return getText(appHeading);
    }

    public boolean isLogoutButtonVisible() {
        return isElementVisible(logoutButton);
    }

    public LandingPage clickLogout() {
        clickElement(logoutButton);
        return new LandingPage(driver);
    }

    public String getTaskListText() {
        return getText(taskList);
    }

    public void fillTaskInput(String taskText) {
        typeText(taskInput, taskText);
    }

    public void clickAddTask() {
        clickElement(addTaskButton);
    }

    public void addTask(String taskText) {
        fillTaskInput(taskText);
        clickAddTask();
    }

    public void addTaskWithEnter(String taskText) {
        WebElement input = waitForElement(taskInput);
        input.clear();
        input.sendKeys(taskText);
        input.sendKeys(Keys.ENTER);
    }

    public boolean isTaskVisible(String taskText) {
        By taskLocator = By.xpath("//div[@class='task-item' or contains(@class, 'task-item ')][contains(., '" + taskText + "')]");
        return isElementVisible(taskLocator);
    }

    public String getTaskCountText() {
        return getText(taskCount);
    }

    public int getTaskItemsCount() {
        try {
            List<WebElement> elements = driver.findElements(taskItems);
            return elements.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void completeFirstTask() {
        By firstCheckbox = By.cssSelector(".task-item input[type='checkbox']");
        checkCheckbox(firstCheckbox);
    }

    public void completeTaskAtIndex(int index) {
        List<WebElement> tasks = driver.findElements(taskItems);
        WebElement checkbox = tasks.get(index).findElement(By.cssSelector("input[type='checkbox']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isFirstTaskStrikethrough() {
        By firstTaskSpan = By.cssSelector(".task-item span");
        WebElement span = waitForElement(firstTaskSpan);
        String classValue = span.getDomAttribute("class");
        return classValue != null && classValue.contains("line-through");
    }

    public void deleteFirstTask() {
        By deleteButton = By.xpath("//button[contains(text(), 'Delete')]");
        clickElement(deleteButton);
    }

    public boolean areTaskItemsNotVisible() {
        return isElementNotVisible(taskItems);
    }

    public void selectPriority(String priority) {
        selectDropdownByValue(prioritySelect, priority);
    }

    public void addTaskWithPriority(String taskText, String priority) {
        fillTaskInput(taskText);
        selectPriority(priority);
        clickAddTask();
    }

    public void clickActiveFilter() {
        clickElement(activeFilterButton);
    }

    public void clickClearCompleted() {
        clickElement(clearCompletedButton);
    }

    public boolean isTaskItemNotVisible() {
        return isElementNotVisible(taskItems);
    }
}
