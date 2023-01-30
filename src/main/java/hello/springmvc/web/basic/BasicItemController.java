package hello.springmvc.web.basic;

import hello.springmvc.domain.item.Item;
import hello.springmvc.domain.item.ItemRepository;
import hello.springmvc.web.basic.param.ItemRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items =itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {

        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/items";
    }

    /**
     * @ModelAttribute("item") Item item -> @ModelAttribute Item item
     *
     * 위와 같이 생략가능한데 생략시 아래 룰을 따른다.
     *
     * default role
     * 객체명(Item)의 첫글자를 소문자로 바꾼것이 modelAttribute명으로해서 들어감
     *
     * ** @ModelAttribute자체도 생략가능하지만.. 권장하지않음
     */
    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 20));
        itemRepository.save(new Item("itemB", 25000, 10));
    }
}
