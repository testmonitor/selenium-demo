# TestMonitor Selenium Demo

This repository contains a demo that shows you how to save your Selenium test results in [TestMonitor](https://www.testmonitor.com/).

## Table of Contents

- [Prerequisites](#prerequisites)
  * [Windows](#windows)
  * [Linux](#linux-debian-based)
  * [Mac](#mac)
- [Running the Demo](#running-the-demo)
- [Credits](#credits)
- [License](#license)

## Prerequisites

In order to run this demo, you have to make sure the following applications are installed:

* A recent [Java JDK](https://www.oracle.com/java/technologies/downloads/).
* A recent [ChromeDriver](https://chromedriver.chromium.org/).

A **TestMonitor project** and **Auth token** are required as well. When you're already a TestMonitor user, create a new project or re-use an existing one. You can create an Auth token by clicking on your avatar on the top right and selecting *"My account..."*. Select *API* in the menu and click on *Create Token* to generate your Auth token.

If you're not a TestMonitor user, you can [register for a free trial](https://register.testmonitor.com/) first.

### Windows

Follow these steps:

- Download and install the [Java Software Development Kit (JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
- Install [Google Chrome](https://www.google.com/intl/en_us/chrome/).
- Download the [Google ChromeDriver](https://chromedriver.chromium.org/downloads) that matches your Google Chrome version.

### Linux (Debian-based)

Follow the instructions of [this tutorial](https://tecadmin.net/setup-selenium-chromedriver-on-ubuntu/) to setup Java, Chrome, and the ChromeDriver on your system.

### Mac

When on a Mac, we recommend installing these applications through [Brew](https://brew.sh/).

```sh
$ brew install --cask oracle-jdk
$ brew install --cask chromedriver
```

## Running the Demo

Start by cloning the repository from Github:

```sh
$ git clone https://github.com/testmonitor/selenium-demo.git
```

Let Maven install the required packages:

```sh
$ mvn install
```

Configure the path of your Chrome driver:

```sh
$ export CHROMEDRIVER_PATH=/usr/local/bin/chromedriver
```

Configure the properties in the `testmonitor.properties` file:

```properties
TESTMONITOR_DOMAIN=mydomain.testmonitor.com
TESTMONITOR_TOKEN=itsatoken
TESTMONITOR_PROJECT_ID=1
TESTMONITOR_MILESTONE_ID=1
TESTMONITOR_TEST_RUN_PREFIX=AT 
```

And you're all good to go! 

To run a single test case without sending the results to TestMonitor, simply type:

```sh
$ mvn test -P single
```

To run a series of happy flow test cases and send their results to TestMonitor, use:

```sh
$ mvn test -P happyflow
```

You can test all test cases (including the failing ones) and send the results to TestMonitor using:

```sh
$ mvn test -P all
```

## Credits

* **Thijs Kok** - *Lead developer* - [ThijsKok](https://github.com/thijskok)
* **Stephan Grootveld** - *Developer* - [Stefanius](https://github.com/stefanius)
* **Muriel Nooder** - *Developer* - [ThaNoodle](https://github.com/thanoodle)

## License

TestMonitor is a commercial product, provided as a SaaS application to the customers of TestManagement BV.
