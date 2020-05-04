package controller.responseJson;

import model.UserAppointment;
import model.UserProfile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserAppointmentResponseJsonTest {

            UserProfile userProfile = new UserProfile();
            UserAppointment userAppointment = new UserAppointment(6, "Tennis", "Wimbledon", userProfile);
            UserAppointmentResponseJson userAppointmentResponseJson = new UserAppointmentResponseJson(userAppointment);

            @Test
            public void make_user_appointments() {
            final long roleId = 6;
            final String roleDescription = "Tennis";
            final String organisationName = "Wimbledon";

            assertThat(userAppointmentResponseJson.getRoleId()).isEqualTo(roleId);
            assertThat(userAppointmentResponseJson.getRoleDescription()).isEqualTo(roleDescription);
            assertThat(userAppointmentResponseJson.getOrganisationName()).isEqualTo(organisationName);
        }
    }