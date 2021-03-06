package pt.mt.joaompfialho.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.67:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.1.67:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void salvarTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			
			// escrever a descri??o
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
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

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
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			
			// escrever a descri??o
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
	public void salvarTarefaComDataPassada() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			
			// escrever a descri??o
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
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			// Inserir tasks de teste
			
			// clicar em Add Todo
			driver.findElement(By.xpath("//a[@id='addTodo']")).click();
			// escrever a descri??o
			driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Via Selenium");
			// escrever a data
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("31/05/2030");
			// clicar em salvar
			driver.findElement(By.xpath("//input[@id='saveButton']")).click();
			// validar mensagem de sucesso
			String message = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Success!", message);
		
			// Remover task de teste criada anteriormente
			
			driver.findElement(By.xpath("//*[@href='/tasks/delete/1']")).click();
			// validar mensagem de sucesso
			message = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals("Success!", message);
			
		} finally {
			// fechar browser
			driver.quit();			
		}
	}
	
}
