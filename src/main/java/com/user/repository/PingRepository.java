package com.user.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.user.repository.model.Ping;

public interface PingRepository extends PagingAndSortingRepository<Ping, Serializable> {

}
