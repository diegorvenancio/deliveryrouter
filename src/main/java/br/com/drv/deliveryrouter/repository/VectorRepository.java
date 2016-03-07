package br.com.drv.deliveryrouter.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.drv.deliveryrouter.entities.RouteMap;
import br.com.drv.deliveryrouter.entities.Vector;

public interface VectorRepository extends PagingAndSortingRepository<Vector, Long> {

    List<Vector> findAll();

    Vector findOne(Long id);

    Vector save(Vector saved);
}