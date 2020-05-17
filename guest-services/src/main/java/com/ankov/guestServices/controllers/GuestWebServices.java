package com.ankov.guestServices.controllers;

import com.ankov.guestServices.model.Guest;
import com.ankov.guestServices.repositories.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guests")
@AllArgsConstructor
public class GuestWebServices {

    private final GuestRepository guestRepository;

    @GetMapping
    public Iterable<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id) {
        return guestRepository.findById(id).get();
    }

}
