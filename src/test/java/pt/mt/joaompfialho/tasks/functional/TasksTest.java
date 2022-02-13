package pt.mt.joaompfialho.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver applicationAccess() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		// Implicitly Wait with 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void shouldSaveNewTaskWithSuccess() {

		WebDriver driver = applicationAccess();
		
		try {
			// Click "Add Todo" button
			driver.findElement(By.id("addTodo")).click();

			// Fill "Task" field
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");

			// Fill "Due Date"
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");

			// Press "Save" button
			driver.findElement(By.id("saveButton")).click();

			// Validate "Success!" message
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			// Close Browser
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveNewTaskWithPastDueDate() {

		WebDriver driver = applicationAccess();

		try {
			// Click "Add Todo" button
			driver.findElement(By.id("addTodo")).click();

			// Fill "Task" field
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");

			// Fill "Due Date"
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");

			// Press "Save" button
			driver.findElement(By.id("saveButton")).click();

			// Validate "Success!" message
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {	
			// Close Browser
			driver.quit();
		}
	}

	@Test
	public void shouldNotSaveNewTaskWithoutDescription() {

		WebDriver driver = applicationAccess();
		
		try {
			// Click "Add Todo" button
			driver.findElement(By.id("addTodo")).click();

			// Fill "Due Date"
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");

			// Press "Save" button
			driver.findElement(By.id("saveButton")).click();

			// Validate "Success!" message
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// Close Browser
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveNewTaskWithoutDate() {

		WebDriver driver = applicationAccess();
		
		try {
			// Click "Add Todo" button
			driver.findElement(By.id("addTodo")).click();

			// Fill "Task" field
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");

			// Press "Save" button
			driver.findElement(By.id("saveButton")).click();

			// Validate "Success!" message
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			// Close Browser
			driver.quit();
		}
	}
	
}
