# dbus-java-with-java-utils-osgi

This is the code of the Eclipse plug-in project put together to use the following dbus-java and java-utils in the OSGi framework. Java source codes have not changed.
- [dbus-java 3.0.2](https://github.com/hypfvieh/dbus-java/tree/dbus-java-parent-3.0.2)
- [java-utils 1.0.5](https://github.com/hypfvieh/java-utils/tree/1.0.5)

The reason for this integration is that if the bundle containing the JNI library for Unix Domain Socket and the bundle for reading the JNI library were separated, they could not be used on the OSGi framework. I tried to deal with this trouble using `Require-Bundle`, but a cycle of dependencies occurred. So, for convenience, I put it together.

Each license files are located below.
- dbus-java license file - `dbus-java-with-java-utils-osgi/resources/dbus-java-3.0.2/COPYING`
- java-utils license file - `dbus-java-with-java-utils-osgi/resources/java-utils-1.0.5/LICENSE`

I think that this repository will not be necessary if [jnr-unixsocket](https://github.com/jnr/jnr-unixsocket) is used in place of the Unix Domain Socket JNI library planned for dbus-java in the future.

I use this with the following bundles.
- [SLF4J 1.7.26](https://www.slf4j.org/)

The built bundle file is placed below. You need Java 8 or higher.
- build/

Finally, I would like to thank the authors of the very useful codes, and everyone who has committed.
