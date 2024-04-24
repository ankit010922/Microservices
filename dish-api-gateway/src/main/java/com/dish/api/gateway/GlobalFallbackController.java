package com.dish.api.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dish.api.gateway.util.APIResponseDTO;
import com.dish.api.gateway.util.Constants;


@RestController
public class GlobalFallbackController {

	@GetMapping("/fallback/{serviceName}/**")
	public ResponseEntity<APIResponseDTO> serviceFallbackGet(@PathVariable String serviceName) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(APIResponseDTO.builder()
				.message(serviceName +Constants.CURRENTLY_UNAVAILABLE).success(false)
				.timeStamp(System.currentTimeMillis()).build());
	}
	
	@PatchMapping("/fallback/{serviceName}/**")
	public ResponseEntity<APIResponseDTO> serviceFallbackPatch(@PathVariable String serviceName) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(APIResponseDTO.builder()
				.message(serviceName +Constants.CURRENTLY_UNAVAILABLE).success(false)
				.timeStamp(System.currentTimeMillis()).build());
	}
	
	@PostMapping("/fallback/{serviceName}/**")
	public ResponseEntity<APIResponseDTO> serviceFallbackPost(@PathVariable String serviceName) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(APIResponseDTO.builder()
				.message(serviceName +Constants.CURRENTLY_UNAVAILABLE).success(false)
				.timeStamp(System.currentTimeMillis()).build());
	}
	
	@PutMapping("/fallback/{serviceName}/**")
	public ResponseEntity<APIResponseDTO> serviceFallbackPut(@PathVariable String serviceName) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(APIResponseDTO.builder()
				.message(serviceName +Constants.CURRENTLY_UNAVAILABLE).success(false)
				.timeStamp(System.currentTimeMillis()).build());
	}
	
	@DeleteMapping("/fallback/{serviceName}/**")
	public ResponseEntity<APIResponseDTO> serviceFallbackDelete(@PathVariable String serviceName) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(APIResponseDTO.builder()
				.message(serviceName +Constants.CURRENTLY_UNAVAILABLE).success(false)
				.timeStamp(System.currentTimeMillis()).build());
	}
}
