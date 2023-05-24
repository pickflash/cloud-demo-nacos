package cn.itcast.order.web;

import cn.itcast.order.clients.UserClient;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

    @Autowired
    private UserClient userClient;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable Long orderId) {
        //1,根据id查询订单
        Order order=orderService.queryOrderById(orderId);

        //2,服务名称代替地址
        User user =userClient.findById(order.getUserId());
        //3，封装user到order中
        order.setUser(user);
        return order;
    }





   /*@Autowired
   private RestTemplate restTemplate;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        //1,根据id查询订单
        Order order=orderService.queryOrderById(orderId);

        //2,服务名称代替地址
        String url="http://userservice/user/"+order.getUserId();
                                                 //返回值类型
        User user = restTemplate.getForObject(url, User.class);
        //3，封装user到order中
        order.setUser(user);
        return order;
    }*/
}
