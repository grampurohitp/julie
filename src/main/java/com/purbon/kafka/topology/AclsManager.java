package com.purbon.kafka.topology;

import com.purbon.kafka.topology.model.Project;
import com.purbon.kafka.topology.model.Topic;
import com.purbon.kafka.topology.model.Topology;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AclsManager {

  private final TopologyBuilderAdminClient adminClient;

  public AclsManager(final TopologyBuilderAdminClient adminClient) {
    this.adminClient = adminClient;
  }

  public void syncAcls(final Topology topology) {

    topology
        .getProjects()
        .stream()
        .forEach(new Consumer<Project>() {
          @Override
          public void accept(Project project) {
            project
            .getTopics()
            .stream()
            .forEach(new Consumer<Topic>() {
              @Override
              public void accept(Topic topic) {
                final String fullTopicName = topic.composeTopicName(topology, project.getName());
                final Collection<String> consumers = project
                    .getConsumers()
                    .stream()
                    .map(consumer -> consumer.getPrincipal())
                    .collect(Collectors.toList());
                setAclsForConsumers(consumers, fullTopicName);

                final Collection<String> producers = project
                    .getProducers()
                    .stream()
                    .map(producer -> producer.getPrincipal())
                    .collect(Collectors.toList());
                setAclsForProducers(producers, fullTopicName);

              }
            });
          }
        });
  }

  public void setAclsForConsumers(Collection<String> principals, String topic) {
    principals.forEach(principal -> adminClient.setAclsForConsumer(principal, topic));
  }

  public void setAclsForProducers(Collection<String> principals, String topic) {
    principals.forEach(principal -> adminClient.setAclsForProducer(principal, topic));
  }

}