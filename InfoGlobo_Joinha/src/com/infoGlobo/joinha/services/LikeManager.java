/**
 * 
 */
package com.infoGlobo.joinha.services;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infoGlobo.joinha.models.Like;

/**
 * @author Ricardo
 *
 */
public class LikeManager implements ILikeManager {

	private IRepository<Like> repository = null;
	
	public LikeManager(IRepository<Like> likeRepository) {
		repository = likeRepository;
	}
	
	@Override
	public String getLikes() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(repository.getList());
	}

	@Override
	public void incLike(String value) {
		boolean incLikes = false;
		if (!NumberUtils.isNumber(value))
			return;
		int id = Integer.parseInt(value);
		int postId = 0;
		
		if (id % 2 == 1)
		{
			postId = (id - 1) / 2;
		}
		else
		{
			incLikes = true;
			postId = id / 2;
		}
		
		Like refLike = repository.GetById(postId);
		if (refLike != null)
			if (incLikes)
				refLike.incLike();
			else
				refLike.incDislike();
	}

}
