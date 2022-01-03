/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.affaires.ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.toulouse.m2.helene.lautard.exposition.ServiceAffaireLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException;

/**
 * REST Web Service
 *
 * @author Hélène
 */
@Path("affaires")
@RequestScoped
public class AffairesResource {

    ServiceAffaireLocal serviceAffaire = lookupServiceAffaireLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AffairesResource
     */
    public AffairesResource() {
    }

    /**
     * Retrieves representation of an instance of miage.toulouse.m2.helene.lautard.affaires.ws.AffairesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AffairesResource
     * @param content representation for the resource
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String content) {
        try {
            return Response.ok(this.serviceAffaire.creerAffaire(content)).build();
        } catch (ClientNotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    private ServiceAffaireLocal lookupServiceAffaireLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ServiceAffaireLocal) c.lookup("java:global/GestionAffaire-ear/GestionAffaire-ejb-1.0/ServiceAffaire!miage.toulouse.m2.helene.lautard.exposition.ServiceAffaireLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
