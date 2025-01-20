package com.cfh.examen3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Tyler Durden christian.fuentesh@elektra.com.mx
 * @proyect examen-e3
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notificacion")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_usuario", nullable = false)
    private Long userId;
    @Column(name = "titulo", nullable = false)
    private String title;
    @Column(name = "mensaje", nullable = false)
    private String message;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate creationDate;
}
