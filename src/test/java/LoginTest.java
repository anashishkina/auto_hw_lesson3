import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LoginTest {

    @BeforeAll
    static void setBrowserSize(){
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @Test
    void TestLoginForm(){

        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#submit").scrollIntoView(true);

        $("[id=firstName]").setValue("Thomas");
        $("#lastName").setValue("Mueller");
        $("[id=userEmail]").setValue("tommymueller@gmail.com");
        $("#genterWrapper").$(byText("Other")).click();
        $("[id=userNumber]").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("8");
        $(".react-datepicker__year-dropdown-container").$(byText("1989")).click();
        $(".react-datepicker__month-container").$(byText("13")).click();
        $("#subjectsInput").setValue("Co").pressEnter();
        $(byText("Sports")).click();
        $("#hobbies-checkbox-3").parent().$(byText("Music")).click();

        File avatar = new File("src/test/resources/pictures/IMG_4379.JPG");
        $("#uploadPicture").uploadFile(avatar);

        $("[id=currentAddress]").setValue("Saebener Strasse 51, Muenchen");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Rajasthan")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Jaiselmer")).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

    }
}
