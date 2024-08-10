package io.aanagtalon.backend.event;

import io.aanagtalon.backend.entity.UserEntity;
import io.aanagtalon.backend.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {
    private UserEntity user;
    private EventType type;
    private Map<?, ?> data;
}
