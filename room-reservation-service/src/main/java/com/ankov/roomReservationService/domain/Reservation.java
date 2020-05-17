package com.ankov.roomReservationService.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Reservation {

    private long id;
    private long roomId;
    private long guestId;
    private LocalDate date;
}
