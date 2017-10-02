package br.com.atlantico.bdd;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.atlantico.pages.RegistrationPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class ResgistrarUsuarioSteps {

	private static WebDriver browser;
	public static RegistrationPage registrationPage;

	@Before("@start")
	public void setup() {

		File file = new File("/Users/eduardofinotti/Documents/chromedriver");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

		browser = new ChromeDriver();
		browser.manage().window().setPosition(new Point(200, 100));

		registrationPage = new RegistrationPage(browser);
	}

	@Dado("^que estou na página de registro$")
	public void que_estou_na_página_de_registro() throws Throwable {

		browser.navigate().to("http://demoqa.com/registration/");
		browser.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		try {
			registrationPage.elementoNomePresente(browser);
		} catch (Exception e) {
			throw new Exception("===> O elemento não foi encontrado. A página não esta correta! <=== " + e);
		}

	}

	@Dado("^somente não preencho os campos referente ao nome do usuário$")
	public void somente_não_preencho_os_campos_referente_ao_nome_do_usuário() throws Throwable {

		try {
			registrationPage.selectHpbby();
			registrationPage.prenchePhone("48991929692");
			registrationPage.preencheUserName("eduardo", "finotti");
			registrationPage.preencheEmail("eduardo", "finotti@hotmail.co");
			registrationPage.preencheSenhaEConfirma("12345678");

		} catch (Exception e) {
			throw new Exception("===> Erro ao preencher campos! <=== " + e);
		}

	}

	@Quando("^clicar no botão Submit$")
	public void clicar_no_botão_Submit() throws Throwable {
		try {
			registrationPage.clickSubmit(browser);

		} catch (Exception e) {
			throw new Exception("===> Erro ao clicar no botão! <=== " + e);
		}

	}

	@Então("^mostrará a mensagem \"(.*?)\"$")
	public void mostrará_a_mensagem(String mensagemEsperada) throws Throwable {
		
		String mensagemAtual = registrationPage.mensagemErro(browser);
		Assert.assertEquals(mensagemAtual, mensagemEsperada);

	}

	@Dado("^preencho todos os campos obrigatórios$")
	public void preencho_todos_os_campos_obrigatórios() throws Throwable {

		registrationPage = new RegistrationPage(browser);

		try {
			registrationPage.prencheFirstName("eduardo");
			registrationPage.prencheLastName("last");
			registrationPage.selectHpbby();
			registrationPage.prenchePhone("48991929692");
			registrationPage.preencheUserName("eduardo", "finotti");
			registrationPage.preencheEmail("eduardo", "finotti@hotmail.co");
			registrationPage.preencheSenhaEConfirma("12345678");

		} catch (Exception e) {
			throw new Exception("===> Erro ao preencher campos! <=== " + e);
		}
		
	}

	@Então("^o formulário é enviado exibindo mensagem \"(.*?)\"$")
	public void o_formulário_é_enviado_exibindo_mensagem(String mensagemEsperada) throws Throwable {

		String mensagemAtual = registrationPage.mensagemFinalSucesso(browser);
		Assert.assertEquals(mensagemAtual, mensagemEsperada);
	}

	@Dado("^preencho o campo username com dado já cadastrado$")
	public void preencho_o_campo_username_com_dado_já_cadastrado() throws Throwable {
		
		try {
			registrationPage.preencheUserName("eduardofinotti");

		} catch (Exception e) {
			throw new Exception("===> Erro ao preencher campos! <=== " + e);
		}
	}

	@Então("^mostrará a mensagem de erro \"(.*?)\"$")
	public void mostrará_a_mensagem_de_erro(String mensagemEsperadaErro) throws Throwable {

		String mensagemAtualErro = registrationPage.mensagemFinalErro(browser);
		Assert.assertEquals(mensagemAtualErro, mensagemEsperadaErro);

	}

	@After("@finish")
	public void tearDown() {
		browser.quit();
	}

}
