package org.practice.utilsForTests;

import org.testng.Assert;

public abstract class AssertionUtils {

    public static void assertTitle(String actual, String expected) {
            Assert.assertEquals(actual, expected, "Title does not match!");
        }

        public static void assertElementVisible(boolean isDisplayed) {
            Assert.assertTrue(isDisplayed, "Element is not visible!");
        }

        public static void assertTextContains(String actual, String expectedSubstring) {
            Assert.assertTrue(actual.contains(expectedSubstring),
                    "Expected text to contain: " + expectedSubstring);
        }
        public static void assertElementNotVisible(boolean isNotDisplayed){
        Assert.assertFalse(isNotDisplayed,"Element is visible");
        }
    }

