package com.dish.roles.response;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ApiResponse<T> {
	private boolean status;
	private String message;
	private HttpStatus HTTPstatusCode;
	private String requestId;
	private T data;
	@Builder.Default
	private Long timeStamp = System.currentTimeMillis();

	public static void main(String[] args) {
	ApiResponse.builder().requestId("testid")

			.build();

	}
}

