package controller;

import controller.requestJson.UserProfileCreationData;
import controller.responseJson.UserProfileResponseJson;
import model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserProfileService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
