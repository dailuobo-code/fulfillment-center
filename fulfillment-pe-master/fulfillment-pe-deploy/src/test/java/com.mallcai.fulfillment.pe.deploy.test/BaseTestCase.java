package com.mallcai.fulfillment.pe.deploy.test;

import com.mallcai.fulfillment.pe.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-26 22:38:42
 */
@SpringBootTest(classes= Application.class)
@TestExecutionListeners(inheritListeners = false, listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, MockitoTestExecutionListener.class})
public class BaseTestCase extends AbstractTestNGSpringContextTests {
}
