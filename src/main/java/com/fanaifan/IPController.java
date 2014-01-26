package com.fanaifan;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("/ip")
public class IPController {
	
	@Path("/get/{ip}.json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getIPArea(@PathParam("ip") String ip){
		JSONObject json = new JSONObject();
		json.put("ip", ip);
		json.put("area", IPSeeker.getInstance().getCountry(ip));
		return json.toString();
	}
	
	@Path("/post.json")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String postIPArea(IP ip){
		JSONObject json = new JSONObject();
		json.put("ip", ip.getIp());
		json.put("area", IPSeeker.getInstance().getCountry(ip.getIp()));
		return json.toString();
	}

}
