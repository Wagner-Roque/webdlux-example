package com.apirest.webflux.webflux.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.webflux.document.PlayList;
import com.apirest.webflux.webflux.services.PlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PlayListController {

	@Autowired
	private PlayListService listService;
	
	@GetMapping("/playlist")
	public Flux<PlayList> getPlayList(){
		return listService.findAll();
	}
	
	@GetMapping("/playlist/{id}")
	public Mono<PlayList> getPlayOne(@PathVariable String id){
		return listService.findById(id);
	}
	
	@PostMapping("/playlist")
	public Mono<PlayList> savePlayList(@RequestBody PlayList playList){
		return listService.save(playList);
	}
	
	@GetMapping(value="/playlist/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, PlayList>> getPlaylistByWebflux(){

		System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<PlayList> playlistFlux = listService.findAll();

        return Flux.zip(interval, playlistFlux);
        
	}
//Streams de eventos
	@GetMapping(value="/playlist/mvc")
	public List<String> getPlaylistByMvc() throws InterruptedException {

		System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());


		List<String> playlistList = new ArrayList<>();
		playlistList.add("Java 8");
		playlistList.add("Spring Security");
		playlistList.add("Github");
		playlistList.add("Deploy de uma aplicação java no IBM Cloud");
		playlistList.add("Bean no Spring Framework");
		TimeUnit.SECONDS.sleep(15);

		return playlistList;

	}
}
