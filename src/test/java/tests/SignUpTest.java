package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @Test()
    public void hasCaptchaBeforeSendingMessageTest() {
        Assert.assertTrue
                (
                        homePage.
                                open().
                                signUp().
                                registrationByEmail("bucherkruche@gmail.com", "Асланбек")
                                .isCaptchaVisible()
                );
    }

}
