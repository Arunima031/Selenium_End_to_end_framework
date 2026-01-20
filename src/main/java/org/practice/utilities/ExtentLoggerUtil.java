package org.practice.utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentLoggerUtil {

        private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

        private ExtentLoggerUtil() {}

        public static void setTest(ExtentTest test) {
            extentTest.set(test);
        }

        public static ExtentTest getTest(){
            return extentTest.get();
        }

        public static void unload() {
            extentTest.remove();
        }

        public static void info(String message) {
            if (extentTest.get() != null) {
                extentTest.get().info(message);
            }
            LoggerUtil.info(message);
        }

        public static void pass(String message) {
            if (extentTest.get() != null) {
                extentTest.get().pass(message);
            }
            LoggerUtil.info(message);
        }

        public static void fail(String message) {
            if (extentTest.get() != null) {
                extentTest.get().fail(message);
            }
            LoggerUtil.error(message);
        }

        public static void warn(String message) {
            if (extentTest.get() != null) {
                extentTest.get().warning(message);
            }
            LoggerUtil.warn(message);
        }
    }
