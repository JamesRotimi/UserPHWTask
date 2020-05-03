package dao;

import model.UserAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppointmentRepository extends JpaRepository<UserAppointment, Long> {
}
