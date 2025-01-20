package com.cfh.examen3.controller;

import com.cfh.examen3.config.RabbitConfig;
import com.cfh.examen3.dto.NotificationDto;
import com.cfh.examen3.service.NotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tyler Durden christian.fuentesh@elektra.com.mx
 * @proyect examen-e3
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private RabbitTemplate rabbitTemplate;
    private NotificationService notificationService;

    public NotificationController(RabbitTemplate rt, NotificationService service){
        this.rabbitTemplate = rt;
        this.notificationService = service;
    }

    @PostMapping
    public void sendNotification(@RequestBody NotificationDto notificationDto){
        this.rabbitTemplate.convertAndSend(RabbitConfig.rabbitExchange, RabbitConfig.rabbitRouting, notificationDto);
    }
    @GetMapping
    public ResponseEntity<Page<NotificationDto>> getNotificationList(Pageable pageable) {
        return this.notificationService.getNotificationList(pageable);
    }
}
