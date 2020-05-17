package com.ankov.roomReservationService.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {

    private long id;
    private String roomName;
    private String roomNumber;
    private String bedInfo;

}
