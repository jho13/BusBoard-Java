package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class PostcodeClient {
    private static final String API_URL = "https://api.postcodes.io";
    private static final String COORDINATE_PATH = "postcodes/{postcode}";

    private Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    public Coordinate getCoordinate(String postcode) {
        return client.target(API_URL)
                .path(COORDINATE_PATH)
                .resolveTemplate("postcode", postcode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<Coordinate>() {});
    }
}
