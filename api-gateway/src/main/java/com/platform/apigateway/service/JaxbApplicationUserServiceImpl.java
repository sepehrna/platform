package com.platform.apigateway.service;

import com.platform.apigateway.domain.ApplicationUserService;
import com.platform.apigateway.domain.entities.ApplicationUsers;
import com.platform.apigateway.service.dto.JaxbApplicationUsersDto;
import com.platform.apigateway.service.mapper.JaxbApplicationUsersMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
public class JaxbApplicationUserServiceImpl implements ApplicationUserService {

    private final Logger logger = LoggerFactory.getLogger(JaxbApplicationUserServiceImpl.class);
    private ApplicationUsers applicationUsers;
    private final JaxbApplicationUsersMapper jaxbApplicationUsersMapper;
    private final Environment environment;

    @Autowired
    public JaxbApplicationUserServiceImpl(JaxbApplicationUsersMapper jaxbApplicationUsersMapper, Environment environment) {
        this.jaxbApplicationUsersMapper = jaxbApplicationUsersMapper;
        this.environment = environment;
    }

    @Override
    public ApplicationUsers getApplicationUsers() {
        return applicationUsers;
    }

    @Override
    public void initiateUsers() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(JaxbApplicationUsersDto.class);
        String applicationUserFilePath = environment.getProperty("application.user.xml.path");
        if (applicationUserFilePath == null) {
            logger.error("Application user file path not found please check application.properties file");
            throw new IOException("Application user file path not found");
        }
        JaxbApplicationUsersDto jaxbApplicationUsersDto = (JaxbApplicationUsersDto) context.createUnmarshaller()
                .unmarshal(new FileReader(applicationUserFilePath));
        applicationUsers = jaxbApplicationUsersMapper.map(jaxbApplicationUsersDto);
        logger.info("Application users have been loaded successfully!!");
    }
}
