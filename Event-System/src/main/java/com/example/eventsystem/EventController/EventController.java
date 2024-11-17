package com.example.eventsystem.EventController;

import com.example.eventsystem.ApiResponse.ApiResponse;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-system")
public class EventController {
    ArrayList<Event> events=new ArrayList<Event>();


    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Successfully added event");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody Event event) {
        events.set(index, event);
        return new ApiResponse("Successfully updated event");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index) {
        events.remove(index);
        return new ApiResponse("Successfully deleted event");
    }

    @PutMapping("/change-capacity/{id}/{capacity}")
    public ApiResponse changeEventCapacity(@PathVariable int id, @PathVariable int capacity) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setCapacity(capacity);
                return new ApiResponse("Successfully change capacity of event");
            }
        }
        return new ApiResponse("Event not found");
    }

    @GetMapping("/search/{id}")
    public ApiResponse searchEvent(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id ){
                return new ApiResponse(""+event);
            }
        }
        return new ApiResponse("Event not found");
    }

}
