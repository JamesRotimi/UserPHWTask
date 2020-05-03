package service;

import com.example.UserPHWTask.exception.ApiRequestException;
import com.example.UserPHWTask.exception.EmailValidator;
import model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final EmailValidator emailValidator;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService,
                          EmailValidator emailValidator) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.emailValidator = emailValidator;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.selectUserProfiles();
    }

    public void addNewUserProfile(UserProfile userProfile) {
        addNewUserProfile(null, userProfile);
    }

    void addNewUserProfile(UUID userId, UserProfile userProfile) {
        UUID newUserId = Optional.ofNullable(userId)
                .orElse(UUID.randomUUID());

        if (!emailValidator.test(userProfile.getEmailId())) {
            throw new ApiRequestException(userProfile.getEmailId() + " is not valid");
        }

        if (userProfileDataAccessService.isEmailTaken(userProfile.getEmailId())) {
            throw new ApiRequestException(userProfile.getEmailId() + " is taken");
        }

        userProfileDataAccessService.insertUserProfile(newUserId, userProfile);
    }

//    List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
//        return userProfileDataAccessService.selectAllStudentCourses(studentId);
//    }

    public void updateUserProfile(UUID userId, UserProfile userprofile) {
        Optional.ofNullable(userprofile.getEmailId())
                .ifPresent(emailId -> {
                    boolean taken = userProfileDataAccessService.selectExistsEmail(userId, emailId);
                    if (!taken) {
                        userProfileDataAccessService.updateEmail(userId, emailId);
                    } else {
                        throw new IllegalStateException("Email already in use: " + userprofile.getEmailId());
                    }
                });

        Optional.ofNullable(userprofile.getFirstName())
                .filter(fistName -> !StringUtils.isEmpty(fistName))
                .map(StringUtils::capitalize)
                .ifPresent(firstName -> userProfileDataAccessService.updateFirstName(userId, firstName));

        Optional.ofNullable(userprofile.getLastName())
                .filter(lastName -> !StringUtils.isEmpty(lastName))
                .map(StringUtils::capitalize)
                .ifPresent(lastName -> userProfileDataAccessService.updateLastName(userId, lastName));
    }

    public void deleteUserProfile(UUID userId) {
        userProfileDataAccessService.deleteUserProfileById(userId);
    }
}
