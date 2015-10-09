Fiddler in development process
=======

How to use fiddler as a proxy during the development.


Features
========

The proxy is configured during build process (task ) so you don't have to configured it manually and it is just for your application. In addition when the proxy is not found, standard connection is used.


Tasks
============

* Fiddler - http://ipv4.fiddler:8888/
* Genymotion
..* proxy address 10.0.3.2
* Real device
* Stetho - chrome://inspect

Links
=====

* [Fiddler configuration] (http://docs.telerik.com/fiddler/configure-fiddler/tasks/ConfigureForAndroid)
* [Fiddler and emulator] (http://blogs.msdn.com/b/jpsanders/archive/2013/04/03/configuring-fiddler-to-be-a-proxy-for-android-emulators.aspx) doesn't work very well in new images.
* [Stetho] (https://github.com/facebook/stetho)
* [GsonFormat] (https://github.com/zzz40500/GsonFormat)
* http://www.jsonschema2pojo.org/


Documentation
=============

*Fill in text about documentation.*


Changelog
=========

*Fill in text about version changes.*


```groovy
buildConfigField "boolean", "LOGS", "false"
buildConfigField "boolean", "DEV_API", "false"
``` 
