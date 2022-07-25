package ru.yandex.practicum.item.storage;

import ru.yandex.practicum.item.Item;
import ru.yandex.practicum.item.ItemDto;

import java.util.List;
import java.util.Optional;

public interface ItemStorage {
    List<Item> getItems(int userId);

    Optional<Item> getItemById(Integer itemId, Integer userId);

    Item create(int userId, ItemDto itemDto);

    Item update (int userId, int itemId, ItemDto itemDto);
}
