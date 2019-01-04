package com.jitin.urlshortenerexample.controller;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jitin.urlshortenerexample.service.UrlShortenerService;

@RestController
public class UrlShortenerController {
	@Resource
	private UrlShortenerService urlShortenerService;

	@GetMapping("urlshortener/url/{urlId}")
	public String getUrl(@PathVariable String urlId) {
		String url = urlShortenerService.getUrl(urlId);
		if (null != url) {
			return url;
		} else {
			throw new RuntimeException("There is no url for url id : " + urlId);
		}
	}

	@PostMapping("urlshortener/url")
	public String create(@RequestBody String url) {
		String response = urlShortenerService.shortUrl(url);
		if (!StringUtils.isEmpty(response)) {
			return response;
		} else {
			throw new RuntimeException("Incorrect url : " + url);
		}
	}
}
