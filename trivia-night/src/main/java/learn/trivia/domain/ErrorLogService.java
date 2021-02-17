package learn.trivia.domain;

import learn.trivia.data.ErrorLogJdbcTemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogService {

    private final ErrorLogJdbcTemplateRepository repository;

    public ErrorLogService (ErrorLogJdbcTemplateRepository repository){
        this.repository = repository;
    }

    public boolean logError(String description) { return repository.logError(description); }

}