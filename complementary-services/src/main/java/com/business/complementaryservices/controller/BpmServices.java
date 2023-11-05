//package com.business.complementaryservices.controller;
//
//import com.business.complementaryservices.dto.CompleteExternalTaskDto;
//import com.business.complementaryservices.dto.TaskDto;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@CrossOrigin(origins = {"*", "*/**"}, maxAge = 3600)
//@RequestMapping(path = "/bpm-services", consumes = {MediaType.ALL_VALUE})
//@RestController
//@OpenAPIDefinition(info = @Info(title = "camunda-api", version = "v1"))
//public class BpmServices {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Value("${microservices.api-gateway.url}")
//    private String apiGatewayUrl;
//
//    @GetMapping("/start-booking")
//    public String startBookingProcess() {
//        String url = "" + "/engine-rest/process-definition/key/cust_room_booking/start";
//        return restTemplate.postForObject(url, null, String.class);
//    }
//
//    public List<TaskDto> getUserTasks(String processInstanceId) {
//        String url = camundaBaseUrl + "/user-tasks/" + processInstanceId;
//        TaskDto[] tasks = restTemplate.getForObject(url, TaskDto[].class);
//        if (tasks != null) {
//            return Arrays.asList(tasks);
//        }
//        return new ArrayList<>();
//    }
//
//    public Object getProcessInstanceVariable(String processInstanceId, String variableName) {
//        String url = camundaBaseUrl + "/process-instance-variable/" + processInstanceId + "/" + variableName;
//        return restTemplate.getForObject(url, Object.class);
//    }
//
//    @PutMapping("/complete/{taskId}")
//    public void completeTask(@PathVariable String taskId) {
//        String url = camundaBaseUrl + "/engine-rest/external-task/" + taskId + "/complete";
//
//        CompleteExternalTaskDto request = new CompleteExternalTaskDto();
//
//        restTemplate.postForEntity(url, request, Void.class);
//    }
//}
