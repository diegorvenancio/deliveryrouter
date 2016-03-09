package br.com.drv.deliveryrouter.ws;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.drv.deliveryrouter.entities.RouteMap;
import br.com.drv.deliveryrouter.entities.Vector;
import br.com.drv.deliveryrouter.pojo.Point;
import br.com.drv.deliveryrouter.service.RouterService;

/**
 * Class to respond to web services for map creating and route generation
 * 
 * @author Diego Venancio
 *
 */
@RestController
public class RouteMapController {

	@Autowired
	RouterService routerService;

	/**
	 * Responds to map creation, adding it to the db.
	 * 
	 * @param mapName
	 *            name of the map
	 * @param vectors
	 *            distances from point to point
	 * @param response
	 *            HTTP response object
	 * @return a message of success or failure
	 */
	@RequestMapping(value = "/maps", method = RequestMethod.POST)
	public String createRouteMap(@RequestParam(required = true) String mapName, @RequestBody String vectors,
			HttpServletResponse response) {
//
//		List<Vector> vectorList = new ArrayList<>();
//
//		for (String vector : vectors.split("\r\n?|\n")) {
//
//			String[] vectorData = vector.split(" ");
//
//			if (!vector.trim().equals("") && vectorData.length == 3) {
//				Vector v = new Vector();
//				v.setPointA(vectorData[0]);
//				v.setPointB(vectorData[1]);
//				v.setDistance(new Long(vectorData[2]));
//				vectorList.add(v);
//			}
//		}
//
//		RouteMap rm = new RouteMap();
//		rm.setName(mapName);
//		rm.setVectors(vectorList);

		routerService.createRouteMap(mapName, vectors);

		// set response code as CREATED
		response.setStatus(HttpServletResponse.SC_CREATED);

		return String.format("Map %s Created!", mapName);
	}

	/**
	 * Gets the route between two points on a map
	 * 
	 * @param mapName
	 *            name that identifies the map
	 * @param pointA
	 *            starting point
	 * @param pointB
	 *            destination point
	 * @param response
	 *            HTTP response object
	 * @return error message or the route
	 */
	@RequestMapping(value = "/route", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String getRoute(@RequestParam(required = true) String mapName, @RequestParam(required = true) String pointA,
			@RequestParam(required = true) String pointB, @RequestParam(required = true) Float kmPerLitter,
			@RequestParam(required = true) Float fuelCost, HttpServletResponse response) {

		Point pB = routerService.generateRoute(mapName, pointA, pointB);

		long distance = pB.getShortestDistance();

		StringBuilder sb = new StringBuilder();

		// builds route string
		Point currentPoint = pB;
		sb.append(String.format("%s", pB.getName()));

		while (!currentPoint.getName().equals(pointA)) {
			currentPoint = currentPoint.getComingFrom();
			sb.insert(0, String.format("%s -> ", currentPoint.getName()));
		}

		float totalCost = distance * fuelCost / kmPerLitter;

		// includes distance on string
		sb.append(String.format("  total cost: %.2f", totalCost));

		return sb.toString();
	}
}