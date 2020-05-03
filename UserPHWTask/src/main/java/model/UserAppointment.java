package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity(name = "userAppointments")
public class UserAppointment {

    @Id
    private long AppointmentId;

    @Column(name = "role_id")
    private long roleId;

    @Column(name = "role_description")
    @Size(max = 255)
    private String roleDescription;

    @Column(name = "organisation_name")
    @Size(max = 255)
    private String organisationName;

    @ManyToOne
    @JoinColumn(name = "userProfile_id")
    private UserProfile userprofile;

    public UserAppointment(long roleId, String roleDescription, String organisationName, UserProfile userprofile) {
        this.roleId = roleId;
        this.roleDescription = roleDescription;
        this.organisationName = organisationName;
        this.userprofile = userprofile;
    }
}