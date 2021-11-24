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
    private static final File BASE_DIRECTORY = new File(BASE_DIRECTORY_PATH);
    private static File screenDirectory;

    public static void createScreenshot(WebDriver driver, String dirName, ITestResult test) {
        try {
            createScreenDir(dirName);
            List<File> files = getFilesFromDir();
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                if (files != null) {
                    FileUtils.copyFile(screenshot, new File(screenDirectory.getPath(), (String.format("%s%s.png", test.getName(), files.size()))));
                } else {
                    FileUtils.copyFile(screenshot, new File(screenDirectory.getPath(), (String.format("%s%s.png", test.getName(), 0))));
                }
                log.info("Screenshots path: " + screenDirectory);
            } catch (IOException e1) {
                log.error("File named 'screenshots' already exists");
            }
        } catch (NullPointerException e2) {
            log.error("driver is null");
        }
    }

    private static void createScreenDir(String screenDirectoryPath) {
        screenDirectoryPath = BASE_DIRECTORY_PATH + screenDirectoryPath;
        if (!BASE_DIRECTORY.exists() || (BASE_DIRECTORY.exists() && !new File(screenDirectoryPath).exists())) {
            screenDirectory = new File(screenDirectoryPath);
            screenDirectory.mkdirs();
        } else screenDirectory = new File(screenDirectoryPath);
    }

    public static void deleteScreenDir() throws IOException {
        if (BASE_DIRECTORY.exists()) {
            if (BASE_DIRECTORY.isDirectory()) {
                FileUtils.deleteDirectory(BASE_DIRECTORY);
            }
        }
    }

    private static List<File> getFilesFromDir() {
        List<File> files = new ArrayList<>();
        try {
            if (screenDirectory.listFiles() != null) {
                for (File file : screenDirectory.listFiles()) {
                    if (file.exists())
                        files.add(file);
                }
            }
            else {
                return null;
            }
        } catch (NullPointerException e) {
            log.info("directory is empty");
        }
        return files;
    }


}
