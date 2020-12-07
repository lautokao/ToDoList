/**
 * @author Vincent Kao
 */
package com.vincent.todolist.controller;


import com.vincent.todolist.model.entity.Merchant;
import com.vincent.todolist.model.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Hello {
    @RequestMapping("/")
    public String sayhello() {
        return "Hello World";
    }

    @GetMapping("/hellos")
    public String hello(Model model) {
        model.addAttribute("hello", "Do Hello World");
        model.addAttribute("gender", "male");
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            list.add("This is ArrayList" + i);
        }
        model.addAttribute("list", list);

        List<Shop> listShop = new ArrayList<>();
        Merchant merchant1 = new Merchant();
        merchant1.setName("merchant1");
        Merchant merchant2 = new Merchant();
        merchant2.setName("merchant2");
        for (int i = 0; i < 5; i++) {
            Shop shop = new Shop();
            shop.setId(i);
            shop.setNumber(500 + i);
            if (i % 2 == 0) {
                shop.setMerchant(merchant1);
            } else {
                shop.setMerchant(merchant2);
            }
            shop.setName("shop" + i);
            shop.setCreate(new Date());
            listShop.add(shop);
        }
        model.addAttribute("listShop", listShop);

        Shop shop = new Shop();
        model.addAttribute("shop", shop);
        // 要導入的html
        return "hello";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Shop shop, Model model) {
        model.addAttribute("shop", shop);
        return "add";
    }
}
