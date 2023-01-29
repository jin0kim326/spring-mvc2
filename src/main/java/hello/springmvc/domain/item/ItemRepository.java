package hello.springmvc.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    // 멀티 쓰레드 환경이기 때문에 HashMap, long 대신 ConcurrentHashMap, atomiclong등을 사용해야함
    private static final Map<Long, Item> store = new HashMap<>();   //static
    private static long sequence = 0L;  //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateItem) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateItem.getItemName());
        findItem.setPrice(updateItem.getPrice());
        findItem.setQuantity(updateItem.getQuantity());
        /**
         * 업데이트용 클래스를 따로 만드는게 더 나음
         * -> id를 사용하지 않음, 명확성x...
         */
    }

    public void clearStore() {
        store.clear();
    }
}
