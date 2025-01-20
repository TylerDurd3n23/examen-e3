package com.cfh.examen3.repository;

import com.cfh.examen3.dto.NotificationDto;
import com.cfh.examen3.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tyler Durden christian.fuentesh@elektra.com.mx
 * @proyect examen-e3
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
