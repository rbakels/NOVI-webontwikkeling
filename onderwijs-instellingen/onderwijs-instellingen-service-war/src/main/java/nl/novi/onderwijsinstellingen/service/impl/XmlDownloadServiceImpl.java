package nl.novi.onderwijsinstellingen.service.impl;

import nl.novi.onderwijsinstellingen.service.XmlDownloadService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;

@ApplicationScoped
public class XmlDownloadServiceImpl implements XmlDownloadService {
    private static final Logger logger = LoggerFactory.getLogger(XmlDownloadServiceImpl.class);

    private OkHttpClient client;

    private String sourceUrl;

    public XmlDownloadServiceImpl() {
        this.client = new OkHttpClient();
        this.sourceUrl = "http://localhost:8080/onderwijsinstellingen.xml";
    }

    public XmlDownloadServiceImpl(String url) {
        this.client = new OkHttpClient();
        this.sourceUrl = url;
    }

    public Optional<String> download()
    {
        try {
            logger.info("Downloading sources from {}", this.sourceUrl);

            Request request = new Request.Builder()
                    .url(this.sourceUrl)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                ResponseBody responseBody = response.body();

                if (!response.isSuccessful()) {
                    String errorMessage = response.message();

                    logger.error("Downloading sources from {} failed. Reason: call to service returned a non-success response. HTTP Status: {}. HTTP Message: {}", this.sourceUrl, response.code(), errorMessage);

                    return Optional.empty();
                }

                if (responseBody == null) {
                    logger.error("Downloading sources from {} failed. Reason: no response body found.", this.sourceUrl);

                    return Optional.empty();
                }

                return Optional.of(responseBody.string());
            }
        } catch (IOException ioe) {
            logger.error("Downloading sources from {} failed. Reason: {}", this.sourceUrl, ioe.getMessage(), ioe);

            return Optional.empty();
        }
    }
}
