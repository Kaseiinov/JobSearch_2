package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.mappers.UserMapper;
import kg.attractor.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> findAll(){
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findByName(String name){
        String sql = "select * from users where name = ?;";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), name)
                )
        );
    }

    public Optional<User> findByEmail(String email){
        String sql = "select * from users where email = ?;";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), email)
                )
        );
    }

//    public List<User> findByEmail(String email){
//        String sql = "select * from users where email ilike :email;";
//        return namedParameterJdbcTemplate.query(
//                sql,
//                new MapSqlParameterSource()
//                        .addValue("email", "%" + email + "%"),
//                new UserMapper()
//        );
//    }

    public Optional<User> findByPhoneNumber(String number){
        String sql = "select * from users where phone_number = ?;";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), number)
                )
        );
    }


}
