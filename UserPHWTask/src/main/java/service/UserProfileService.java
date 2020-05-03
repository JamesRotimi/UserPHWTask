package service;

import controller.requestJson.UserProfileCreationData;
import controller.responseJson.UserProfileResponseJson;
import model.UserAppointment;
import model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserProfileService {

    UserProfileResponseJson createUserProfileFrom(UserProfileCreationData userProfileCreationData);

    public List<UserProfile> getUserProfiles();

    List<UserAppointment> getAllUserAppointmentsForUser(long UserId);

    public void updateUserProfile(long userId, UserProfile userprofile);

    public void deleteUserProfile(long userId);
}
