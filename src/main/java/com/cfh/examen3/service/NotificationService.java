package com.cfh.examen3.service;

import ch.qos.logback.classic.Logger;
import com.cfh.examen3.dto.NotificationDto;
import com.cfh.examen3.mapper.NotificationMapper;
import com.cfh.examen3.model.Notification;
import com.cfh.examen3.repository.NotificationRepository;
import com.cfh.examen3.util.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

/**
 * @author Tyler Durden christian.fuentesh@elektra.com.mx
 * @proyect examen-e3
 */
@Service
public class NotificationService {
    private static Logger log = (Logger) LoggerFactory.getLogger(NotificationService.class.getName());
    private NotificationRepository notificationRepository;
    public NotificationService(NotificationRepository repository){
        this.notificationRepository = repository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processNotification(NotificationDto notificationDto){
        log.info("Procesando notificacion recibida de RabbitMQ..."+notificationDto.toString());
        try{
            Notification model = NotificationMapper.MAPPER.mapDtoToModel(notificationDto);
            this.notificationRepository.save(model);
        }
        catch (Exception e){
            log.error("Ocurrio un error al procesar la notificacion");
            e.printStackTrace();
        }

    }
    public ResponseEntity<Page<NotificationDto>> getNotificationList(Pageable pageable){
        log.info("Obteniendo registros de notificaciones");
        try{
            Page<Notification> notificationPage = this.notificationRepository.findAll(pageable);
            List<NotificationDto> notificationDtoList = NotificationMapper.MAPPER.mapListModelToDto(notificationPage.getContent());
            Page<NotificationDto> notificationDtoPage = new PageImpl<>(notificationDtoList, pageable, notificationPage.getTotalElements());
            return ResponseEntity.ok(notificationDtoPage);
        }
        catch (Exception e){
            log.error("Ocurrio un error al obtener las notificaciones "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
