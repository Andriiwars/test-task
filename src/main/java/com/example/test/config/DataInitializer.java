package com.example.test.config;

import com.example.test.model.Goods;
import com.example.test.model.Product;
import com.example.test.model.Role;
import com.example.test.model.User;
import com.example.test.service.GoodsService;
import com.example.test.service.ProductService;
import com.example.test.service.RoleService;
import com.example.test.service.UserService;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;
    private final GoodsService goodsService;

    public DataInitializer(UserService userService,
                           RoleService roleService,
                           ProductService productService,
                           GoodsService goodsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.productService = productService;
        this.goodsService = goodsService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.save(userRole);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setRole(adminRole);
        userService.save(admin);

        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("user");
        user.setRole(userRole);
        userService.save(user);

        Product iphone13 = new Product();
        iphone13.setName("iPhone 13 Pro");
        iphone13.setPrice(BigDecimal.valueOf(2000));
        productService.save(iphone13);

        Product iphone11 = new Product();
        iphone11.setName("iPhone 11");
        iphone11.setPrice(BigDecimal.valueOf(1000));
        productService.save(iphone11);

        Goods goods = new Goods();
        goods.setCount(15L);
        goods.setProduct(iphone13);
        goodsService.save(goods);

        Goods goods1 = new Goods();
        goods1.setCount(40L);
        goods1.setProduct(iphone11);
        goodsService.save(goods1);
    }
}
