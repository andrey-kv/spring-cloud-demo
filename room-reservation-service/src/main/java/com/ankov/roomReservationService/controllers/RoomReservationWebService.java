package com.ankov.roomReservationService.controllers;

import com.ankov.roomReservationService.domain.Guest;
import com.ankov.roomReservationService.domain.Reservation;
import com.ankov.roomReservationService.domain.Room;
import com.ankov.roomReservationService.domain.RoomReservation;
import com.ankov.roomReservationService.feignclients.GuestClient;
import com.ankov.roomReservationService.feignclients.ReservationClient;
import com.ankov.roomReservationService.feignclients.RoomClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room-reservations")
@AllArgsConstructor
public class RoomReservationWebService {
    
    private final RoomClient roomClient;
    private final GuestClient guestClient;
    private final ReservationClient reservationClient;
    
    @GetMapping
    public List<RoomReservation> getRoomReservations(
            @RequestParam(name="date", required = false) String dateString) {

        List<Room> rooms = roomClient.getAllRooms();
        Map<Long, RoomReservation> roomReservations = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.put(room.getId(), roomReservation);
        });

        LocalDate date = null == dateString ? null : LocalDate.parse(dateString);
        List<Reservation> reservations = reservationClient.getAllReservations(date);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setDate(reservation.getDate());
            Guest guest = guestClient.getGuest(reservation.getGuestId());
            roomReservation.setGuestId(guest.getId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });

        return new ArrayList<>(roomReservations.values());
    }


}
