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
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.toulouse.m2.helene.lautard.exposition.ServiceAffaireLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

/**
 * REST Web Service
 *
 * @author Hélène
 */
@Path("affaires/{idAffaire}/commande")
@RequestScoped
public class AffaireResource {

    ServiceAffaireLocal serviceAffaire = lookupServiceAffaireLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AffaireResource
     */
    public AffaireResource() {
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("idAffaire") String idAffaire) throws AffaireNotFoundException {
        try {
            int numAffaire = Integer.parseInt(idAffaire);
            return Response.ok(this.serviceAffaire.findAffaireByNum(numAffaire)).build();
        } catch (AffaireNotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    /**
     * PUT method for updating or creating an instance of AffaireResource -
     * Valider commande
     *
     * @param idAffaire resource URI parameter
     * @param content representation for the resource
     * @throws
     * miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException
     * @throws
     * miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("idAffaire") String idAffaire, String content) throws AffaireNotFoundException, WrongTotalAmountException {
        try {
            this.serviceAffaire.validerCommande(idAffaire, content);
            return Response.ok().build();
        } catch (AffaireNotFoundException | WrongTotalAmountException ex){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    /**
     * POST method for creating instance of AffaireRessource - renseinger une
     * commande
     *
     * @param idAffaire
     * @param content
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(@PathParam("idAffaire") String idAffaire, String content) {
        try {
            return Response.ok(this.serviceAffaire.renseignerCommande(content)).build();
        } catch (AffaireNotFoundException | WrongClientException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    /**
     * PUT method for updating or creating an instance of AffaireResource -
     * Réceptionner commande
     *
     * @param idAffaire resource URI parameter
     * @param idCommande ressource URL parameter
     * @param content representation for the resource
     * @throws
     * miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException
     * @throws
     * miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException
     */
    @PUT
    @Path("/{idCommande}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(@PathParam("idAffaire") String idAffaire, @PathParam("idAffaire") String idCommande, String content) throws AffaireNotFoundException, WrongTotalAmountException {
        this.serviceAffaire.validerCommande(idAffaire, content);
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
