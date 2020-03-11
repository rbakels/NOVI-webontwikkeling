package nl.novi.onderwijsinstellingen.service;

import nl.novi.onderwijsinstellingen.definition.domain.ZoekenOpType;
import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;

import java.util.List;
import java.util.Optional;

public interface DataService {
    /**
     * Retrieve all Instellingen. This converts the XML from the OAR system to
     * a Java POJO.
     *
     * @return Optional converted Java POJO.
     */
    Optional<Instellingen> retrieveInstellingen();

    /**
     * Clear the in-memory storage.
     */
    void clear();

    /**
     * Reset the in-memory storage.
     */
    void reset();

    /**
     * Initialize the in-memory storage.
     */
    void initialize();

    /**
     * Retrieve all known Instellingen.
     *
     * @return List of Instellingen.
     */
    List<Instellingen.Instelling> all();

    /**
     * Retrieve a Instelling by a given BRIN.
     *
     * @return Instelling.
     */
    Optional<Instellingen.Instelling> retrieveBy(String brin);

    /**
     * Search for a Instelling.
     *
     * @param type      Search type enumeration.
     * @param searchFor Search for the given string. If the string starts with the given parameter, then
     *                  it is returned. This is case insensitive.
     * @return List of found Instellingen.
     */
    List<Instellingen.Instelling> search(ZoekenOpType type, String searchFor);

    /**
     * Search for a Instelling in a given list of found Instellingen.
     *
     * @param type      Search type enumeration.
     * @param searchFor Search for the given string. If the string starts with the given parameter, then
     *                  it is returned. This is case insensitive.
     * @param searchIn  Search in the list of given Instellingen.
     * @return List of found Instellingen.
     */
    List<Instellingen.Instelling> search(ZoekenOpType type, String searchFor, List<Instellingen.Instelling> searchIn);
}
