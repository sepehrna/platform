//package com.platform.workflow.service;
//
//import org.camunda.bpm.engine.IdentityService;
//import org.camunda.bpm.engine.ProcessEngine;
//import org.camunda.bpm.engine.identity.User;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class UserServices {
//
//    @Autowired
//    private ProcessEngine processEngine;
//
//    public void synchronizeUsers() {
//        String usersJson = fetchExternalUsers();
//        JSONArray usersArray = new JSONArray(usersJson);
//
//        IdentityService identityService = processEngine.getIdentityService();
//
//        for (int i = 0; i < usersArray.length(); i++) {
//            JSONObject externalUser = usersArray.getJSONObject(i);
//            String userId = externalUser.getString("id");
//
//            User camundaUser = identityService.createUserQuery().userId(userId).singleResult();
//
//            if (camundaUser == null) {
//                camundaUser = identityService.newUser(userId);
//            }
//
//            camundaUser.setId(externalUser.getString("id"));
//            camundaUser.setEmail(externalUser.getString("email"));
//            camundaUser.setPassword(externalUser.getString("password"));
//            camundaUser.setFirstName(externalUser.getString("username"));
//
//            identityService.saveUser(camundaUser);
//        }
//    }
//
//    private String fetchExternalUsers() {
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject("https://external-system/api/users", UserDto.class);
//
//        if (result == null || result.isEmpty()) {
//            throw new RuntimeException("Failed to fetch users from external system");
//        }
//
//        return result;
//    }
//}
