package com.infoGlobo.joinha.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import com.infoGlobo.joinha.models.*;

import org.apache.commons.lang3.math.NumberUtils;

@Path("/joinha")
public class LikeResource {
	
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
	
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getLikes()
	{
		ArrayList<Like> list = new ArrayList<Like>(likesMap.values());
		Gson gson = new GsonBuilder().create();
		return gson.toJson(list);
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void incLike(String value)
	{
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
		
		Like refLike = likesMap.get(postId);
		if (refLike != null)
			if (incLikes)
				refLike.incLike();
			else
				refLike.incDislike();
	} 
}
