package com.ankov.roomReservationService.controllers;

import com.ankov.roomReservationService.domain.Room;
import com.ankov.roomReservationService.domain.RoomReservation;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room-reservations-ribbon")
@AllArgsConstructor
public class RoomReservationWebServiceRibbon {

    private final RestTemplate restTemplate;

    @GetMapping
    public List<RoomReservation> getRoomReservations() {
        List<Room> rooms = getAllRooms();
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

    private List<Room> getAllRooms() {
        // Тут ми використовуємо URL який підставляється з EUREKA
        ResponseEntity<List<Room>> roomResponse = restTemplate
                .exchange("http://ROOMSERVICES/rooms",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Room>>() {});
        return roomResponse.getBody();
    }
}
