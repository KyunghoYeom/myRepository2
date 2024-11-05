package mypractice.forproject.service;

import lombok.RequiredArgsConstructor;
import mypractice.forproject.domain.item.Item;
import mypractice.forproject.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long id, String name){
        Item item = itemRepository.findById(id).get();
        item.changeItemName(name);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }



}
