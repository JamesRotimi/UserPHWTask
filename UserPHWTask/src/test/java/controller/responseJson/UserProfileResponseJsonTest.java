package controller.responseJson;

import model.UserAppointment;
import model.UserProfile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserProfileResponseJsonTest {

    @Test
    public void make_user_response() {
        final String FirstName = "Ken";
        final String LastName = "Ben";
        final String EmailAddress = "foo@bar.com";

        UserProfile userProfile = new UserProfile(FirstName, LastName, EmailAddress);
        UserAppointment userAppointment = new UserAppointment(6, "Med", "Nhs", userProfile);
        userProfile.addUserAppointment(userAppointment);

        UserProfileResponseJson userProfileResponseJson = new UserProfileResponseJson(userProfile);

        assertThat(userProfileResponseJson.getUserAppointments().size()).isEqualTo(1);
        assertThat(userProfileResponseJson.getFirstName()).isEqualTo(FirstName);
        assertThat(userProfileResponseJson.getLastName()).isEqualTo(LastName);
        assertThat(userProfileResponseJson.getEmailAddress()).isEqualTo(EmailAddress);


    }
}