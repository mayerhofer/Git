package com.infoGlobo.joinha.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.infoGlobo.joinha.models.Like;

public class LikeRepository implements IRepository<Like> {

	// TODO: THIS INITIALIZATION SHOULD BE REMOVED IN A PRODUCTION ENVIRONMENT.
	// TODO: A DATABASE SHOULD BE USED INSTEAD OF THIS STATIC "likesMap".
	static private Map<Integer, Like> likesMap;
	
	static {
		likesMap = new HashMap<Integer, Like>();
		
		Like l1 = new Like(1, 4, 1);
		Like l2 = new Like(2, 32, 2);
		Like l3 = new Like(3, 54, 7);
		
		likesMap.put(l1.getPostId(), l1);
		likesMap.put(l2.getPostId(), l2);
		likesMap.put(l3.getPostId(), l3);
	}
		
	@Override
	public void add(Like obj) {
		likesMap.put(obj.getPostId(), obj);
	}

	@Override
	public boolean remove(Like obj) {
		if (likesMap.containsKey(obj.getPostId()))
		{
			likesMap.remove(obj.getPostId());
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Like> getList() {
		return new ArrayList<Like>(likesMap.values());
	}

	@Override
	public Like GetById(int id) {
		if (likesMap.containsKey(id))
			return likesMap.get(id);
		return null;
	}

	@Override
	public void removeAll() {
		likesMap.clear();
	}

}
