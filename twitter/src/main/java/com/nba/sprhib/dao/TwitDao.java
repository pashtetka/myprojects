package com.nba.sprhib.dao;

import java.util.List;

import com.nba.sprhib.entity.TwitEntity;
import com.nba.sprhib.exceptions.NullResultException;

public interface TwitDao {
	
	List<TwitEntity> getAllTwits();

	List<TwitEntity> getByUserId(Long userId) throws NullResultException;
}
