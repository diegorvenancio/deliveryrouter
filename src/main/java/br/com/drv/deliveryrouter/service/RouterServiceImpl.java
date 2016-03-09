package br.com.drv.deliveryrouter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.drv.deliveryrouter.entities.RouteMap;
import br.com.drv.deliveryrouter.entities.Vector;
import br.com.drv.deliveryrouter.exception.DeliveryRouterException;
import br.com.drv.deliveryrouter.pojo.Point;
import br.com.drv.deliveryrouter.repository.RouteDeliveryRepository;

@Service
public class RouterServiceImpl implements RouterService {

	@Autowired
	protected RouteDeliveryRepository routeRepo;

	@Override
	public void createRouteMap(String mapName, String vectors) {
		
		List<Vector> vectorList = new ArrayList<>();

		for (String vector : vectors.split("\r\n?|\n")) {

			String[] vectorData = vector.split(" ");

			if (!vector.trim().equals("") && vectorData.length == 3) {
				Vector v = new Vector();
				v.setPointA(vectorData[0]);
				v.setPointB(vectorData[1]);
				v.setDistance(new Long(vectorData[2]));
				vectorList.add(v);
			}
		}

		RouteMap rm = new RouteMap();
		rm.setName(mapName);
		rm.setVectors(vectorList);
		
		RouteMap map = routeRepo.findByName(mapName);
		if (map != null) {
			throw new DeliveryRouterException("Map name already exists!");
		}
		
		routeRepo.save(rm);
	}

	@Override
	public Point generateRoute(String mapName, String pointA, String pointB) {

		// fetch map and vectors form db
		RouteMap map = routeRepo.findByName(mapName);

		// create a set with distinct nodes
		Set<String> nodes = new HashSet<>();

		for (Vector v : map.getVectors()) {

			nodes.add(v.getPointA());
			nodes.add(v.getPointB());
		}

		// generate individual points with containing vectors inside
		Map<String, Point> pointsMap = generatePoints(map);

		getShortestRoute(pointsMap, pointA, pointB);

		return pointsMap.get(pointB);
	}

	/**
	 * Calculates the route using Dijkstra's algorithm
	 * 
	 * @param pointsMap
	 *            Map with vectors, points and distances
	 * @param pointA
	 *            point of departure
	 * @param pointB
	 *            point of destination
	 * @return List with ordered route of Points.
	 */
	private void getShortestRoute(Map<String, Point> pointsMap, String pointA, String pointB) {

		Point pA = pointsMap.get(pointA);

		if (pA == null) {
			throw new DeliveryRouterException("Destination point not found on map!");
		}

		// sets distance for destination
		pA.setShortestDistance(Long.valueOf(0));

		Point currentPoint = pA;

		// Check vectors for all points
		for (int ind = 0; ind < pointsMap.size(); ind++) {

			for (Vector v : currentPoint.getVectors()) {

				String neighborName = v.getNeighborOf(currentPoint.getName());
				Point neighbor = pointsMap.get(neighborName);

				// if neighbor point not visited yet, calculate distance
				if (!neighbor.isChecked()) {

					Long possibleNewDistance = currentPoint.getShortestDistance() + v.getDistance();

					// if distance is smaller, save it
					if (possibleNewDistance < neighbor.getShortestDistance()) {

						neighbor.setShortestDistance(possibleNewDistance);
						neighbor.setComingFrom(currentPoint);
					}
				}
			}

			currentPoint.setChecked(true);

			// get new shortest distance point on points not checked
			Optional<Point> opPoint = pointsMap.values().stream().filter(e -> !e.isChecked())
					.min((e1, e2) -> e1.getShortestDistance().compareTo(e2.getShortestDistance()));

			currentPoint = null;
			
			// gets the result from Optional object. Returns null after all were checked
			if (opPoint.isPresent()) {
				currentPoint = opPoint.get();
			}
		}
	}

	/**
	 * Method to generate the list of points from the Vectors
	 * 
	 * @param map
	 *            Map with vectors to generate points
	 * @return map with generated list of points
	 */
	private Map<String, Point> generatePoints(RouteMap map) {

		List<Vector> vectors = map.getVectors();

		Map<String, Point> result = new HashMap<>();

		for (Vector v : vectors) {

			Point pointA = result.get(v.getPointA());
			Point pointB = result.get(v.getPointB());

			// if point A is not on the map, add it with the vector, otherwise
			// add the vector to the point
			if (pointA != null) {
				pointA.getVectors().add(v);
			} else {
				pointA = new Point(v.getPointA(), v);
				result.put(v.getPointA(), pointA);
			}

			// if point B is not on the map, add it with the vector, otherwise
			// add the vector to the point
			if (pointB != null) {
				pointB.getVectors().add(v);
			} else {
				pointB = new Point(v.getPointB(), v);
				result.put(v.getPointB(), pointB);
			}

		}
		return result;
	}

}
