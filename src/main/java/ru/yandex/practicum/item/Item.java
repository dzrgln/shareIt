package ru.yandex.practicum.item;

import lombok.Data;
import ru.yandex.practicum.user.User;

@Data
public class Item {
    private String name;
    private String description;
    private User owner;
    private boolean available;
}
