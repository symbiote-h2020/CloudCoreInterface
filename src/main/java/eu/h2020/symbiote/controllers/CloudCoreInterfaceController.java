package eu.h2020.symbiote.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.h2020.symbiote.communication.RabbitManager;
import eu.h2020.symbiote.model.Resource;
import eu.h2020.symbiote.model.ResourceCreationResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST endpoints definition.
 */

@RestController
public class CloudCoreInterfaceController {
    private static final String URI_PREFIX = "/cloudCoreInterface/v1";

    public static Log log = LogFactory.getLog(CloudCoreInterfaceController.class);

    private final RabbitManager rabbitManager;

    @Autowired
    public CloudCoreInterfaceController(RabbitManager rabbitManager){
        this.rabbitManager = rabbitManager;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = URI_PREFIX + "/platforms/{platformId}/rdfResources")
    public ResponseEntity<?> getRdfResources(@PathVariable("platformId") String platformId) {
        return new ResponseEntity<String>("RDF Resources listing: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = URI_PREFIX + "/platforms/{platformId}/rdfResources")
    public ResponseEntity<?> createRdfResources(@PathVariable("platformId") String platformId,
                                                     @RequestBody String rdfResources) {
        return new ResponseEntity<String>("RDF Resource create: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = URI_PREFIX + "/platforms/{platformId}/rdfResources/{resourceId}")
    public ResponseEntity<?> getRdfResource(@PathVariable("platformId") String platformId,
                                                 @PathVariable("resourceId") String resourceId) {
        return new ResponseEntity<String>("RDF Resource listing: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = URI_PREFIX + "/platforms/{platformId}/rdfResources/{resourceId}")
    public ResponseEntity<?> modifyRdfResource(@PathVariable("platformId") String platformId,
                                                    @PathVariable("resourceId") String resourceId,
                                                    @RequestBody String rdfResources) {
        return new ResponseEntity<String>("RDF Resource modify: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = URI_PREFIX + "/platforms/{platformId}/rdfResources/{resourceId}")
    public ResponseEntity<?> deleteRdfResource(@PathVariable("platformId") String platformId,
                                                    @PathVariable("resourceId") String resourceId) {
        return new ResponseEntity<String>("RDF Resource delete: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = URI_PREFIX + "/platforms/{platformId}/resources")
    public ResponseEntity<?> getResources(@PathVariable("platformId") String platformId) {
        return new ResponseEntity<String>("Resources listing: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = URI_PREFIX + "/platforms/{platformId}/resources")
    public ResponseEntity<?> createResources(@PathVariable("platformId") String platformId,
                                                     @RequestBody Resource resource) {
        resource.setPlatformId(platformId);
        ResourceCreationResponse response = rabbitManager.sendResourceCreationRequest(resource);

        System.out.println(response);

        //Timeout or exception on our side
        if (response == null)
            return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);

        if (response.getStatus() != org.apache.http.HttpStatus.SC_OK)
            return new ResponseEntity<String>("{}", HttpStatus.valueOf(response.getStatus()));
        return new ResponseEntity<Resource>(response.getResource(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = URI_PREFIX + "/platforms/{platformId}/resources/{resourceId}")
    public ResponseEntity<?> getResource(@PathVariable("platformId") String platformId,
                                                 @PathVariable("resourceId") String resourceId) {
        return new ResponseEntity<String>("Resource listing: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = URI_PREFIX + "/platforms/{platformId}/resources/{resourceId}")
    public ResponseEntity<?> modifyResource(@PathVariable("platformId") String platformId,
                                                    @PathVariable("resourceId") String resourceId,
                                                    @RequestBody Resource resource) {
        return new ResponseEntity<String>("Resource modify: NYI", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = URI_PREFIX + "/platforms/{platformId}/resources/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable("platformId") String platformId,
                                                    @PathVariable("resourceId") String resourceId) {
        return new ResponseEntity<String>("Resource delete: NYI", HttpStatus.NOT_IMPLEMENTED);
    }


}