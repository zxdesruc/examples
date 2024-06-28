package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {
    private static final String CRPT_API_URL = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    private final int requestLimit;
    private final long timeUnitMillis;
    private volatile long timestamp = System.currentTimeMillis();
    private final AtomicInteger currentConnectionCount = new AtomicInteger(0);

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.requestLimit = requestLimit;
        this.timeUnitMillis = timeUnit.getModifier();
    }

    public void createDocument(Document document) throws IOException, URISyntaxException, InterruptedException {
        String payload = convertDocumentToJson(document);
        sendPayload(payload);
    }

    private void sendPayload(String payload) throws URISyntaxException, IOException, InterruptedException {
        if (currentConnectionCount.incrementAndGet() <= requestLimit) {
            sendHttpRequest(payload);
        } else {
            while (currentConnectionCount.get() > requestLimit) {
                if (System.currentTimeMillis() - timestamp >= timeUnitMillis) {
                    synchronized (this) {
                        if (System.currentTimeMillis() - timestamp >= timeUnitMillis) {
                            timestamp = System.currentTimeMillis();
                            currentConnectionCount.set(0);
                        }
                    }
                }
            }
            sendPayload(payload);
        }
    }

    private void sendHttpRequest(String payload) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI(CRPT_API_URL))
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String convertDocumentToJson(Document document) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, document);
        return writer.toString();
    }

    @JsonAutoDetect
    public static record Document(
            @JsonProperty("description") Description description,
            @JsonProperty("doc_id") String id,
            @JsonProperty("doc_status") String status,
            @JsonProperty("doc_type") String type,
            @JsonProperty("importRequest") boolean importRequest,
            @JsonProperty("owner_inn") String ownerInn,
            @JsonProperty("participant_inn") String participantInn,
            @JsonProperty("producer_inn") String producerInn,
            @JsonProperty("production_date") 
            @JsonDeserialize(using = LocalDateDeserializer.class)
            @JsonSerialize(using = LocalDateSerializer.class)
            @JsonFormat(pattern = "yyyy-MM-dd") LocalDate productionDate,
            @JsonProperty("production_type") String productionType,
            @JsonProperty("products") List<Product> products,
            @JsonProperty("reg_date") String regDate,
            @JsonProperty("reg_number") String regNumber
    ) {
        @JsonAutoDetect
        public static record Description(
                @JsonProperty("participantInn") String participantInn
        ){}
    }

    @JsonAutoDetect
    public static record Product(
            @JsonProperty("certificate_document") String certificateDocument,
            @JsonProperty("certificate_date") 
            @JsonFormat(pattern="yyyy-MM-dd")
            @JsonDeserialize(using = LocalDateDeserializer.class)
            @JsonSerialize(using = LocalDateSerializer.class) LocalDate certificateDocumentDate,
            @JsonProperty("certificate_number") String certificateDocumentNumber,
            @JsonProperty("owner_inn") String ownerInn,
            @JsonProperty("producer_inn") String producerInn,
            @JsonProperty("production_date") String productionDate,
            @JsonProperty("tnved_code") String tnvedCode,
            @JsonProperty("uit_code") String uitCode,
            @JsonProperty("uitu_code") String uituCode
    ){}

    public static class TimeUnit {
        public static final TimeUnit SECOND = new TimeUnit(1000);
        public static final TimeUnit FIVE_SECONDS = new TimeUnit(5000);
        public static final TimeUnit MINUTE = new TimeUnit(60000);
        public static final TimeUnit HOUR = new TimeUnit(3600000);

        private final long modifier;

        private TimeUnit(long modifier) {
            this.modifier = modifier;
        }

        public long getModifier() {
            return modifier;
        }
    }
}
