package com.vincent.todolist.controller;

import com.vincent.todolist.annotation.I18n;
import com.vincent.todolist.model.entity.Merchant;
import com.vincent.todolist.model.entity.Shop;
import com.vincent.todolist.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zbw
 * @create 2018/4/3 16:23
 */
@I18n("dashboard")
@Controller
@RequestMapping("dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(Model model) {
        List<Merchant> merchants = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Merchant merchant = new Merchant();
            merchants.add(merchant);
        }
        List<Shop> shops = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Shop shop = new Shop();
            shops.add(shop);
        }
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            users.add(user);
        }
        model.addAttribute("merchants", merchants);
        model.addAttribute("shops", shops);
        model.addAttribute("users", users);
        return "system/dashboard";
    }

}
