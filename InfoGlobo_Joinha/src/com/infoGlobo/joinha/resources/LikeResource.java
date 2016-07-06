package com.infoGlobo.joinha.resources;

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
import com.infoGlobo.joinha.services.LikeManager;
import com.infoGlobo.joinha.services.LikeRepository;

import org.apache.commons.lang3.math.NumberUtils;

@Path("/joinha")
public class LikeResource {
	
	static private LikeManager manager;
	
	static {
		manager = new LikeManager(new LikeRepository());
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getLikes()
	{
		return manager.getLikes();
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void incLike(String value)
	{
		manager.incLike(value);
	} 
}
