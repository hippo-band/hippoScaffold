package junitTest;

import com.healthmanagement.report.service.PingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = "classpath:/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PingServiceTest {

  @Autowired
  private PingService pingService;

  @Test
  public void ping() throws Exception {
   
   String result= pingService.ping();
  }
}
