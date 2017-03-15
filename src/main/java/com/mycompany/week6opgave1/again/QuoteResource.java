/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.week6opgave1.again;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Bruger
 */
@Path("quote")
public class QuoteResource {
     Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QuoteResource
     */
    public QuoteResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.week6opgave1.again.QuoteResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getQoute(@PathParam("id") int id) {
        String quote;
        quote = quotes.get(id);
        return quote;
    }

    @GET
    @Path("/random")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRandomQoute() {
        Random r = new Random();
        int random = r.nextInt(quotes.size() + 1);
        String qoute = quotes.get(random);
        return qoute;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addQoute(String jsonQuote) {
        JsonParser parser = new JsonParser();
        com.google.gson.JsonObject job = parser.parse(jsonQuote).getAsJsonObject();
        String quoteValue = job.get("quote").getAsString();
        JsonObject result = new JsonObject();
        result.addProperty("id", quotes.size() + 1);
        result.addProperty("quote", quoteValue);
        
        return Response
                .status(200)
                .entity(gson.toJson(result))
                .build();
    }

    /**
     * PUT method for updating or creating an instance of QuoteResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

  

}
