package controller;

import model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
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

        private final UserProfileService userProfileService;

        @Autowired
        public UserProfileController(UserProfileService userProfileService) {
            this.userProfileService = userProfileService;
        }


        @GetMapping("/userprofiles")
        public List<UserProfile> getUserProfiles() {
            return userProfileService.getUserProfiles();
        }

//    @GetMapping("/userprofile/{userId}")
//    UserProfile getUserProfileByID(@PathVariable UUID userId) {
//
//        return this.userProfileService.(id)
//                .orElseThrow(() -> new UserProfileNotFoundException(userId));
//    }

        @PostMapping("/userprofile")
        @ResponseBody
        public void addUserProfile(@RequestBody @Valid UserProfile userProfile) {
            userProfileService.addNewUserProfile(userProfile);
        }

        @PutMapping("/userprofile/{userId}")
        public void updateUserProfile(@PathVariable("userId") UUID userId,
                                  @RequestBody UserProfile userProfile) {
            userProfileService.updateUserProfile(userId, userProfile);
        }

        @DeleteMapping("/userprofile/{userId}")
        public void deleteUserProfile(@PathVariable("userId") UUID userId) {
            userProfileService.deleteUserProfile(userId);
        }
}
