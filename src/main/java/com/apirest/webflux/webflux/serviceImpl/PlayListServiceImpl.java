package com.apirest.webflux.webflux.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.webflux.webflux.document.PlayList;
import com.apirest.webflux.webflux.repository.PlayListRepository;
import com.apirest.webflux.webflux.services.PlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayListServiceImpl implements PlayListService {

	@Autowired
	private PlayListRepository listRepository;
	
	@Override
	public Flux<PlayList> findAll() {
		return listRepository.findAll();
	}

	@Override
	public Mono<PlayList> findById(String id) {
		return listRepository.findById(id);
	}

	@Override
	public Mono<PlayList> save(PlayList playList) {
		return listRepository.save(playList);
	}

}
