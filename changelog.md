v2.1.4 / 2021-12-20
==================

  * Bump log4j from 2.13.3 to 2.17.0 (#425)

v2.1.3 / 2021-08-18
===================

  * version 2.1.3 bump
  * Add kerberos example for julieops (#310)

v2.1.2 / 2021-04-30
===================

  * Bump version for httpclient.version to avoid regression in internal domain validation HTTPCLIENT-2047 (a18ea2d81f8074ca0844582c62b646323b19db02)

v2.1.1 / 2021-04-30
===================

  * Amend dependencies to be fully supporting Confluent RBAC implementation  (#272)
  * Fix problem with passing a dir that containts another dir for topologies (#269)
  * Amend listAcls function for rbac providers so delete and listing after execution works as well when using RBAC (#266)
  * Sync integration test Kafka version with the version in the pom. (#260)
  * Add overridingClientConfig parameter to allow override and fallbacks ot the original configuration. This would be useful when passing parameters that need to be adapted in some execution scenarios like deployments or CI/CD.
  * verify CLI parameters as first thing in the process before creating the main handler object  (#253)

v2.1.0 / 2021-04-03
===================

  * Use Set as internal datastructure for 'principals to be created' instead of List. 
  * Add gcp backend and general cleanup
  * Introduce an s3 backend
  * Add control center and schema registry principals on the platform level to be handled by the service account manager as well (#247)
  * Add error handling when building acls and specially when the group prefix is empty (#245)
  * Update topic partition and replication factor to use cluster defaults  if nothing is available in the topology (#244)
  * Skip Julie principal when dealing with ACLs, if present (#246)

v2.0.1 / 2021-03-16
===================

* Upgrade dependencies to Kafka 2.7 and Confluent 6.1, plus some other minor ones
* Add support and checks for non ascii characters in schema content (#230)
* Add integration test for SchemaRegistry Management in multiple formats (#229)
* Add more clear error for descriptor files without the topics section in the serdes (#227)
* Fix FileBackend bug in windows to use FileWriter due to closing problems with RandomAccessFile (#222)
* Add support for JsonSchema and Protobuf when working with Schema Registry (#216)

v2.0.0 / 2021-02-27
===================

  * Entity rename to JulieOps as a new project name (#213)
  * [Sonar 2095] Fix, resources should be closed (#207)

v1.5.1 / 2021-02-25
===================

* Bump jackson.version from 2.10.3 to 2.12.1 (#211)
* File backend fix (#210)
* Support topology validations with fully qualified class names as configuration values (backwards compatible) (#204)
* Add topology metadata support for topics and users (#200)
* README update
* updated github action to take into account the new version branches

v1.5.0 / 2021-01-16
===================

* Add ability to use the name as the end topic name (#194)
* Support ACLs from Cluster (#188)
* Enable to run integration test parametrised vs multiple cp versions (#189)

v1.4.0 / 2021-01-03
===================

* [PrincipalManager] Improved error handling when using Confluent Cloud  (#186)
* Add support for a prefix list to filter which principals are allowed to be managed (#185)
* Enable remote state fetching from the cluster for the principals (#187)
* Add support for absolute paths when fetching schemas from disk (#184)
* Add support for custom SchemaTypes and Compatibility level when managing schemas (#182)
* Support multiple schema per topic (#183)
* Adding ability to pass KTB options for the jvm or the KTB itself via an environment variable (#177)
* Allow topic state bookkeeping from the cluster backend state system   (#180)
* Fix that principle manager was not adding principles defined within consumers/producers at topic levels (#179)
* Improve AccessControlManagerTest (#168)
* Option to disable creation of the CLUSTER CREATE ACL for connectors (#173)
* Add Kafka Streams integration test bed (#169)

v1.3.0 / 2020-12-14
===================

* Fix RPM/DEB packaging update   (#172)
* Managed principals using topology Builder, include support for ccloud CLI (#135)
* Add an action to build and push artifacts for KTB (#166)
* Introduced to detailed allow delete options to be used from the config files.
  One for now dedicated to topics and one for the bindings (#163)
* Keep order in file backend for generated items (#161)
* Nightly docker image build, regular push to docker hub (#153)
* Add workflows with github actions (#152)
* Integration test verifying that producers can produce and consumers can consume (#150)
* Fail on parsing of invalid topologies (#149)
* ACL integration tests using testcontainers (#131)
* Test cleanup (#145)
* Add streams acls for consumer group and support for applicationId (#144)
* Fix bug with a topology/config not using schemas (#143)

1.2.2 / 2020-11-24
==================

* fix path of topology builder gen jar in the deb package mapping (#140)
* Add schema.registry.url to docs and examples (#139)
* make usage of key schemas optional, but throw an exception if key is present but not value schemas (#128)


1.2.0 / 2020-11-16
==================

* Add custom plans to help standardise topic config (#129)
* mockito-all was abandoned in 2015. Using mockito-core instead. (#130)
* fix bug with state management for acls and deletion of removed rules (#127)
* Add topic level Access Control Rules (ACLs/RBAC) (#124)
* Upgrade testcontainers to support recent Docker for Mac. (#121)

1.1.0 / 2020-10-30
==================

* Grouping acls for consumers and producers when the function is selected via configuration (#120)
* Enable JSON and YAML as Topology descriptor file formats. (#108)
* Add support for Transactional and Idempotence producing and consuming in KTB (#119)
* Fix missing key in the deserializer for project causing projects to not properly extract the producers and perse not create the necessary acls
* ammend deb sign script

1.0.0 / 2020-10-23
==================

* Extended the logging support to many components and classes in the KTB with the idea to support better troubleshooting. (Closes #101)
* Add a filter to delete only topic configurations that are explicitly set, not the ones that use default values. (closes #99)
* Fix a bug with wrongly set PatternType for Consumer group acls level. (closes #111)
* Add test and documentation for list type config values as handled using the new config library.
* Add a Health Check function used during the creation of the internal admin client. This function will describe the cluster and perse test if the setup credentials are ok.
  This fixes (#112)

1.0.0-rc2 / 2020-10-06
==================
* Check for required configuration values for the configuration of RBAC, if not present it raises a Configuration error
* Update Log4j version to 2.13.3 to prevent CVE-2020-9488
* Add an option to not delete internal topics, including an option configure rules to filter internal topics that can't not be removed config with  _kafka.internal.topic.prefixes_,
  by default this filters all topics starting with underscore. Users can configure more than one filter in the property file.
* Made internal topics for Kafka Connect, Schema Registry and Confluent Control Center configurable.
  By introducing this, users can now handle non-standard instances of Schema Registry or having for example more than
  a single Kafka Connect cluster (situation more than common).
* Add a rest api component, available under server-api and created using the Micronaut Framework. This component is an independent artifact, that wrap the
  Kafka Topology Builder library in a single and uniform web application that can be used to automate and centralise the configuration management for clusters.
* Remove the requirement of writing in the configuration file the access control method, as of now will be ACLs as default value and if other (like RBAC) is required
  it will have to be properly configured.
* Merge #22 to provide the initial support for schema management. Now you can manage schemas with Confluent Schema Registry using the Kafka Topology Builder. Thanks Kiril.
* Add an option to specify custom consumer groups in the topology descriptor
* Add option to increase the partition count in an already created topic.
* Add support for cluster level acls for kafka, schema registry and kafka connect. This solve issues #42.
* Add support for schema level permissions in the rbac mode. This solve issue #44.
* Add support for connect level permissions in the rbac mode. This solve issue #45.
* Implement an initial method for building an execution plan including an option to print a dry-run. This solve issue #62.
* Rename team as context as it was a confusing wording. This solve issue #68.
* Fix bug on removal of acls. This solve issue #72.
* Fix bug with schema registration and file path reference,  now by default all are relative to the main directory where the topology file is staying.
* Improved delete and general management of acls logic, only create what is necessary, only delete what is mandatory.
* Fixed a problem with the schema registry client that got no configs, so it would not be possible to use with security contrain systems such as confluent cloud.
* Add the possibility to define a topic and project pattern using the jinja format, like this users can tune the order of the variables without hampeting the full idea of the structure
* Add a framework to add incoming validation for topology files, this could be configured with the topology.validations config array.
* Added the lightbend config library to support a more structured config management, including usage of environment variables and multiple config formats.
* Made the broker CLI param optional, however one of both config or CLI has to be present, CLI takes always precedence over the config value.

1.0.0-rc1 / 2020-07-28
==================
* Add support for platform wide acls for control center in teh topology description file.
* Port support for schema registry and confluent control center roles in the rbac provider. Now the two providers are future pair.
* Fix the usage of the source field, it should not be mandatory as the others list and the custom topology serdes can cover a dynamic list
  of fields between the team and the first project separator.
* Add code to support passing a directory, only topologies for a single team are supported for now.
* Add support for passing a directory, instead of a single file as topology. If a directory is passed, all topologies are expected to be from
  the same team and all subsequent projects will be at append one after the other.
* Implement the necessary methods to pass back the status from the rbac modules, so the reply and clean up is possible with multiple runs
* Added an option to use redis as external system to keep the acls status list. Now people can use either files or redis.
  Note in the future this subsystem should be externalised as plugins
* raise up error handling in case of non available cluster
* Make usage of the maven jdeb plugin to build a deb package to use in distributions such as Debian and relative like the Ubuntu family
* Fix parser problem with non required parameters, such as the ones for the acls where an empty array had to be in place, now all principals for acls if not required can be ignored and will have no effect
* Ammend param and typo issues with rbac that where not facilitating the execution flow, updated as well the examples to match a correct execution flow

v0.11:
* Add support for platform wide acls for schema registry in the topology description file.
* Rebird the option to disable deletes when not required anymore.
* Code refactoring in several classes
* add a option, activated by default to print the current status of topics created and acls being set after each run. this is very valuable to know the current state of the cluster after each execution
* add release mechanism for docker images with the kafka topology builder (#21)
* Add version number to maven-compiler-plugin (#16)
* Add support to keeping notes of generated acls (#15)
* Add support for storing the current generated acls within a state file. This is useful to delete
  acls that are known and generated previously by the topology builder.

v0.10.3:
* updated ACLs for producers and consumers to include the describe permission in order to properly
  allow for metadata recollection.
* Fix wrong verification for parameters in the CLI.

v0.10.2:
* Add improved connection handling when talking with the RBAC MDS server
* Extended the test suite for rbac and isoleted test with the SASL plain suit.

v0.10.1:
* Fix the RPM builder script, build an rpm that create proper users for running the application.
* Fix param passing to the RBAC provider from the properties file.
* Add more clear example configuration within the RPM artifacts.

v0.10:
* Separate access control providers between simple ACLs and RBAC provider.
* Introduced an option to select with Access Control mechanism is used using the application configuration file.
* Add initial rbac support for application roles.
* Add the option to define custom rbac (per project) roles. This option allows you to define inside the project level role and principals mapping such as ResourceOwner, ... this will be automatically applied per topic if using the rbac acces control provider

v0.9:
* RPM package for installation in Redhat based systems.
* Fully descriptive managed ACLs (via a descriptor file) - Create, Read, Update and Delete.
* Fully descriptive and structured topic management (via a descriptor file) - Create, Read, Update and Delete.
* Simple Roles for Connectors, Streams application, Consumers and Producers.