/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.toulouse.m2.helene.lautard.planning.exposition.ServicePlanningLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CalendrierNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommercialNotAvailableException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CreneauNotFoundException;

/**
 * REST Web Service
 *
 * @author Hélène
 */
@Path("commerciaux/{idCommercial}/creneaux")
@RequestScoped
public class CreneauxCommerciauxRessource {

    ServicePlanningLocal servicePlanning = lookupServicePlanningLocal();

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
    public Response getJson(@PathParam("idCommercial") String idCommercial) {
        int id = Integer.parseInt(idCommercial);
        return Response.ok(this.servicePlanning.findCreneauxCom(id)).build();
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
    public Response putJson(@PathParam("idCommercial") String idCommercial,@PathParam("idCreneau") String idCreneau,  String content) {
        int numCommercial = Integer.parseInt(idCommercial);
        int numCreneau = Integer.parseInt(idCreneau);
        
        try {
            return Response.ok(this.servicePlanning.bloquerCreneauCommercial(content, numCommercial, numCreneau)).build();
        } catch (CalendrierNotFoundException | CreneauNotFoundException | CommercialNotAvailableException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    private ServicePlanningLocal lookupServicePlanningLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ServicePlanningLocal) c.lookup("java:global/GestionPlanning-ear/GestionPlanning-ejb-1.0/ServicePlanning!miage.toulouse.m2.helene.lautard.planning.exposition.ServicePlanningLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
