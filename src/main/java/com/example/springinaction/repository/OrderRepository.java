package com.example.springinaction.repository;

import com.example.springinaction.dao.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
