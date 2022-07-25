package ru.yandex.practicum.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.item.storage.ItemStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
@Slf4j
public class ItemController {
    private final ItemStorage itemStorage;

    public ItemController(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    @GetMapping
    public List<Item> getItems(@RequestHeader("X-Sharer-User-Id") int userId) {
        log.info("Получен GET-запрос к эндпоинту /items");
        return itemStorage.getItems(userId);
    }

    @GetMapping("/{itemId}")
    public Item getUserById(@PathVariable("itemId") int itemId, @RequestHeader("X-Sharer-User-Id") int userId){
        return itemStorage.getItemById(itemId, userId).get();
    }

    @PostMapping
    public Item create(@Valid @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") int userId) {
        log.info("Создан объект '{}'", itemDto);
        return itemStorage.create(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public Item update(@RequestHeader("X-Sharer-User-Id") int userId, @PathVariable("itemId") int itemId
            , @Validated @RequestBody ItemDto itemDto) {

        log.info("Обновлен объект '{}'", itemDto);
        return itemStorage.update(userId, itemId, itemDto);
    }

}
