package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class TFLClient {
    private Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    
}
