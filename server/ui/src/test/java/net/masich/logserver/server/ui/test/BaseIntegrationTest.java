package net.masich.logserver.server.ui.test;

import net.masich.logserver.server.ui.config.JpaConfig;
import net.masich.logserver.server.ui.config.MvcConfig;
import net.masich.logserver.server.ui.config.SecurityConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, JpaConfig.class, SecurityConfig.class})
public abstract class BaseIntegrationTest {

    protected final static SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor SIGNED_IN_USER = user("w3cvalid@gmail.com");

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void baseSetUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                                 .addFilters(springSecurityFilterChain)
                                 .build();
    }

}
