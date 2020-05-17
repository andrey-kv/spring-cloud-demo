package com.ankov.roomReservationService.controllers;

import com.ankov.roomReservationService.domain.Room;
import com.ankov.roomReservationService.domain.RoomReservation;
import com.ankov.roomReservationService.feignclients.RoomClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


// Клас робить те саме що і RoomReservationWebServiceRibbon, але використовує OpenFeign контроллер
// RoomClient для запиту до іншого сервіса ("roomservices"). Цей сервіс має бути
// зареєстрований в Eureka
@RestController
@RequestMapping("/room-reservations-feign")
@AllArgsConstructor
public class RoomReservationWebServiceOpenFeign {

    private final RoomClient roomClient;

    @GetMapping
    public List<RoomReservation> getRoomReservations() {
        List<Room> rooms = roomClient.getAllRooms();
        List<RoomReservation> roomReservations = new ArrayList<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomId(room.getId());
            roomReservations.add(roomReservation);
        });
        return roomReservations;
    }
}
