package com.ankov.reservationServices;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationRepository repository;

    @GetMapping
    public Iterable<Reservation> getReservations(@RequestParam(name="date", required = false) LocalDate date) {
        if (null != date) {
            return repository.findAllByDate(date);
        }
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable long id) {
        return repository.findById(id).get();
    }

}
