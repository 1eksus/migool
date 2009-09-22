package migool.poster.cms.dle.test;

import migool.host.auth.impl.HostLoginPassword;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IConstants {
	String LOGIN = "dle";
	String PASS = "dle";

	// Test hosts:
	public static final String HOST_DLE_67 = "dle67";
	public static final String HOST_DLE_70 = "dle70";
	public static final String HOST_DLE_72 = "dle72";
	public static final String HOST_DLE_73 = "dle73";
	public static final String HOST_DLE_75 = "dle75";
	public static final String HOST_DLE_80 = "dle80";
	public static final String HOST_DLE_82 = "dle82";
	
	//
	public static final HostLoginPassword HLP_DLE_67 = new HostLoginPassword(HOST_DLE_67, LOGIN, PASS);
	public static final HostLoginPassword HLP_DLE_80 = new HostLoginPassword(HOST_DLE_80, LOGIN, PASS);
}