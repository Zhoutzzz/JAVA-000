package com.week08.thursday;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.week08.thursday.repo.Order;
import com.week08.thursday.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ThursdayApplicationTests {

	@Autowired
	OrderRepo orderRepo;

	@Test
	void selectAll() {
		List<Order> orders = orderRepo.selectList(null);
		orders.forEach(System.out::println);
	}

	@Test
	void selectById() {
		Order orders = orderRepo.selectById(541916220971528193L);
		System.out.println(orders);
	}


	@Test
	void insert() {
		Order orders = new Order();
		orders.setBuyerId("new buyer");
		orders.setOrderStatus(10);
		int i = orderRepo.insert(orders);
		Assert.isTrue(i == 1, "新增失败");
	}

	@Test
	void update() {
		Order orders = orderRepo.selectById(1335822440678916098L);
		orders.setBuyerId("change this buyer");
		int i = orderRepo.updateById(orders);
		Assert.isTrue(i == 1, "修改失败");
	}

	@Test
	void delete() {
		int i = orderRepo.deleteById(1335822440678916098L);
		Assert.isTrue(i == 1, "删除失败");
	}

}
