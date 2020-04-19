package pt.mt.joaompfialho.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void salvarTarefaComSucesso() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			
			// escrever a descrição
			driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Via Selenium");
			
			// escrever a data
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("31/05/2020");
			
			// clicar em salvar
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Success!", message);
		
		} finally {
			// fechar browser
			driver.quit();			
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {

		WebDriver driver = acessarAplicacao();
		
		try {

			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
						
			// escrever a data
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("31/05/2010");
			
			// clicar em salvar
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Fill the task description", message);
			
		} finally {

			// fechar browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			
			// escrever a descrição
			driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Via Selenium");
			
			// clicar em salvar
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Fill the due date", message);
		
		} finally {
			// fechar browser
			driver.quit();			
		}
	}
	
	@Test
	public void salvarTarefaComDataPassada() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			
			// escrever a descrição
			driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Via Selenium");
			
			// escrever a data
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("31/05/2010");
			
			// clicar em salvar
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		
		} finally {
			// fechar browser
			driver.quit();			
		}
	}
	
}
