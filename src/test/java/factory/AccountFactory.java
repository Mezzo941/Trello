package factory;

import org.testng.Assert;
import utils.PropertyReader;

public class AccountFactory {

    public static String getAccount(String id) {
        String email;
        if (id.equals("1")) {
            email = PropertyReader.getProperty("trello.email1");
        } else if (id.equals("2")) {
            email = PropertyReader.getProperty("trello.email2");
        } else {
            email = PropertyReader.getProperty("trello.email1");
        }
        if (email == null) {
            Assert.fail("Email is null");
        }
        return email;
    }

}
