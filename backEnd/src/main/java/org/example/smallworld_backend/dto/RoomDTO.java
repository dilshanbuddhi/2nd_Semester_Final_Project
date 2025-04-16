
package org.example.smallworld_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RoomDTO {
    private Long id;
    private long hotel_id;
    private long room_type_Id;
    private String price;
    private String totalRooms;
    private String availableRooms;

    public RoomDTO(long hotel_id, long room_type_Id, String price, String totalRooms, String availableRooms) {
        this.hotel_id = hotel_id;
        this.room_type_Id = room_type_Id;
        this.price = price;
        this.totalRooms = totalRooms;
        this.availableRooms = availableRooms;
    }
}
