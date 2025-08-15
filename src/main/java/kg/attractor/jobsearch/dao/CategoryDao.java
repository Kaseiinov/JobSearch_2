//package kg.attractor.jobsearch.dao;
//
//import kg.attractor.jobsearch.model.Category;
//import kg.attractor.jobsearch.model.mappers.CategoryMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class CategoryDao {
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<Category> findAll(){
//        String sql = "select * from categories";
//
//        return jdbcTemplate.query(sql, new CategoryMapper());
//    }
//}
