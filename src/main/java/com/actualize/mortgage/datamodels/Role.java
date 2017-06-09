package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Role in MISMO XML
 * @author sboragala
 *
 */
public class Role extends MISMODataAccessObject {
	private static final long serialVersionUID = 2481131092700366498L;
	public final RealEstateAgent realEstateAgent;
    public final Licenses licenses;
    public final RoleDetail roleDetail;

    public Role(Element element) {
		super(element);
		realEstateAgent = new RealEstateAgent((Element)getElementAddNS("REAL_ESTATE_AGENT"));
		licenses = new Licenses((Element)getElementAddNS("LICENSES"));
		roleDetail = new RoleDetail((Element)getElementAddNS("ROLE_DETAIL"));
	}
}
