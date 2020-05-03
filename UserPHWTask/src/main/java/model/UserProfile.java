package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserProfile {

    private UUID userId;

    @Email(regexp = "^.*[@].*[.].*$")
    @NotBlank(message = "email must not be null or blank")
    private String emailId;

    @NotBlank (message = "firstName must not be null or blank")
    private String firstName;

    @NotBlank (message = "lastName must not be null or blank")
    private String lastName;

    @NotBlank (message = "createdDate must not be null or blank")
    private LocalDateTime createdDate;

    @NotBlank (message = "lastUpdatedDate must not be null or blank")
    private LocalDateTime lastUpdatedDate;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonCreator
    public UserProfile(
                       @JsonProperty(value = "emailID") String emailId,
                       @JsonProperty(value = "firstName") String firstName,
                       @JsonProperty(value = "lastName") String lastName){

        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;


    }

    private List<UserProfileAppointment> userProfileAppointments;


    @Override
    public String toString() {
        return "UserProfile [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId +
                 " ]";
    }


    public List<UserProfileAppointment> getUserProfileAppointment() {
        return userProfileAppointments;
    }
}