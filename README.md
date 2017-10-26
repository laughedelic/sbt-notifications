# sbt-notifications

[![](https://travis-ci.org/laughedelic/sbt-notifications.svg?branch=master)](https://travis-ci.org/laughedelic/sbt-notifications)
[![](https://img.shields.io/github/release/laughedelic/sbt-notifications/all.svg)](https://github.com/laughedelic/sbt-notifications/releases/latest)
[![](https://img.shields.io/badge/license-Apache_2.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

A simple sbt-1.x plugin that sends native OS notifications when tests are completed. Uses Toast notifications on Windows (tested on Windows 10), Notification Center on Mac OS X (tested on Mac OS X Yosemite) and `libnotify` on Linux (requires `libnotify-bin` package, tested on Ubuntu with Gnome).

> NOTE: This is a fork. Original repository is https://github.com/PavelPenkov/sbt-notifications

## Usage

Add the following to `./project/plugins.sbt`:

```scala
addSbtPlugin("laughedelic" % "sbt-notifications" % "0.1.0")
```

### Test runs notifications

By default you will get notifications on test runs: when they fail and when they pass. To receive notifications only when tests failed use

```
notifyFailureOnly := true
```

in build definition.


### Tasks completion notifications

You can also configure the plugin to get notified on completion of a particular task. For example:

```scala
notifyOn(compile in Compile)
```

<!-- TODO: explain how to change notification formatting -->
