package Automation.Base.ScreenAndRecord;
import Automation.Base.ExcelandProperties.ResolveProperties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screen {
    //Lấy đường dẫn đến project hiện tại
    static String projectPath = System.getProperty("user.dir") + "/";
    //Tạo format ngày giờ để xíu gắn dô cái name của screenshot hoặc record video
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void captureScreenshot(WebDriver driver, String screenName) {
       ResolveProperties.setPropertiesFile();
        try {
            Reporter.log("Driver for Screenshot: " + driver);
            // Tạo tham chiếu đối tượng của TakesScreenshot với dirver hiện tại
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
            File source = ts.getScreenshotAs(OutputType.FILE);
            //Kiểm tra folder nếu không tồn tại thì tạo folder
            File theDir = new File(projectPath + ResolveProperties.getPropValue("exportCapturePath"));
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            // Chổ này đặt tên thì truyền biến "screenName" gán cho tên File chụp màn hình
            FileHandler.copy(source, new File(projectPath + ResolveProperties.getPropValue("exportCapturePath") + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png"));
            System.out.println("Screenshot taken: " + screenName);
            Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}

//// Nó sẽ thực thi sau mỗi lần thực thi testcase (@Test)
//@AfterMethod
//public void takeScreenshot(ITestResult result) {
//    // Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Step
//    // Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
//    // passed = SUCCESS và failed = FAILURE
//    if (ITestResult.FAILURE == result.getStatus()) {
//        try {
//         sample.captureScreenshot(driver, result.getname());
//        } catch (Exception e) {
//            System.out.println("Exception while taking screenshot " + e.getMessage());
//        }
//    }
//}