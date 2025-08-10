package kg.attractor.jobsearch.model.mappers;

import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Resume;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        category.setParentId(rs.getLong("parent_id"));

        return category;
    }
}
