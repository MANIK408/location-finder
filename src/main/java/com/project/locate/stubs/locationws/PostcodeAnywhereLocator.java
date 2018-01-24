/**
 * PostcodeAnywhereLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.project.locate.stubs.locationws;

public class PostcodeAnywhereLocator extends org.apache.axis.client.Service implements com.project.locate.stubs.locationws.PostcodeAnywhere {

    public PostcodeAnywhereLocator() {
    }


    public PostcodeAnywhereLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PostcodeAnywhereLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PostcodeAnywhere_Soap
    private java.lang.String PostcodeAnywhere_Soap_address = "https://ws1.postescanada-canadapost.ca/AddressComplete/Interactive/AutoComplete/v1.00/soapnew.ws";

    public java.lang.String getPostcodeAnywhere_SoapAddress() {
        return PostcodeAnywhere_Soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PostcodeAnywhere_SoapWSDDServiceName = "PostcodeAnywhere_Soap";

    public java.lang.String getPostcodeAnywhere_SoapWSDDServiceName() {
        return PostcodeAnywhere_SoapWSDDServiceName;
    }

    public void setPostcodeAnywhere_SoapWSDDServiceName(java.lang.String name) {
        PostcodeAnywhere_SoapWSDDServiceName = name;
    }

    public com.project.locate.stubs.locationws.PostcodeAnywhere_Soap getPostcodeAnywhere_Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PostcodeAnywhere_Soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPostcodeAnywhere_Soap(endpoint);
    }

    public com.project.locate.stubs.locationws.PostcodeAnywhere_Soap getPostcodeAnywhere_Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.project.locate.stubs.locationws.PostcodeAnywhere_SoapStub _stub = new com.project.locate.stubs.locationws.PostcodeAnywhere_SoapStub(portAddress, this);
            _stub.setPortName(getPostcodeAnywhere_SoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPostcodeAnywhere_SoapEndpointAddress(java.lang.String address) {
        PostcodeAnywhere_Soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.project.locate.stubs.locationws.PostcodeAnywhere_Soap.class.isAssignableFrom(serviceEndpointInterface)) {
				com.project.locate.stubs.locationws.PostcodeAnywhere_SoapStub _stub = new com.project.locate.stubs.locationws.PostcodeAnywhere_SoapStub(new java.net.URL(PostcodeAnywhere_Soap_address), this);
                _stub.setPortName(getPostcodeAnywhere_SoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PostcodeAnywhere_Soap".equals(inputPortName)) {
            return getPostcodeAnywhere_Soap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "PostcodeAnywhere");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws1.postescanada-canadapost.ca/", "PostcodeAnywhere_Soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PostcodeAnywhere_Soap".equals(portName)) {
            setPostcodeAnywhere_SoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
