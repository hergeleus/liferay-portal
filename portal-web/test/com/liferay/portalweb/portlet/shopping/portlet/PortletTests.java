/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.portlet.shopping.portlet;

import com.liferay.portalweb.portal.BaseTests;
import com.liferay.portalweb.portlet.shopping.portlet.addportlet.AddPortletTests;
import com.liferay.portalweb.portlet.shopping.portlet.addportletduplicate.AddPortletDuplicateTests;
import com.liferay.portalweb.portlet.shopping.portlet.configureportletacceptedcreditcard.ConfigurePortletAcceptedCreditCardTests;
import com.liferay.portalweb.portlet.shopping.portlet.configureportletinsuranceflatrate.ConfigurePortletInsuranceFlatRateTests;
import com.liferay.portalweb.portlet.shopping.portlet.configureportletinsurancepercentage.ConfigurePortletInsurancePercentageTests;
import com.liferay.portalweb.portlet.shopping.portlet.configureportletshippingflatrate.ConfigurePortletShippingFlatRateTests;
import com.liferay.portalweb.portlet.shopping.portlet.configureportletshippingpercentage.ConfigurePortletShippingPercentageTests;
import com.liferay.portalweb.portlet.shopping.portlet.configureportletstatetax.ConfigurePortletStateTaxTests;
import com.liferay.portalweb.portlet.shopping.portlet.removeportlet.RemovePortletTests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletTests extends BaseTests {

	public static Test suite() {
		TestSuite testSuite = new TestSuite();

		testSuite.addTest(AddPortletTests.suite());
		testSuite.addTest(AddPortletDuplicateTests.suite());
		testSuite.addTest(ConfigurePortletAcceptedCreditCardTests.suite());
		testSuite.addTest(ConfigurePortletInsuranceFlatRateTests.suite());
		testSuite.addTest(ConfigurePortletInsurancePercentageTests.suite());
		testSuite.addTest(ConfigurePortletShippingFlatRateTests.suite());
		testSuite.addTest(ConfigurePortletShippingPercentageTests.suite());
		testSuite.addTest(ConfigurePortletStateTaxTests.suite());
		testSuite.addTest(RemovePortletTests.suite());

		return testSuite;
	}

}