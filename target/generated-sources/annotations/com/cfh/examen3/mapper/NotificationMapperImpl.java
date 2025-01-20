package com.cfh.examen3.mapper;

import com.cfh.examen3.dto.NotificationDto;
import com.cfh.examen3.model.Notification;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T10:19:45-0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification mapDtoToModel(NotificationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setUserId( dto.getUserId() );
        notification.setTitle( dto.getTitle() );
        notification.setMessage( dto.getMessage() );
        notification.setCreationDate( dto.getCreationDate() );

        return notification;
    }

    @Override
    public NotificationDto mapModelToDto(Notification model) {
        if ( model == null ) {
            return null;
        }

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setUserId( model.getUserId() );
        notificationDto.setTitle( model.getTitle() );
        notificationDto.setMessage( model.getMessage() );
        notificationDto.setCreationDate( model.getCreationDate() );

        return notificationDto;
    }

    @Override
    public List<NotificationDto> mapListModelToDto(List<Notification> model) {
        if ( model == null ) {
            return null;
        }

        List<NotificationDto> list = new ArrayList<NotificationDto>( model.size() );
        for ( Notification notification : model ) {
            list.add( mapModelToDto( notification ) );
        }

        return list;
    }
}
