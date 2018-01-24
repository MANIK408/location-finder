/**
 * AddressComplete_Interactive_AutoComplete_v1_00_Results.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.project.locate.stubs.locationws;

public class AddressComplete_Interactive_AutoComplete_v1_00_Results  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String text;

    private java.lang.String highlight;

    private java.lang.String description;

    private boolean isRetrievable;

    public AddressComplete_Interactive_AutoComplete_v1_00_Results() {
    }

    public AddressComplete_Interactive_AutoComplete_v1_00_Results(
           java.lang.String id,
           java.lang.String text,
           java.lang.String highlight,
           java.lang.String description,
           boolean isRetrievable) {
           this.id = id;
           this.text = text;
           this.highlight = highlight;
           this.description = description;
           this.isRetrievable = isRetrievable;
    }


    /**
     * Gets the id value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the text value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @return text
     */
    public java.lang.String getText() {
        return text;
    }


    /**
     * Sets the text value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @param text
     */
    public void setText(java.lang.String text) {
        this.text = text;
    }


    /**
     * Gets the highlight value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @return highlight
     */
    public java.lang.String getHighlight() {
        return highlight;
    }


    /**
     * Sets the highlight value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @param highlight
     */
    public void setHighlight(java.lang.String highlight) {
        this.highlight = highlight;
    }


    /**
     * Gets the description value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the isRetrievable value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @return isRetrievable
     */
    public boolean isIsRetrievable() {
        return isRetrievable;
    }


    /**
     * Sets the isRetrievable value for this AddressComplete_Interactive_AutoComplete_v1_00_Results.
     * 
     * @param isRetrievable
     */
    public void setIsRetrievable(boolean isRetrievable) {
        this.isRetrievable = isRetrievable;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressComplete_Interactive_AutoComplete_v1_00_Results)) return false;
        AddressComplete_Interactive_AutoComplete_v1_00_Results other = (AddressComplete_Interactive_AutoComplete_v1_00_Results) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.text==null && other.getText()==null) || 
             (this.text!=null &&
              this.text.equals(other.getText()))) &&
            ((this.highlight==null && other.getHighlight()==null) || 
             (this.highlight!=null &&
              this.highlight.equals(other.getHighlight()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.isRetrievable == other.isIsRetrievable();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getText() != null) {
            _hashCode += getText().hashCode();
        }
        if (getHighlight() != null) {
            _hashCode += getHighlight().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += (isIsRetrievable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressComplete_Interactive_AutoComplete_v1_00_Results.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "AddressComplete_Interactive_AutoComplete_v1_00_Results"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "Text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("highlight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "Highlight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRetrievable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "IsRetrievable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
