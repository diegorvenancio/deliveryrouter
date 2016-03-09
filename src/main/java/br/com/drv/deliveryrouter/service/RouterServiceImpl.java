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

	// @Autowired
	// protected VectorRepository vectorRepo;

	@Autowired
	protected RouteDeliveryRepository vectorRepo;

	@Override
	public void createRouteMap(RouteMap rm) {

		// Calendar calendar = Calendar.getInstance();
		// Date date = new Date();
		// calendar.setTime(date);

		// RouteMap map = new RouteMap();
		// map.setName(mapName);
		//
		// Vector v = new Vector();
		// v.setDistance(10L);
		// v.setPointA("A");
		// v.setPointB("B");
		//
		// Vector v2 = new Vector();
		// v2.setDistance(10L);
		// v2.setPointA("B");
		// v2.setPointB("C");
		//
		// map.getVectors().add(v);
		// map.getVectors().add(v2);

		// vectorRepo.save(v);

		vectorRepo.save(rm);

		// List<Match> list = matchRepository.findAll();
		// StringBuffer sb = new StringBuffer();
		//
		// for (Match mt : list) {
		//
		// sb.append(mt.toString());
		// }
		//
		// return sb.toString();

	}

}
