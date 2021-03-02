package com.petclinic.api.onlineshopcontroller.online.data;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class Response {
	@Expose
	private String name;
	@Expose
	private String currency;
	@Expose
	private Integer id;
	@Expose
	private Integer amount;
	@Expose
	private Integer totalAmount;

	@Override
	public String toString() {
		return "Response{" +
				"name='" + name + '\'' +
				", currency='" + currency + '\'' +
				'}';
	}
}