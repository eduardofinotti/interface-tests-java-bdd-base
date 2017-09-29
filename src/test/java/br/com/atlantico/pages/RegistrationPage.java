package br.com.atlantico.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.atlantico.util.Utils;
import cucumber.api.PendingException;

public class RegistrationPage extends PageObject {

	@FindBy(id = "name_3_firstname")
	private WebElement campoFirstName;
	
	@FindBy(id = "name_3_lastname")
	private WebElement campoLastName;
	
	@FindBy(xpath = "//*[@id=\"pie_register\"]/li[3]/div/div/input[1]")
	private WebElement campoHobby;

	@FindBy(id = "phone_9")
	private WebElement campoPhone;

	@FindBy(id = "username")
	private WebElement campoUserName;

	@FindBy(id = "email_1")
	private WebElement campoEmail;
	
	@FindBy(id = "password_2")
	private WebElement campoSenha;

	@FindBy(id = "confirm_password_password_2")
	private WebElement campoConfirmaSenha;
	
	@FindBy(id = "signup")
	private WebElement submitButton;
	
	@FindBy(xpath = "//*[@id=\\\"pie_register\\\"]/li[1]/div[1]/div[2]/span")
	private WebElement mensagemErro;
	
	Utils util = new Utils();

	public RegistrationPage(WebDriver browser) {
		super(browser);
	}
	
	public void prencheFirstName(String name) {
		this.campoFirstName.clear();
		this.campoFirstName.sendKeys(name);
	}
	
	public void prencheLastName(String lastName) {
		this.campoLastName.clear();
		this.campoLastName.sendKeys(lastName);
	}

	public void selectHpbby() {
		if (!this.campoHobby.isSelected() ){
			this.campoHobby.click();
		}
	}
	
	public void prenchePhone(String phone) {
		this.campoPhone.clear();
		this.campoPhone.sendKeys(phone);
	}

	public String preencheUserName(String userName, String userName2) {
		
		String complemento = util.geraStringComMomento();		
		String user = userName + complemento + userName2;
		
		this.campoUserName.clear();
		this.campoUserName.sendKeys(user);
		
		return user;
	}
	
	public void preencheUserName(String userName) {
		this.campoUserName.clear();
		this.campoUserName.sendKeys(userName);
	}
	
	public void preencheEmail(String email, String email2) {
		
		String complemento = util.geraStringComMomento();
		
		this.campoEmail.clear();
		this.campoEmail.sendKeys(email + complemento + email2);
	}
	
	public void preencheSenhaEConfirma(String senha) {
		this.campoSenha.clear();
		this.campoSenha.sendKeys(senha);
		
		this.campoConfirmaSenha.clear();
		this.campoConfirmaSenha.sendKeys(senha);
	}

	public void clickSubmit(WebDriver browser) {
		WebElement element = browser.findElement(By.name("pie_submit"));
		JavascriptExecutor executor = (JavascriptExecutor)browser;
		executor.executeScript("arguments[0].click();", element);
		
		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	public String mensagemErro(WebDriver browser) {
		String mensagem = browser.findElement(By.xpath("//*[@id=\"pie_register\"]/li[1]/div[1]/div[2]/span")).getText();
		return mensagem;
	}
	
	public String mensagemFinalSucesso(WebDriver browser) {
		
		String mensagem = browser.findElement(By.xpath("//*[@id=\"post-49\"]/div/p")).getText();
		return mensagem;
	}
	
	public String mensagemFinalErro(WebDriver browser) {

		String mensagem = browser.findElement(By.className("piereg_login_error")).getText();
		return mensagem;
	}
	
	public void elementoNomePresente(WebDriver browser) {
		
		try {
			this.campoFirstName.isDisplayed();	
		} catch (Exception e) {
			throw new PendingException("===> Elemento não está presente! <===");
		}	
		
	}

}
