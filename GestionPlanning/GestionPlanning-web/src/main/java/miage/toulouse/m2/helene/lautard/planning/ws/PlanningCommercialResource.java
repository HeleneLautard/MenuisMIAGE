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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Hélène
 */
@Path("planningCommercial")
@RequestScoped
public class PlanningCommercialResource {

    miage.toulouse.m2.helene.lautard.planning.exposition.ServicePlanningLocal servicePlanning = lookupServicePlanningLocal();

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of PlanningCommercialResource
     */
    public PlanningCommercialResource() {
    }

    /**
     * Retrieves representation of an instance of miage.toulouse.m2.helene.lautard.planning.ws.PlanningCommercialResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok(this.servicePlanning.findCreneauxDispoCom()).build();
    }

    private miage.toulouse.m2.helene.lautard.planning.exposition.ServicePlanningLocal lookupServicePlanningLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (miage.toulouse.m2.helene.lautard.planning.exposition.ServicePlanningLocal) c.lookup("java:global/GestionPlanning-ear/GestionPlanning-ejb-1.0/ServicePlanning!miage.toulouse.m2.helene.lautard.planning.exposition.ServicePlanningLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
