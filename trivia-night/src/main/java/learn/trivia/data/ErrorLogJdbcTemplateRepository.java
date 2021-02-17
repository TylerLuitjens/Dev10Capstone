package learn.trivia.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@Repository
public class ErrorLogJdbcTemplateRepository {
    private final JdbcTemplate template;

    public ErrorLogJdbcTemplateRepository(JdbcTemplate template) { this.template = template; }

    public boolean logError(String description) {
        LocalDateTime timestamp = LocalDateTime.now();
        final String sql = "INSERT INTO error_log " +
                "(description, error_timestamp) " +
                "VALUES (?, ?);";

        return template.update( connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, description);
            ps.setString(2, timestamp.toString());

            return ps;
        }) > 0;
    }
}
