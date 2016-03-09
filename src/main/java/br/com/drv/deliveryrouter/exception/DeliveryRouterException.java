package br.com.drv.deliveryrouter.exception;

public class DeliveryRouterException extends RuntimeException {

	public DeliveryRouterException(String msg) {

		super(msg);
	}

	public DeliveryRouterException(String msg, Throwable cause) {

		super(msg, cause);
	}
}