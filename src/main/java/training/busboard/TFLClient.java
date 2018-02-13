package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class TFLClient {
    private Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    public List<ArrivalPrediction> getPredictions(String stopID) {
        return client.target("foo")
                .path("foo")
                .resolveTemplate("stopId", "foo")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {
                });
    }
}
