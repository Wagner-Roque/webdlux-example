package com.apirest.webflux.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.apirest.webflux.webflux.document.PlayList;

public interface PlayListRepository extends ReactiveMongoRepository<PlayList, String> {

}
