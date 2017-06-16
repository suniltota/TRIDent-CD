package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents RoleDetail in MISMO XML
 * @author sboragala
 *
 */
public class RoleDetail extends MISMODataAccessObject {
	private static final long serialVersionUID = 4742083595431495437L;
	public final String PartyRoleType;
	
	public RoleDetail(Element element) {
		super(element);
		PartyRoleType = getValueAddNS("PartyRoleType");
	}
}
