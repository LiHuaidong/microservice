package hdli.service.impl;

import hdli.service.IAspectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AspectService implements IAspectService {

    private Logger logger = LoggerFactory.getLogger(AspectService.class);

    @Override
    public void sayHello(String email) {
        logger.info("sayHello to >>>>{}", email);
    }

}
