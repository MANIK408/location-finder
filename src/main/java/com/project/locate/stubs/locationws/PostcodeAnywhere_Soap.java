/**
 * PostcodeAnywhere_Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.project.locate.stubs.locationws;

public interface PostcodeAnywhere_Soap extends java.rmi.Remote {
	public com.project.locate.stubs.locationws.AddressComplete_Interactive_AutoComplete_v1_00_Results[] addressComplete_Interactive_AutoComplete_v1_00(
			java.lang.String key, java.lang.String searchTerm, java.lang.String location, int locationAccuracy,
			java.lang.String country, java.lang.String languagePreference) throws java.rmi.RemoteException;
}
