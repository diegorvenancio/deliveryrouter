package br.com.drv.deliveryrouter.pojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.drv.deliveryrouter.entities.Vector;

/**
 * Class that represents a point on the map
 * 
 * @author Diego Venancio
 *
 */
public class Point {

	// Name that identifies this point
	private String name;

	// list of vectors connected to this point
	private List<Vector> vectors = new ArrayList<>();

	// if this point was already checked by the algorithm
	private boolean checked = false;

	// indicates from which point this shortest distance came from
	private Point comingFrom;

	// shortest distance already calculated for this point
	private Long shortestDistance = Long.MAX_VALUE;

	public Point(String name, Vector v) {
		this.name = name;
		this.vectors.add(v);
	}

	public List<Vector> getVectors() {
		return vectors;
	}

	public void setVectors(List<Vector> vectors) {
		this.vectors = vectors;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Point getComingFrom() {
		return comingFrom;
	}

	public void setComingFrom(Point comingFrom) {
		this.comingFrom = comingFrom;
	}

	public Long getShortestDistance() {
		return shortestDistance;
	}

	public void setShortestDistance(Long shortestDistance) {
		this.shortestDistance = shortestDistance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
