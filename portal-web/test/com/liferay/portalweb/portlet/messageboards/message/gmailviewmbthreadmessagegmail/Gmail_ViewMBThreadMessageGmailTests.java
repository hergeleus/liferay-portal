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

package com.liferay.portalweb.portlet.messageboards.message.gmailviewmbthreadmessagegmail;

import com.liferay.portalweb.portal.BaseTests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class Gmail_ViewMBThreadMessageGmailTests extends BaseTests {

	public static Test suite() {
		TestSuite testSuite = new TestSuite();

		testSuite.addTestSuite(AddSiteTest.class);
		testSuite.addTestSuite(AddUserGmailTest.class);
		testSuite.addTestSuite(AssignMembersSiteUserTest.class);
		testSuite.addTestSuite(AddPageMBSiteTest.class);
		testSuite.addTestSuite(AddPortletMBSiteTest.class);
		testSuite.addTestSuite(ConfigurePortletEmailFromAddressTest.class);
		testSuite.addTestSuite(AddMBCategorySiteTest.class);
		testSuite.addTestSuite(
			EditMBCategoryMailingListActiveActionsTest.class);
		testSuite.addTestSuite(Gmail_TearDownEmailTest.class);
		testSuite.addTestSuite(GmailServer_TearDownEmailTest.class);
		testSuite.addTestSuite(
			GmailServer_TearDownMailingListMessageTest.class);
		testSuite.addTestSuite(PostNewMBCategoryThreadMessageSiteTest.class);
		testSuite.addTestSuite(
			Gmail_ReplyMBCategoryThreadMessageEmailTest.class);
		testSuite.addTestSuite(ViewMBThreadMessageGmailTest.class);
		testSuite.addTestSuite(
			Gmail_ViewMBThreadMessageGmailMailingListTest.class);
		testSuite.addTestSuite(Gmail_TearDownEmailTest.class);
		testSuite.addTestSuite(GmailServer_TearDownEmailTest.class);
		testSuite.addTestSuite(
			GmailServer_TearDownMailingListMessageTest.class);
		testSuite.addTestSuite(TearDownMBCategoryTest.class);
		testSuite.addTestSuite(TearDownPageSiteTest.class);
		testSuite.addTestSuite(TearDownUserTest.class);
		testSuite.addTestSuite(TearDownSiteTest.class);

		return testSuite;
	}

}