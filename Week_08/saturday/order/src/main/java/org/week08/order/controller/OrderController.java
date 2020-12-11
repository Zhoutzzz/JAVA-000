
package org.week08.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.week08.order.service.OrderService;

import java.math.BigDecimal;

/**
 * @author xiaoyu
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @PostMapping(value = "/orderPay")
    public String orderPay(@RequestParam(value = "count") Integer count,
                           @RequestParam(value = "amount") BigDecimal amount) {
        final long start = System.currentTimeMillis();
        orderService.orderPay(count, amount);
        System.out.println("消耗时间为:" + (System.currentTimeMillis() - start));
        return "";
    }
    @PostMapping(value = "/orderPayTAC")
    public String orderPayTAC(@RequestParam(value = "count") Integer count,
                           @RequestParam(value = "amount") BigDecimal amount) {
        final long start = System.currentTimeMillis();
        orderService.saveOrderForTAC(count, amount);
        System.out.println("消耗时间为:" + (System.currentTimeMillis() - start));
        return "";
    }

    @PostMapping(value = "/testOrderPay")
    public String testOrderPay(@RequestParam(value = "count") Integer count,
                               @RequestParam(value = "amount") BigDecimal amount) {
        final long start = System.currentTimeMillis();
        orderService.testOrderPay(count, amount);
        System.out.println("消耗时间为:" + (System.currentTimeMillis() - start));
        return "";
    }

    @PostMapping(value = "/mockInventoryWithTryException")
    public String mockInventoryWithTryException(@RequestParam(value = "count") Integer count,
                                                @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockInventoryWithTryException(count, amount);
    }
    
    @PostMapping(value = "/mockTacInventoryWithTryException")
    public String mockTacInventoryWithTryException(@RequestParam(value = "count") Integer count,
                                                @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockTacInventoryWithTryException(count, amount);
    }

    @PostMapping(value = "/mockInventoryWithTryTimeout")
    public String mockInventoryWithTryTimeout(@RequestParam(value = "count") Integer count,
                                              @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockInventoryWithTryTimeout(count, amount);
    }
    
    @PostMapping(value = "/mockAccountWithTryException")
    public String mockAccountWithTryException(@RequestParam(value = "count") Integer count,
                                                @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockAccountWithTryException(count, amount);
    }
    
    @PostMapping(value = "/mockAccountWithTryTimeout")
    public String mockAccountWithTryTimeout(@RequestParam(value = "count") Integer count,
                                              @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockAccountWithTryTimeout(count, amount);
    }

    @PostMapping(value = "/orderPayWithNested")
    public String orderPayWithNested(@RequestParam(value = "count") Integer count,
                                     @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.orderPayWithNested(count, amount);
    }
    
    @PostMapping(value = "/orderPayWithNestedException")
    public String orderPayWithNestedException(@RequestParam(value = "count") Integer count,
                                     @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.orderPayWithNestedException(count, amount);
    }

    @PostMapping(value = "/mockInventoryWithConfirmTimeout")
    public String mockInventoryWithConfirmTimeout(@RequestParam(value = "count") Integer count,
                                              @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockInventoryWithConfirmTimeout(count,amount);
    }
}
