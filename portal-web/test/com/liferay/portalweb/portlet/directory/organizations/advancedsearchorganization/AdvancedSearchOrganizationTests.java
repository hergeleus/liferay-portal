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

package com.liferay.portalweb.portlet.directory.organizations.advancedsearchorganization;

import com.liferay.portalweb.portal.BaseTests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class AdvancedSearchOrganizationTests extends BaseTests {

	public static Test suite() {
		TestSuite testSuite = new TestSuite();

		testSuite.addTestSuite(AddPageDirectoryTest.class);
		testSuite.addTestSuite(AddPortletDirectoryTest.class);
		testSuite.addTestSuite(AddOrganizationTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationCityTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationCityQuotesTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationCountryTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationNameTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationNameQuotesTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationRegionTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationStreetTest.class);
		testSuite.addTestSuite(
			AdvancedSearchOrganizationStreetQuotesTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationTypeTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationZipTest.class);
		testSuite.addTestSuite(AdvancedSearchOrganizationZipQuotesTest.class);
		testSuite.addTestSuite(TearDownOrganizationTest.class);
		testSuite.addTestSuite(TearDownPageTest.class);

		return testSuite;
	}

}