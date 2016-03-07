package br.com.drv.deliveryrouter.ws;

import org.springframework.web.bind.annotation.RestController;

import br.com.drv.deliveryrouter.service.RouterService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
//@Log4j
public class RouteMapController {

	@Autowired
	RouterService rs;
	
	//, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
	
    @RequestMapping(value = "/mapas", method = RequestMethod.POST)
    public String createRouteMap(String mapName, HttpServletResponse response) {
    	
//    	this.log.
    	
        return rs.createRouteMap(mapName);
    }

}