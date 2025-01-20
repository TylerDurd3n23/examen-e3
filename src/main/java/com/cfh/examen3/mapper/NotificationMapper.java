package com.cfh.examen3.mapper;

import com.cfh.examen3.dto.NotificationDto;
import com.cfh.examen3.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Tyler Durden christian.fuentesh@elektra.com.mx
 * @proyect examen-e3
 */
@Mapper
public interface NotificationMapper {
    NotificationMapper MAPPER = Mappers.getMapper(NotificationMapper.class);

    Notification mapDtoToModel(NotificationDto dto);
    NotificationDto mapModelToDto(Notification model);
    List<NotificationDto> mapListModelToDto(List<Notification> model);
}
