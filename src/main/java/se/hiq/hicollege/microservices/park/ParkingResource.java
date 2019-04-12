package se.hiq.hicollege.microservices.park;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingResource {
    private static final Logger LOG = LoggerFactory.getLogger(ParkingResource.class);
    private URI parkingService;

    public ParkingResource(@Value("${parkingUrl}") URI parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public Map<String, Object> helloWorld() {
        return hello("");
    }

    @GetMapping(path = "/{regNr}")
    public Map<String, Object> hello(@PathVariable String regNr) {
        LOG.info("In to hello()");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Varsågodaaaa! Parkera din bil med registreringsnummer !" + regNr);
        response.put("parkingticket", "ParkingTicketFor[" + regNr + "]ValidTo" + LocalDateTime.now().plusHours(2));
        return response;
    }
}
