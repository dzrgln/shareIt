package ru.yandex.practicum.item;

import lombok.Data;
import ru.yandex.practicum.request.ItemRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemDto {
    @NotEmpty
    private String name;
    @NotNull
    private String description;
    @NotEmpty
    private String available;
    private ItemRequest request;

    public ItemDto(String name, String description, String available, ItemRequest request) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.request = request;
    }
}
