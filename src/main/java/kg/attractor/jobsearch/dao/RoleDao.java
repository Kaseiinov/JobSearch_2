package kg.attractor.jobsearch.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public Long getRoleIdByName(String roleName){
        String sql = "select id from roles where lower(role_name) = ? limit 1;";
        return jdbcTemplate.queryForObject(sql, Long.class, roleName);
    }
}
