package base;

import java.lang.reflect.Method;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

public class BaseTest extends BaseClass {

	// Create a test with the method name and browser name
	protected void createTestWithMethodName(Method method, String browserName) {
		String methodName = method.getName();
		test = ExtentReportManager.extent.createTest(methodName + ": " + browserName);
	}

	// Get the name of the test method
	protected String getTestMethodName() {
		Method method = getTestMethod();
		if (method != null) {
			return method.getName();
		}
		return "Unknown";
	}

	// Get the test method using reflection
	private Method getTestMethod() {
		// Using an anonymous inner class to get the enclosing method
		Method enclosingMethod = new Object() {
		}.getClass().getEnclosingMethod();
		if (enclosingMethod != null) {
			// Check if the method has the Test annotation
			Test annotation = enclosingMethod.getAnnotation(Test.class);
			if (annotation != null) {
				return enclosingMethod;
			}
		}
		return null;
	}

}