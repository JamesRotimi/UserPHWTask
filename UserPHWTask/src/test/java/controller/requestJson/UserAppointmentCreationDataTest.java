package controller.requestJson;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserAppointmentCreationDataTest {

    private UserAppointmentCreationData userAppointmentCreationData;
    private long roleId = 1;
    private String roleDescription = "Basketball";
    private String organisationName = "Spurs";

    @Test
    public void make_user_appointment_request() {
        userAppointmentCreationData = new UserAppointmentCreationData(roleId, roleDescription, organisationName);
        assertThat(userAppointmentCreationData.getRoleId()).isEqualTo(roleId);
        assertThat(userAppointmentCreationData.getRoleDescription()).isEqualTo(roleDescription);
        assertThat(userAppointmentCreationData.getOrganisationName()).isEqualTo(organisationName);
    }

    @Test
    public void build_Method_Test() {
        userAppointmentCreationData = UserAppointmentCreationData.builder()
                .roleId(roleId)
                .roleDescription(roleDescription)
                .organisationName(organisationName)
                .build();

        assertThat(userAppointmentCreationData.getRoleId()).isEqualTo(roleId);
        assertThat(userAppointmentCreationData.getRoleDescription()).isEqualTo(roleDescription);
        assertThat(userAppointmentCreationData.getOrganisationName()).isEqualTo(organisationName);
    }
}
