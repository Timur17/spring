package ru.otus.spring.repostory;

import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Email;

import java.util.List;

@Repository
public class EmailRepositoryImp implements EmailRepository {
    final private KeyValueOperations keyValueTemplate;

    public EmailRepositoryImp(KeyValueOperations keyValueTemplate) {
        this.keyValueTemplate = keyValueTemplate;
    }

    @Override
    public List<Email> findAll() {
        return (List<Email>) keyValueTemplate.findAll(Email.class);
    }

    @Override
    public Email save(Email email) {
        return (Email) keyValueTemplate.insert(email);
    }
}
