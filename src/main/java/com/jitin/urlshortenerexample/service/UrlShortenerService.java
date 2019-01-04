package com.jitin.urlshortenerexample.service;

import java.nio.charset.StandardCharsets;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

@Service("urlShortenerService")
public class UrlShortenerService {
	@Autowired
	private StringRedisTemplate redisTemplate;

	public String getUrl(String urlId) {
		return redisTemplate.opsForValue().get(urlId);
	}

	public String shortUrl(String url) {
		UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });
		String result = "";
		if (urlValidator.isValid(url)) {
			String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
			redisTemplate.opsForValue().set(id, url);
			result = "Operation was successful, id for given url is : " + id;
		}
		return result;
	}
}
