package jaq.tacocloud.data;

import jaq.tacocloud.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {

}