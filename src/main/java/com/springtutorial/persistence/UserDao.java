package com.springtutorial.persistence;

import com.springtutorial.model.User;
import com.springtutorial.model.UserField;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<User> findByUserName(String username) {
        return Optional.ofNullable(
                mongoTemplate
                        .findOne(
                                query(
                                        where(UserField.USER_NAME.field()).is(username))
                                , User.class));
    }

    public void save(@NonNull User user) {
        mongoTemplate.save(user);
    }
}
