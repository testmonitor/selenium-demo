# TestMonitor TestNG Reporter Demo

This demo repository showcases how to integrate the [TestMonitor TestNG Reporter](https://mvnrepository.com/artifact/com.testmonitor/testng-reporter) into your Selenium WebDriver test suite. It contains example tests that demonstrate automated test reporting to TestMonitor.

## Table of Contents

- [About](#about)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Tests](#running-the-tests)
- [License](#license)

## About

This repository provides a working example of:

- Setting up Selenium WebDriver with TestNG and the TestMonitor reporter
- Configuring the reporter in `pom.xml` and `testng.xml`
- Sample test cases using the Page Object Model pattern
- Automatic screenshot capture on test failures
- Tests that run on Windows, Linux, and macOS

## Installation

Before you start, make sure you have the following installed:

- [Java 11](https://www.java.com/) or higher
- [Maven 3.6](https://maven.apache.org/download.cgi) or higher
- [Chrome browser](https://www.google.com/chrome/)

Clone this repository and install the dependencies:

```bash
git clone https://github.com/testmonitor/selenium-demo.git
cd selenium-demo
mvn clean install -DskipTests
```

The project uses Selenium Manager (built into Selenium 4.6+), which automatically downloads and manages the ChromeDriver for your operating system.

## Configuration

The reporter is already configured in [pom.xml](pom.xml) and [testng.xml](testng.xml).

First, add the TestMonitor TestNG reporter dependency to your Maven project:

```xml
<dependency>
  <groupId>com.testmonitor</groupId>
  <artifactId>testng-reporter</artifactId>
  <version>[1.0.0,)</version>
</dependency>
```

Next, add the reporter to the listeners section in your test suite XML:

```xml
<suite name="Tests">
  <listeners>
    <listener class-name="com.testmonitor.testng.TestNGReporter"/>
  </listeners>
  <!-- test definitions -->
</suite>
```

Configure your TestMonitor credentials using environment variables or system properties. For this demo, we recommend using `-D` parameters with Maven:

**Configuration options:**

| Variable | Required | Description |
|----------|----------|-------------|
| `TESTMONITOR_DOMAIN` | Yes | Your TestMonitor instance domain (e.g., mydomain.testmonitor.com) |
| `TESTMONITOR_TOKEN` | Yes | Your TestMonitor integration token |
| `TESTMONITOR_MILESTONE_ID` | No | ID of an existing milestone to link test results to |
| `TESTMONITOR_MILESTONE_NAME` | No | Name of a milestone (will be created if it doesn't exist or matched if it does) |
| `TESTMONITOR_ATTACHMENT_ON_FAILURE` | No | Attach screenshots on failure (default: true) |

To get started, you'll need a token from your TestNG integration. You can find it on the TestNG integration page of your TestMonitor project settings.

## Running the Tests

Run the demo tests using Maven with your TestMonitor credentials:

```bash
mvn test \
  -DTESTMONITOR_DOMAIN=mydomain.testmonitor.com \
  -DTESTMONITOR_TOKEN=your-token-here
```

Alternatively, you can set environment variables:

```bash
export TESTMONITOR_DOMAIN=mydomain.testmonitor.com
export TESTMONITOR_TOKEN=your-token-here
mvn test
```

The reporter will automatically:

- Create a test run in TestMonitor
- Submit test results (pass/fail status)
- Include screenshots for failed tests
- Report test execution times

### View results

After running the tests, check your TestMonitor instance to see the reported results.

## License

Copyright (c) TestMonitor | we are Cerios B.V. All rights reserved.
