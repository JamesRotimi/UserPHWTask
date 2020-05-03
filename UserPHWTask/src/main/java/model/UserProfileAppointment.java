package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class UserProfileAppointment {

    private final UUID usersProfileAppointmentId;

    @NotBlank(message = "UserProfile must not be null or blank")
    private UserProfile userProfileId;

    @NotBlank(message = "roleId must not be null or blank")
    private long roleId;

    @NotBlank(message = "roleDescription must not be null or blank")
    private String roleDescription;

    @NotBlank(message = "organisationName must not be null or blank")
    private String organisationName;

    @JsonCreator
    public UserProfileAppointment(@JsonProperty(value = "usersProfileAppointmentId") UUID usersProfileAppointmentId,
                       @JsonProperty(value = "userProfileId") UserProfile userProfileId,
                       @JsonProperty(value = "roleId") long roleId,
                       @JsonProperty(value = "roleDescription") String roleDescription,
                       @JsonProperty(value = "organisationName") String organisationName)
                       {

    {
        this.usersProfileAppointmentId = usersProfileAppointmentId;
        this.userProfileId = userProfileId;
        this.roleId = roleId;
        this.roleDescription = roleDescription;
        this.organisationName = organisationName;
    }
  }

    @Override
    public String toString() {
        return "UserProfileAppointment [usersProfileAppointmentId=" + usersProfileAppointmentId + ", userProfileId=" + userProfileId + ", roleId=" + roleId + ", roleDescription=" + roleDescription +
                " + organisationName =" + organisationName  + " ]";
    }
}
