package br.com.drv.deliveryrouter.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@SequenceGenerator(name = "SEQ_DIST", sequenceName = "SEQ_DIST", allocationSize = 1)
public class Vector {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIST")
	private Long id;

	@NonNull
	private Long distance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getPointA() {
		return pointA;
	}

	public void setPointA(String pointA) {
		this.pointA = pointA;
	}

	public String getPointB() {
		return pointB;
	}

	public void setPointB(String pointB) {
		this.pointB = pointB;
	}

	public RouteMap getRouteMap() {
		return routeMap;
	}

	public void setRouteMap(RouteMap routeMap) {
		this.routeMap = routeMap;
	}

	private String pointA;
	
	private String pointB;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="map_id")
	private RouteMap routeMap;
	


}