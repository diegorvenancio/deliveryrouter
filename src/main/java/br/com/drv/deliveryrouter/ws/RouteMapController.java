package br.com.drv.deliveryrouter.ws;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.drv.deliveryrouter.entities.RouteMap;
import br.com.drv.deliveryrouter.entities.Vector;
import br.com.drv.deliveryrouter.service.RouterService;

@RestController
// @Log4j
public class RouteMapController {

	@Autowired
	RouterService rs;

	@RequestMapping(value = "/mapas", method = RequestMethod.POST)
	public String createRouteMap(String mapName, @RequestBody String vectors, HttpServletResponse response) {

		List<Vector> vectorList = new ArrayList<>();

		for (String vector : vectors.split("\r\n?|\n")) {

			String[] vectorData = vector.split(" ");
			Vector v = new Vector();
			v.setPointA(vectorData[0]);
			v.setPointB(vectorData[1]);
			v.setDistance(new Long(vectorData[2]));
			vectorList.add(v);
		}

		RouteMap rm = new RouteMap();
		rm.setName(mapName);
		rm.setVectors(vectorList);

		rs.createRouteMap(rm);

		// set response code as CREATED
		response.setStatus(HttpServletResponse.SC_CREATED);
		
		return String.format("Map %s Created!", rm.getName());
	}

}