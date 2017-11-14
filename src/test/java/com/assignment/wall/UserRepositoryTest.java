package com.assignment.wall;

import com.assignment.wall.model.User;
import com.assignment.wall.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adharamshi on 11/13/17.
 */
@RunWith(SpringRunner.class) //used to provide a bridge between Spring Boot test features and JUnit
@DataJpaTest //configuring H2, an in-memory database, setting Hibernate, Spring Data, and the DataSource, performing an @EntityScan
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        User usr = new User("Jon", "jon");
        entityManager.persist(usr);
        entityManager.flush();

        User found = userRepository.findOne(usr.getUserId());

        assertThat(found.getUserId()).isEqualTo(usr.getUserId());
    }


}
