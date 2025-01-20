package com.cfh.examen3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Tyler Durden christian.fuentesh@elektra.com.mx
 * @proyect examen-e3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto implements Serializable {
    private Long userId;
    private String title;
    private String message;
    private LocalDate creationDate;
    @Override
    public String toString(){
        return userId+" - "+title+ " - " + message + " - " + creationDate.toString();
    }
}
