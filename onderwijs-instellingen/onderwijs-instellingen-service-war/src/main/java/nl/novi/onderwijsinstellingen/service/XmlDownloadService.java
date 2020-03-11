package nl.novi.onderwijsinstellingen.service;

import java.util.Optional;

/**
 * Downloads the organisations from a external url as XML data
 */
public interface XmlDownloadService {
    /**
     * Download the given XML and return it as an Optional String.
     *
     * @return Optional Content.
     */
    Optional<String> download();
}
