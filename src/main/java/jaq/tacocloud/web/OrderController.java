package jaq.tacocloud.web;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import jaq.tacocloud.User;
import jaq.tacocloud.data.OrderRepo;
import jaq.tacocloud.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepo orderRepo;

    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @GetMapping("/current")
    public String orderForm(Model model, @AuthenticationPrincipal User user) {
        Order order = (Order) model.getAttribute("order");
        if (order == null || order.getTacos().isEmpty()) {
            return "redirect:/design";
        }
        order.setUser(user);
        order.setDeliveryStreet(order.getDeliveryStreet());
        order.setDeliveryCity(order.getDeliveryCity());
        order.setDeliveryState(order.getDeliveryState());
        order.setDeliveryZip(order.getDeliveryZip());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors())
            return "orderForm";
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
