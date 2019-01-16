# appengine-maven-plugin-bug

This repo is a minimized reproduction of an argument mangling bug I ran into when using 
[`com.google.cloud.tools:appengine-maven-plugin:1.3.2`](https://cloud.google.com/appengine/docs/standard/java/tools/maven-reference#appenginerun). The value passed to 
`configuration.jvmFlags` has commas (`,`) stripped out, and replaced with spaces.

I discovered this bug while attempting to setup remote debugging and received an error when 
trying to pass the jvm args for jdwp. The arg I passed is
```
-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
```

When I attempt to run the following error is logged to stderr:
```
[INFO] --- appengine-maven-plugin:1.3.2:run (default-cli) @ appengine-maven-plugin-bug ---
Jan 16, 2019 3:44:59 PM com.google.cloud.tools.appengine.cloudsdk.CloudSdk logCommand
INFO: submitting command: ${JAVA_8_HOME}/jre/bin/java -agentlib:jdwp=transport=dt_socket address=8000 server=y suspend=n -Duse_jetty9_runtime=true -D--enable_all_permissions=true -Dappengine.sdk.root=${GCLOUD_SDK_HOME}/platform/google_appengine/google/appengine/tools/java -cp ${GCLOUD_SDK_HOME}/platform/google_appengine/google/appengine/tools/java/lib/appengine-tools-api.jar com.google.appengine.tools.development.DevAppServerMain --allow_remote_shutdown --disable_update_check --no_java_agent ${PROJECT_ROOT}/target/appengine-maven-plugin-bug-0.1.0-SNAPSHOT
[INFO] GCLOUD: ERROR: JDWP Non-server transport dt_socket must have a connection address specified through the 'address=' option
[INFO] GCLOUD: ERROR: JDWP invalid option: -agentlib:jdwp=transport=dt_socket
```

Notice the argument passed to the jvm is in fact the following (notice the missing commas)
```
-agentlib:jdwp=transport=dt_socket address=8000 server=y suspend=n
```


## To Run

### Prerequisites
1. java 1.8+
2. Maven 3.5+
3. google-cloud-sdk installed and on `PATH`

### Running
```bash
mvn -Premote-debug appengine:run
```
