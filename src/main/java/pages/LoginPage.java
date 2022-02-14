package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage {

	@FindBy(name = "userNameOrEmailAddress")
	public WebElement userName;

	@FindBy(name = "password")
	public WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text(),'Create new tenant')]")
	public WebElement createTenant;

	@FindBy(xpath = "//button[contains(text(),' Free ')]")
	public WebElement clickStdEdtn;

	public LoginPage() {

		PageFactory.initElements(driver, this);

	}

	public HomePage doLogin(String un, String pw) throws Exception {

		userName.sendKeys(un);
		password.sendKeys(pw);
		loginBtn.click();
		System.out.println("Login");

		return new HomePage();
	}

	public SignUpTenantPage createNewTenant() {

		createTenant.click();
		clickStdEdtn.click();
		return new SignUpTenantPage();
	}

}
