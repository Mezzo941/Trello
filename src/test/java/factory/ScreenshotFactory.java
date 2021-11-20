package factory;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utils.PropertyReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ScreenshotFactory {

    private static final String BASE_DIRECTORY_PATH = PropertyReader.getProperty("trello.screen.path");
    private static File directory = new File(BASE_DIRECTORY_PATH);

    public static void deleteScreenDir() throws IOException {
        directory = new File(BASE_DIRECTORY_PATH);
        if (directory.exists()) {
            if (directory.isDirectory()) {
                FileUtils.deleteDirectory(directory);
            }
        }
    }

    private static File createScreenDir() {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }

    private static List<File> getFilesFromDir() {
        List<File> files = new ArrayList<>();
        try {
            for (File file : directory.listFiles()) {
                if (file.exists())
                    files.add(file);
            }
        } catch (NullPointerException e) {
            return null;
        }
        return files;
    }

    public static void createScreenshot(WebDriver driver, ITestResult test) throws IOException {
        createScreenDir();
        File screenshot;
        List<File> files = getFilesFromDir();
        try {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                if (files != null) {
                    FileUtils.copyFile(screenshot, new File(BASE_DIRECTORY_PATH, (String.format("%s%s.png", test.getName(), files.size()))));
                } else {
                    FileUtils.copyFile(screenshot, new File(BASE_DIRECTORY_PATH, (String.format("%s%s.png", test.getName(), 0))));
                }
                log.error("Screenshots path: " + BASE_DIRECTORY_PATH);
            } catch (IOException e1) {
                log.error("File named 'screenshots' already exists");
            }
        } catch (NullPointerException e2) {
            log.error("driver is null");
        }

    }


}
