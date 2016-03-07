package br.com.drv.deliveryrouter.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.drv.deliveryrouter.entities.RouteMap;
import br.com.drv.deliveryrouter.entities.Vector;
import br.com.drv.deliveryrouter.repository.RouteDeliveryRepository;
import br.com.drv.deliveryrouter.repository.VectorRepository;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class RouterServiceImpl implements RouterService {

	
	@Autowired
    protected VectorRepository vectorRepo;
	
//	@Autowired
//    protected RouteDeliveryRepository vectorRepo;
	
	
	@Override
	public String createRouteMap(String mapName){
		
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		
		RouteMap map = new RouteMap();
		map.setName(mapName);
		
		Vector v = new Vector();
		v.setDistance(10L);
		v.setRouteMap(map);
		v.setPointA("A");
		v.setPointB("B");
		
		vectorRepo.save(v);
//		vectorRepo.save(map);
		
//		List<Match> list = matchRepository.findAll();
//		StringBuffer sb = new StringBuffer();
//		
//		for (Match mt : list) {
//			
//			sb.append(mt.toString());
//		}
//		
//		return sb.toString();
		
		return "JAX-WS + Spring!";
	}

}
