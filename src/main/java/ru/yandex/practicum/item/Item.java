package ru.yandex.practicum.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.yandex.practicum.request.ItemRequest;
import ru.yandex.practicum.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class Item {
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String description;
    private User owner;

    @NotNull
    private boolean available;
    private ItemRequest request;

    public Item(String name, String description, boolean available, ItemRequest request) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.request = request;
    }

    public Item(int id, String name, String description, boolean available, ItemRequest request) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.request = request;
    }

    public Item(String name, String description, boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }
}
