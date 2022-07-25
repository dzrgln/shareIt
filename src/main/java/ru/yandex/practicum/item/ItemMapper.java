package ru.yandex.practicum.item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getName(),
                item.getDescription(),
                String.valueOf(item.isAvailable()),
                item.getRequest() != null ? item.getRequest() : null
        );
    }

    public static Item updateItem(ItemDto itemDto, Item item) {
        return new Item(
                item.getId(),
                itemDto.getName() != null ? itemDto.getName() : item.getName(),
                itemDto.getDescription() != null ? itemDto.getDescription() : item.getDescription(),
                itemDto.getAvailable() != null ? Boolean.parseBoolean(itemDto.getAvailable()) : item.isAvailable(),
                itemDto.getRequest() != null ? itemDto.getRequest() : item.getRequest()
        );
    }

    public static Item toItem (ItemDto itemDto){
        return new Item(
                0,
                itemDto.getName(),
                itemDto.getDescription(),
                Boolean.parseBoolean(itemDto.getAvailable()),
                itemDto.getRequest()
        );
    }
}
