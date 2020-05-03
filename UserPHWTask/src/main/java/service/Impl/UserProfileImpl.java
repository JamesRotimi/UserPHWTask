package service.Impl;

import controller.requestJson.UserAppointmentCreationData;
import controller.requestJson.UserProfileCreationData;
import controller.responseJson.UserProfileResponseJson;
import dao.UserAppointmentRepository;
import dao.UserProfileRepository;
import exception.ApiRequestException;
import exception.EmailValidator;
import model.UserAppointment;
import model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserProfileService;

import java.util.List;

public class UserProfileImpl implements UserProfileService {

    @Autowired
    UserAppointmentRepository userAppointmentRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    private final EmailValidator emailValidator;

    public UserProfileImpl(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Override
    public UserProfileResponseJson createUserProfileFrom(UserProfileCreationData userProfileCreationData) {

        if (!emailValidator.test(userProfileCreationData.getEmailAddress())) {
            throw new ApiRequestException(userProfileCreationData.getEmailAddress() + " is not valid");
        }

//        if (userProfileDataAccessService.isEmailTaken(userProfileCreationData.getEmailAddress())) {
//            throw new ApiRequestException(userProfileCreationData.getEmailAddress() + " is taken");
//        }

               UserProfile newUserProfile = new UserProfile(userProfileCreationData.getFirstName(),userProfileCreationData.getLastName(),userProfileCreationData.getEmailAddress());

                  UserProfile userProfile =  userProfileRepository.save(newUserProfile);

                  addAppointmentsToUser(userProfileCreationData.getUserAppointments(), userProfile);

                  return new UserProfileResponseJson(userProfile);
    }

    private void addAppointmentsToUser(List<UserAppointmentCreationData> userAppointmentCreationData, UserProfile userProfile) {

        if (userAppointmentCreationData != null) {
            userAppointmentCreationData.forEach(UserAppointmentDetails -> {
                UserAppointment newUserAppointment = new UserAppointment();
                newUserAppointment.setRoleId(UserAppointmentDetails.getRoleId());
                newUserAppointment.setRoleDescription(UserAppointmentDetails.getRoleDescription());
                newUserAppointment.setOrganisationName(UserAppointmentDetails.getOrganisationName());
                newUserAppointment.setUserprofile(userProfile);

                userAppointmentRepository.save(newUserAppointment);

                userProfile.addUserAppointment(newUserAppointment);
            });
        }
    }


    @Override
    public List<UserProfile> getUserProfiles() {
        return null;
    }

    @Override
    public List<UserAppointment> getAllUserAppointmentsForUser(long UserId) {
        return null;
    }

    @Override
    public void updateUserProfile(long userId, UserProfile userprofile) {

    }

    @Override
    public void deleteUserProfile(long userId) {

    }
}
