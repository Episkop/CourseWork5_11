package ua.student.coursetest.Controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.student.coursetest.Model.UserModel;

import java.util.Map;

@RestController
public class RController {
    @GetMapping("account")
    public UserModel account(OAuth2AuthenticationToken auth) {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();

        String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String pictureUrl = (String) attrs.get("picture");

        return UserModel.of(email, name, pictureUrl);
    }
}
