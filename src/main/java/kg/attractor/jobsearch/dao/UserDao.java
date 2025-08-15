//package kg.attractor.jobsearch.dao;
//
//import kg.attractor.jobsearch.model.mappers.UserMapper;
//import kg.attractor.jobsearch.model.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.dao.support.DataAccessUtils;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class UserDao {
//    private final JdbcTemplate jdbcTemplate;
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public Boolean isExistsUser(String email){
//        String sql = "select exists(select 1 from users where email = ?);";
//        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
//    }
//
//    public void saveUser(User user){
//        String sql = "insert into users(name, surname, age, email, password, phone_number, avatar, account_type, role_id, enabled)" +
//                " values(:name, :surName, :age, :email, :password, :phoneNumber, :avatar, :accountType, :roleId, :enabled);";
//
//        namedParameterJdbcTemplate.update(sql,
//                new MapSqlParameterSource()
//                        .addValue("name", user.getName())
//                        .addValue("surName", user.getSurname())
//                        .addValue("age", user.getAge())
//                        .addValue("email", user.getEmail())
//                        .addValue("password", user.getPassword())
//                        .addValue("phoneNumber", user.getPhoneNumber())
//                        .addValue("avatar", user.getAvatar())
//                        .addValue("accountType", user.getAccountType())
//                        .addValue("roleId", user.getRoleId())
//                        .addValue("enabled", user.getEnabled())
//
//        );
//    }
//
//    public void update(User user, String email){
//        String sql = "update users set " +
//                "name = :name, " +
//                "surname = :surname, " +
//                "age = :age, " +
//                "phone_number = :phoneNumber, " +
//                "account_type = :account_type, " +
//                "avatar = :avatar " +
//                "where email = :email;";
//
//        namedParameterJdbcTemplate.update(sql,
//                new MapSqlParameterSource()
//                        .addValue("name", user.getName())
//                        .addValue("surname", user.getSurname())
//                        .addValue("age", user.getAge())
//                        .addValue("phoneNumber", user.getPhoneNumber())
//                        .addValue("account_type", user.getAccountType())
//                        .addValue("email", email)
//                        .addValue("avatar", user.getAvatar().getFileName())
//        );
//    }
//
//    public List<User> findAll(){
//        String sql = "select * from users";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
//    }
//
//    public Optional<User> findByName(String name){
//        String sql = "select * from users where name = ?;";
//        return Optional.ofNullable(
//                DataAccessUtils.singleResult(
//                        jdbcTemplate.query(sql, new UserMapper(), name)
//                )
//        );
//    }
//
//    public Optional<User> findByEmail(String email){
//        String sql = "select * from users where email = ?;";
//        return Optional.ofNullable(
//                DataAccessUtils.singleResult(
//                        jdbcTemplate.query(sql, new UserMapper(), email)
//                )
//        );
//    }
//
////    public List<User> findByEmail(String email){
////        String sql = "select * from users where email ilike :email;";
////        return namedParameterJdbcTemplate.query(
////                sql,
////                new MapSqlParameterSource()
////                        .addValue("email", "%" + email + "%"),
////                new UserMapper()
////        );
////    }
//
//    public Optional<User> findByPhoneNumber(String number){
//        String sql = "select * from users where phone_number = ?;";
//        return Optional.ofNullable(
//                DataAccessUtils.singleResult(
//                        jdbcTemplate.query(sql, new UserMapper(), number)
//                )
//        );
//    }
//
//
//}
