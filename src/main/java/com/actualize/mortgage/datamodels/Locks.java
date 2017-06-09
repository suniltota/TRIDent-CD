package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Locks in MISMO XML
 * @author sboragala
 *
 */
public class Locks extends MISMODataAccessObject {
	private static final long serialVersionUID = -9159770968138358028L;
	public final Lock[] locks;
	
	public Locks(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("LOCK");
		locks = new Lock[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < locks.length; i++)
			locks[i] = new Lock((Element)nodes.item(i));
	}
}
