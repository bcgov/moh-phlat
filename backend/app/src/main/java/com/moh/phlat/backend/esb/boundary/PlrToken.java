package com.moh.phlat.backend.esb.boundary;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

public class PlrToken {
	
	private Instant lastRequestTime = Instant.now();
	@Getter
	@Setter
	private String accessToken;
	@Getter
	@Setter
	private long expiry;
	@Getter
	@Setter
	private String refreshToken;
	@Getter
	@Setter
	private String error;
	@Getter
	@Setter
	private String errorDesc;
	
	public boolean isExpired() {
		long difference = Instant.now().getEpochSecond() - lastRequestTime.getEpochSecond();
		return difference >= expiry - 10;
	}
}
