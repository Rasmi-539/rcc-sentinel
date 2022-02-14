package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BasePage;
import pages.LoginPage;
import pages.SignUpTenantPage;
import utility.TestUtil;

public class CreateNewTenantTest extends BasePage {
	
	LoginPage createTenant;
	SignUpTenantPage doNewSignUp;
	
	String sheetName = "new_Tenant";
	
	@DataProvider
	public Object[][] getNewTenantData(){
		
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;	
	}
	
	@Test(dataProvider = "getNewTenantData")
	public void doCreateNewTenant(String tenantCode,String tenantName,String adminEmail,String adminPassword,String repeatPassword) {
		
		createTenant = new LoginPage();
		doNewSignUp = createTenant.createNewTenant();
		doNewSignUp.doSignUp(tenantCode, tenantName, adminEmail, adminPassword, repeatPassword);
	}
	
	

}
