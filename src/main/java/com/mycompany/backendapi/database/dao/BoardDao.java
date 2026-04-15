package com.mycompany.backendapi.database.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backendapi.database.entity.Board;

@Mapper
public interface BoardDao {
	public int insert(Board board);
	public int update(Board board);
	public int delete(int bno);
	public Board selectByBno(int bno);
}
