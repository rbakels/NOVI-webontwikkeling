
package nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="instelling" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SOORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PROVINCIE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BRI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="INSTELLINGSNAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STRAATNAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="HUISNUMMER_TOEVOEGING" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="POSTCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PLAATSNAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GEMEENTENUMMER" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="GEMEENTENAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DENOMINATIE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TELEFOONNUMMER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="INTERNETADRES" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "instelling"
})
@XmlRootElement(name = "instellingen")
public class Instellingen {

    protected List<Instellingen.Instelling> instelling;

    /**
     * Gets the value of the instelling property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instelling property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstelling().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Instellingen.Instelling }
     * 
     * 
     */
    public List<Instellingen.Instelling> getInstelling() {
        if (instelling == null) {
            instelling = new ArrayList<Instellingen.Instelling>();
        }
        return this.instelling;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SOORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PROVINCIE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BRI" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="INSTELLINGSNAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STRAATNAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="HUISNUMMER_TOEVOEGING" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="POSTCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PLAATSNAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GEMEENTENUMMER" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *         &lt;element name="GEMEENTENAAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DENOMINATIE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TELEFOONNUMMER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="INTERNETADRES" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "soort",
        "provincie",
        "bri",
        "instellingsnaam",
        "straatnaam",
        "huisnummertoevoeging",
        "postcode",
        "plaatsnaam",
        "gemeentenummer",
        "gemeentenaam",
        "denominatie",
        "telefoonnummer",
        "internetadres"
    })
    public static class Instelling {

        @XmlElement(name = "SOORT", required = true)
        protected String soort;
        @XmlElement(name = "PROVINCIE", required = true)
        protected String provincie;
        @XmlElement(name = "BRI", required = true)
        protected String bri;
        @XmlElement(name = "INSTELLINGSNAAM", required = true)
        protected String instellingsnaam;
        @XmlElement(name = "STRAATNAAM", required = true)
        protected String straatnaam;
        @XmlElement(name = "HUISNUMMER_TOEVOEGING", required = true)
        protected String huisnummertoevoeging;
        @XmlElement(name = "POSTCODE", required = true)
        protected String postcode;
        @XmlElement(name = "PLAATSNAAM", required = true)
        protected String plaatsnaam;
        @XmlElement(name = "GEMEENTENUMMER")
        protected short gemeentenummer;
        @XmlElement(name = "GEMEENTENAAM", required = true)
        protected String gemeentenaam;
        @XmlElement(name = "DENOMINATIE", required = true)
        protected String denominatie;
        @XmlElement(name = "TELEFOONNUMMER", required = true)
        protected String telefoonnummer;
        @XmlElement(name = "INTERNETADRES", required = true)
        protected String internetadres;

        /**
         * Gets the value of the soort property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSOORT() {
            return soort;
        }

        /**
         * Sets the value of the soort property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSOORT(String value) {
            this.soort = value;
        }

        /**
         * Gets the value of the provincie property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPROVINCIE() {
            return provincie;
        }

        /**
         * Sets the value of the provincie property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPROVINCIE(String value) {
            this.provincie = value;
        }

        /**
         * Gets the value of the bri property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBRI() {
            return bri;
        }

        /**
         * Sets the value of the bri property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBRI(String value) {
            this.bri = value;
        }

        /**
         * Gets the value of the instellingsnaam property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getINSTELLINGSNAAM() {
            return instellingsnaam;
        }

        /**
         * Sets the value of the instellingsnaam property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setINSTELLINGSNAAM(String value) {
            this.instellingsnaam = value;
        }

        /**
         * Gets the value of the straatnaam property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTRAATNAAM() {
            return straatnaam;
        }

        /**
         * Sets the value of the straatnaam property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTRAATNAAM(String value) {
            this.straatnaam = value;
        }

        /**
         * Gets the value of the huisnummertoevoeging property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHUISNUMMERTOEVOEGING() {
            return huisnummertoevoeging;
        }

        /**
         * Sets the value of the huisnummertoevoeging property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHUISNUMMERTOEVOEGING(String value) {
            this.huisnummertoevoeging = value;
        }

        /**
         * Gets the value of the postcode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOSTCODE() {
            return postcode;
        }

        /**
         * Sets the value of the postcode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOSTCODE(String value) {
            this.postcode = value;
        }

        /**
         * Gets the value of the plaatsnaam property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPLAATSNAAM() {
            return plaatsnaam;
        }

        /**
         * Sets the value of the plaatsnaam property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPLAATSNAAM(String value) {
            this.plaatsnaam = value;
        }

        /**
         * Gets the value of the gemeentenummer property.
         * 
         */
        public short getGEMEENTENUMMER() {
            return gemeentenummer;
        }

        /**
         * Sets the value of the gemeentenummer property.
         * 
         */
        public void setGEMEENTENUMMER(short value) {
            this.gemeentenummer = value;
        }

        /**
         * Gets the value of the gemeentenaam property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGEMEENTENAAM() {
            return gemeentenaam;
        }

        /**
         * Sets the value of the gemeentenaam property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGEMEENTENAAM(String value) {
            this.gemeentenaam = value;
        }

        /**
         * Gets the value of the denominatie property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDENOMINATIE() {
            return denominatie;
        }

        /**
         * Sets the value of the denominatie property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDENOMINATIE(String value) {
            this.denominatie = value;
        }

        /**
         * Gets the value of the telefoonnummer property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTELEFOONNUMMER() {
            return telefoonnummer;
        }

        /**
         * Sets the value of the telefoonnummer property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTELEFOONNUMMER(String value) {
            this.telefoonnummer = value;
        }

        /**
         * Gets the value of the internetadres property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getINTERNETADRES() {
            return internetadres;
        }

        /**
         * Sets the value of the internetadres property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setINTERNETADRES(String value) {
            this.internetadres = value;
        }

    }

}
