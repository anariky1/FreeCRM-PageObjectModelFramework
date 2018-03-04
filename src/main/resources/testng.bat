<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="FREE CRM Application Test Automation Test Suite">

    <listeners>
		<listener class-name="com.crm.qa.ExtentReportListener.ExtentReporterNG" />
	</listeners>

	<test name="Free CRM App Test Cases">
		<classes>
			<class name="com.crm.qa.testscases.LoginPageTest"></class>
			<class name="com.crm.qa.testscases.HomePageTest"></class>
            <class name="com.crm.qa.testscases.ContactsPageTest"></class>
		</classes>
	</test>
</suite>