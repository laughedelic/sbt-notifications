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

in build definition. Here's an example:

![screen shot 2017-10-26 at 11 48 47](https://user-images.githubusercontent.com/766656/32054626-4a11f29a-ba5f-11e7-8c7d-dc0e12468c3a.png)


### Tasks completion notifications

You can also configure the plugin to get notified on completion of a particular task. For example:

```scala
notifyOn(compile in Compile)
```

![screen shot 2017-10-22 at 05 22 23](https://user-images.githubusercontent.com/766656/31857876-076de4c2-b6e9-11e7-9f47-5434e31c4606.png)

Or

```scala
notifyOn(compile in Test)
```

![screen shot 2017-10-22 at 05 22 08](https://user-images.githubusercontent.com/766656/31857878-0cde1076-b6e9-11e7-9acd-c659cd85f9d2.png)

<!-- TODO: explain how to change notification formatting -->
