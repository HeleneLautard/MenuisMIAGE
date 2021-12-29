/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Hélène
 */
@Path("commerciaux/{idCommercial}/creneaux")
@RequestScoped
public class CreneauxCommerciauxRessource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PlanningCommercialRessource
     */
    public CreneauxCommerciauxRessource() {
    }

    /**
     * Retrieves representation of an instance of miage.toulouse.m2.helene.lautard.planning.ws.CreneauxCommerciauxRessource
     * @param idCommercial resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("idCommercial") String idCommercial) {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CreneauxCommerciauxRessource
     * @param idCommercial resource URI parameter
     * @param idCreneau
     * @param content representation for the resource
     */
    @PUT
    @Path("/{idCreneau}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(@PathParam("idCommercial") String idCommercial,@PathParam("idCreneau") String idCreneau,  String content) {
    }
}
