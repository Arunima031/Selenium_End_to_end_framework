package org.practice.dataProviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.practice.pojoClasses.webPojo.LoginScenario;
import org.testng.annotations.DataProvider;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<LoginScenario> scenarios = Arrays.asList(
                    mapper.readValue(
                            Paths.get("src/test/java/org/practice/testData/jsonData/loginScenarios.json").toFile(),
                            LoginScenario[].class
                    )
            );
            return scenarios.stream()
                    .map(s -> new Object[]{s})
                    .toArray(Object[][]::new);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load login test data", e);
        }
    }
}
