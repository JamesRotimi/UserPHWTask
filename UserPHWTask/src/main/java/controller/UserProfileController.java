package controller;

import controller.requestJson.UserProfileCreationData;
import controller.responseJson.UserProfileResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserProfileService;


@RestController
@RequestMapping(path = "/")
public class UserProfileController {

    @Autowired
    protected UserProfileService userProfileService;

    @PostMapping("/userprofile")
    public ResponseEntity<UserProfileResponseJson> addUserProfile(UserProfileCreationData userProfileCreationData) {

        UserProfileResponseJson userProfileResponseJson = userProfileService.createUserProfileFrom(userProfileCreationData);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userProfileResponseJson);
    }
}
