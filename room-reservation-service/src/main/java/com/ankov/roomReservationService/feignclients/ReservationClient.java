package com.ankov.roomReservationService.feignclients;

import com.ankov.roomReservationService.domain.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient("reservationservices")
public interface ReservationClient {

    @GetMapping("/reservations")
    List<Reservation> getAllReservations(
            @RequestParam(name = "date", required = false) LocalDate date);

    @GetMapping("/reservations/{id}")
    Reservation getReservation(@PathVariable("id") long id);

}
