package br.com.drv.deliveryrouter.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.drv.deliveryrouter.entities.RouteMap;

public interface RouteDeliveryRepository extends PagingAndSortingRepository<RouteMap, Long> {

    List<RouteMap> findAll();

    RouteMap findOne(Long id);

    RouteMap save(RouteMap saved);
    
    RouteMap findByName(String name);
}