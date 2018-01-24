/**
 * PostcodeAnywhere_SoapSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.project.locate.stubs.locationws;

public class PostcodeAnywhere_SoapSkeleton implements com.project.locate.stubs.locationws.PostcodeAnywhere_Soap, org.apache.axis.wsdl.Skeleton {
    private com.project.locate.stubs.locationws.PostcodeAnywhere_Soap impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "SearchTerm"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "Location"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "LocationAccuracy"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "Country"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "LanguagePreference"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addressComplete_Interactive_AutoComplete_v1_00", _params, new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "AddressComplete_Interactive_AutoComplete_v1_00_Result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "AddressComplete_Interactive_AutoComplete_v1_00_ArrayOfResults"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "AddressComplete_Interactive_AutoComplete_v1_00"));
        _oper.setSoapAction("http://ws1.postescanada-canadapost.ca/AddressComplete_Interactive_AutoComplete_v1_00");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addressComplete_Interactive_AutoComplete_v1_00") == null) {
            _myOperations.put("addressComplete_Interactive_AutoComplete_v1_00", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addressComplete_Interactive_AutoComplete_v1_00")).add(_oper);
    }

    public PostcodeAnywhere_SoapSkeleton() {
        this.impl = new com.project.locate.stubs.locationws.PostcodeAnywhere_SoapImpl();
    }

    public PostcodeAnywhere_SoapSkeleton(com.project.locate.stubs.locationws.PostcodeAnywhere_Soap impl) {
        this.impl = impl;
    }
    public com.project.locate.stubs.locationws.AddressComplete_Interactive_AutoComplete_v1_00_Results[] addressComplete_Interactive_AutoComplete_v1_00(java.lang.String key, java.lang.String searchTerm, java.lang.String location, int locationAccuracy, java.lang.String country, java.lang.String languagePreference) throws java.rmi.RemoteException
    {
        com.project.locate.stubs.locationws.AddressComplete_Interactive_AutoComplete_v1_00_Results[] ret = impl.addressComplete_Interactive_AutoComplete_v1_00(key, searchTerm, location, locationAccuracy, country, languagePreference);
        return ret;
    }

}
