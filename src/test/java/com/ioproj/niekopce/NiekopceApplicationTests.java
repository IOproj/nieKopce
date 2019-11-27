package com.ioproj.niekopce;

import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Repositories.UserAccountRepository;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.UUID;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class NiekopceApplicationTests {

    @Autowired
    UserAccountRepository userAccountRepository;


    @Test
    public void testEmbeddedPg() throws Exception
    {
        UserAccount empty = new UserAccount();
       UserAccount newAcc = new UserAccount();
       newAcc.setUsername("testMan");
       newAcc.setPassword("testPass");
       newAcc.setIsServiceman(false);
       newAcc.setUuid(UUID.randomUUID());
       newAcc.setDbId(100L);
       newAcc.setEmail("test@test.test");
       userAccountRepository.save(newAcc);
       UserAccount found = userAccountRepository.findByUsername(newAcc.getUsername()).orElse(empty);
        Assert.assertEquals(newAcc.getUsername(),found.getUsername());
    }
}
