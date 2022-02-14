package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import utility.TestUtil;

public class SignUpTenantPage extends BasePage {

	@FindBy(name = "tenancyName")
	public WebElement tenantCode;

	@FindBy(name = "Name")
	public WebElement tenantName;

	@FindBy(name = "adminEmailAddress")
	public WebElement adminEmail;

	@FindBy(name = "Password")
	public WebElement adminPassword;

	@FindBy(name = "PasswordRepeat")
	public WebElement repeatPassword;
	
	@FindBy(xpath= "//button[@type='submit']")
	public WebElement submitBtn;

	public SignUpTenantPage() {

		PageFactory.initElements(driver, this);
	}

	public SignUpTenantPage doSignUp(String tenCode, String tenName, String admnEmail, String admnPaswd, String pswdRept) {
		tenantCode.sendKeys(tenCode);
		TestUtil.sleep(3);
		tenantCode.sendKeys(tenName);
		TestUtil.sleep(3);
		adminEmail.sendKeys(admnEmail);
		TestUtil.sleep(3);
		adminPassword.sendKeys(admnPaswd);
		TestUtil.sleep(3);
		repeatPassword.sendKeys(pswdRept);
		TestUtil.sleep(3);
		TestUtil.clickOnElement(submitBtn, "submit button");
		TestUtil.sleep(3);

		return new SignUpTenantPage();
	}

}
