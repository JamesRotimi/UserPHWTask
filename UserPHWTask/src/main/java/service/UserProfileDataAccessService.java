package service;

import model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserProfileDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserProfileDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<UserProfile> selectUserProfiles() {
        String sql = "" +
                "SELECT " +
                " user_id, " +
                " first_name, " +
                " last_name, " +
                " email_id, " +
                "FROM userprofile";

        return jdbcTemplate.query(sql, mapUserprofileFomDb());
    }

    int insertUserProfile(UUID userId, UserProfile userprofile) {
        String sql = "" +
                "INSERT INTO userprofile (" +
                " user_id, " +
                " first_name, " +
                " last_name, " +
                " email_id, " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                userId,
                userprofile.getFirstName(),
                userprofile.getLastName(),
                userprofile.getEmailId()
        );
    }

    @SuppressWarnings("ConstantConditions")
    boolean isEmailTaken(String email) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM userprofile " +
                " WHERE email = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{email},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }

//    List<StudentCourse> selectAllStudentCourses(UUID studentId) {
//        String sql = "" +
//                "SELECT " +
//                " student.student_id, " +
//                " course.course_id, " +
//                " course.name, " +
//                " course.description," +
//                " course.department," +
//                " course.teacher_name," +
//                " student_course.start_date, " +
//                " student_course.end_date, " +
//                " student_course.grade " +
//                "FROM student " +
//                "JOIN student_course USING (student_id) " +
//                "JOIN course         USING (course_id) " +
//                "WHERE student.student_id = ?";
//        return jdbcTemplate.query(
//                sql,
//                new Object[]{studentId},
//                mapStudentCourseFromDb()
//        );
//    }
//
//    private RowMapper<StudentCourse> mapStudentCourseFromDb() {
//        return (resultSet, i) ->
//                new StudentCourse(
//                        UUID.fromString(resultSet.getString("student_id")),
//                        UUID.fromString(resultSet.getString("course_id")),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getString("department"),
//                        resultSet.getString("teacher_name"),
//                        resultSet.getDate("start_date").toLocalDate(),
//                        resultSet.getDate("end_date").toLocalDate(),
//                        Optional.ofNullable(resultSet.getString("grade"))
//                                .map(Integer::parseInt)
//                                .orElse(null)
//                );
//    }

    private RowMapper<UserProfile> mapUserprofileFomDb() {
        return (resultSet, i) -> {
            String userprofileIdStr = resultSet.getString("user_id");
            UUID userId = UUID.fromString(userprofileIdStr);

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String emailId = resultSet.getString("email_Id");
            String createdDateStr = resultSet.getString("createdDate");
            LocalDateTime createdDate = LocalDateTime.parse(createdDateStr);
            String lastUpdatedDateStr = resultSet.getString("lastUpdatedDate");
            LocalDateTime lastUpdatedDate = LocalDateTime.parse(lastUpdatedDateStr);
            return new UserProfile (
                    firstName,
                    lastName,
                    emailId

            );
        };
    }

    int updateEmail(UUID userId, String emailId) {
        String sql = "" +
                "UPDATE userprofile " +
                "SET email = ? " +
                "WHERE student_id = ?";
        return jdbcTemplate.update(sql, emailId, userId);
    }

    int updateFirstName(UUID userId, String firstName) {
        String sql = "" +
                "UPDATE userprofile " +
                "SET first_name = ? " +
                "WHERE user_id = ?";
        return jdbcTemplate.update(sql, firstName, userId);
    }

    int updateLastName(UUID userId, String lastName) {
        String sql = "" +
                "UPDATE userprofile " +
                "SET last_name = ? " +
                "WHERE user_id = ?";
        return jdbcTemplate.update(sql, lastName, userId);
    }

    @SuppressWarnings("ConstantConditions")
    boolean selectExistsEmail(UUID userId, String emailId) {
        String sql = "" +
                "SELECT EXISTS ( " +
                "   SELECT 1 " +
                "   FROM userprofile " +
                "   WHERE user_Id <> ? " +
                "    AND email_Id = ? " +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{userId, emailId},
                (resultSet, columnIndex) -> resultSet.getBoolean(1)
        );
    }

    int deleteUserProfileById(UUID userId) {
        String sql = "" +
                "DELETE FROM userprofile " +
                "WHERE student_id = ?";
        return jdbcTemplate.update(sql, userId);
    }
}

