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

package com.liferay.portalweb.socialofficehome.activities.microblogsentryactivity.sofoviewmicroblogsentryactivityfollowing;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class SOFo_ViewMicroblogsEntryActivityFollowingTest extends BaseTestCase {
	public void testSOFo_ViewMicroblogsEntryActivityFollowing()
		throws Exception {
		selenium.open("/user/socialofficefollowersn/home/");
		loadRequiredJavaScriptModules();

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//div/div/div/div[1]/ul/li[1]/a")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("//div/div/div/div[1]/ul/li[1]/a",
			RuntimeVariables.replace("Home"));
		selenium.waitForPageToLoad("30000");
		loadRequiredJavaScriptModules();
		assertEquals(RuntimeVariables.replace("Activities"),
			selenium.getText("//div[2]/div/section/header/h1/span"));
		assertEquals(RuntimeVariables.replace("Following"),
			selenium.getText("//div/ul/li[3]/span/a"));
		selenium.clickAt("//div/ul/li[3]/span/a",
			RuntimeVariables.replace("Following"));
		selenium.waitForPageToLoad("30000");
		loadRequiredJavaScriptModules();
		assertTrue(selenium.isVisible("//div[1]/span[1]/img"));
		assertTrue(selenium.isVisible("//span[2]/span/img"));
		assertEquals(RuntimeVariables.replace("Microblogs Content"),
			selenium.getText("//section/div/div/div/div/div/div[2]/div[1]"));
		assertEquals(RuntimeVariables.replace("Joe Bloggs"),
			selenium.getText("//div[2]/div[2]/a"));
	}
}