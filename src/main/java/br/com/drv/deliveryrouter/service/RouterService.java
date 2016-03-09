package br.com.drv.deliveryrouter.service;

import br.com.drv.deliveryrouter.pojo.Point;

public interface RouterService {

	void createRouteMap(String mapName, String vectors);

	Point generateRoute(String mapName, String pointA, String pointB);

}
