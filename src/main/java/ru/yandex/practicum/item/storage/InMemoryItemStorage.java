package ru.yandex.practicum.item.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.exceptions.ObjectNotFoundException;
import ru.yandex.practicum.exceptions.ValidationException;
import ru.yandex.practicum.item.Item;
import ru.yandex.practicum.item.ItemDto;
import ru.yandex.practicum.item.ItemMapper;
import ru.yandex.practicum.user.User;
import ru.yandex.practicum.user.storage.UserStorage;

import java.util.*;

@Component
public class InMemoryItemStorage implements ItemStorage {
    private Map<Integer, Item> items = new HashMap<>();
    private final UserStorage userStorage;
    private int id = 0;

    public InMemoryItemStorage(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public List<Item> getItems(int userId) {
        List<Item> itemList = new ArrayList<>();
        for (Item item : items.values()) {
            if (isOwner(item.getId(), userId)) {
                itemList.add(item);
            }
        }
        return itemList;
    }

    @Override
    public Optional<Item> getItemById(Integer itemId, Integer userId) {
        Optional<Item> optionalItem;
        if (items.containsKey(id)) {
            Item item = items.get(id);
            optionalItem = Optional.of(item);
        } else {
            throw new ObjectNotFoundException(String.format("Вещи с id \"%s\"не существует.", id));
        }
        return optionalItem;
    }

    @Override
    public Item create(int userid, ItemDto itemDto) {
        if (userid == 0) {
            throw new ValidationException("Необходимо ввести непустой id");
        }
        Item item = ItemMapper.toItem(itemDto);
        id++;
        item.setId(id);
        item.setOwner(userStorage.getUserById(userid).get());
        items.put(id, item);
        return item;
    }

    public boolean isOwner(Integer itemId, Integer userId) {
        User owner;
        User requester;
        if (userStorage.getUserById(userId).isPresent()) {
            owner = items.get(itemId).getOwner();
            requester = userStorage.getUserById(userId).get();
        } else {
            throw new ObjectNotFoundException(String.format("Пользователя с id \"%s\"не существует.", userId));
        }
        return owner.getId() == requester.getId();
    }

    @Override
    public Item update(int userId, int itemId, ItemDto itemDto) {
        Item item;
        if (items.containsKey(itemId)) {
            if (userStorage.getUserById(userId).isPresent()) {
                if (userStorage.getUserById(userId).get().getId() == items.get(itemId).getOwner().getId()) {
                    item = ItemMapper.updateItem(itemDto, items.get(itemId));
                    item.setId(itemId);
                    items.put(itemId, item);
                } else {
                    throw new ValidationException(String.format("Пользователь с id \"%s\"не владелец вещи.", userId));
                }
            } else {
                throw new ValidationException(String.format("Пользователь с id \"%s\"не существует.", userId));
            }
        } else {
            throw new ObjectNotFoundException(String.format("Вещи с id \"%s\"не существует.", itemId));
        }
        return item;
    }
}
