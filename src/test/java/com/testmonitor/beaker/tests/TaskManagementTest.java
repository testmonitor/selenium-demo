package com.testmonitor.beaker.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskManagementTest extends BaseTest {

    @BeforeMethod
    public void loginAndNavigateToTaskPage() {
        landingPage.navigateToLandingPage();
        loginPage = landingPage.clickGetStarted();
        taskPage = loginPage.loginWithCredentials("test@example.com", "password123");
    }

    @Test(description = "Displays empty task list initially")
    public void testEmptyTaskList() {
        String taskListText = taskPage.getTaskListText();
        Assert.assertTrue(taskListText.contains("No tasks yet"), "Task list should show 'No tasks yet'");
    }

    @Test(description = "Adds a new task")
    public void testAddNewTask() {
        taskPage.addTask("Write test cases");
        Assert.assertTrue(taskPage.isTaskVisible("Write test cases"),
            "Task 'Write test cases' should be visible");
    }

    @Test(description = "Shows correct task count - EXPECTED TO FAIL due to off-by-one bug")
    public void testTaskCount() {
        taskPage.addTask("First task");
        String count = taskPage.getTaskCountText();
        Assert.assertEquals(count, "1", "Task count should be 1");
    }

    @Test(description = "Adds task with Enter key - EXPECTED TO FAIL (Enter key doesn't work)")
    public void testAddTaskWithEnter() {
        taskPage.addTaskWithEnter("Test with Enter key");
        Assert.assertTrue(taskPage.isTaskVisible("Test with Enter key"),
            "Task should be visible after pressing Enter");
    }

    @Test(description = "Completes a task")
    public void testCompleteTask() {
        taskPage.addTask("Task to complete");
        taskPage.completeFirstTask();
        Assert.assertTrue(taskPage.isFirstTaskStrikethrough(),
            "Completed task should have strikethrough style");
    }

    @Test(description = "Deletes a task - EXPECTED TO FAIL (delete doesn't remove from array)")
    public void testDeleteTask() {
        taskPage.addTask("Task to delete");
        taskPage.deleteFirstTask();
        Assert.assertTrue(taskPage.areTaskItemsNotVisible(),
            "Task should not be visible after deletion");
    }

    @Test(description = "Filters active tasks correctly - EXPECTED TO FAIL (filter logic reversed)")
    public void testFilterActiveTasks() {
        taskPage.addTask("Active task");
        taskPage.addTask("Completed task");

        // Complete the second task
        taskPage.completeTaskAtIndex(1);

        // Filter to show only active tasks
        taskPage.clickActiveFilter();
        int visibleTasks = taskPage.getTaskItemsCount();
        Assert.assertEquals(visibleTasks, 1, "Should show only 1 active task");
        Assert.assertTrue(taskPage.isTaskVisible("Active task"),
            "Should show 'Active task'");
    }

    @Test(description = "Adds tasks with different priorities")
    public void testAddTaskWithPriority() {
        taskPage.addTaskWithPriority("High priority task", "high");
        Assert.assertTrue(taskPage.isTaskVisible("high"),
            "Task should show 'high' priority");
    }

    @Test(description = "Clears completed tasks - EXPECTED TO FAIL (wrong logic)")
    public void testClearCompletedTasks() {
        taskPage.addTask("Task 1");
        taskPage.addTask("Task 2");

        // Complete first task
        taskPage.completeTaskAtIndex(0);

        taskPage.clickClearCompleted();
        int remainingTasks = taskPage.getTaskItemsCount();
        Assert.assertEquals(remainingTasks, 1, "Should have 1 remaining task");
        Assert.assertTrue(taskPage.isTaskVisible("Task 2"),
            "Should show 'Task 2'");
    }
}
