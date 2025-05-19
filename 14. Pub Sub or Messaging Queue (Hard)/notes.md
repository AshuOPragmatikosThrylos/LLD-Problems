MQ (RabbitMQ) - message delivery from queue to consumer is P2P (1 message to only 1 consumer)
Pub Sub (Kafka) - could be 1-M

- for asynchronous communication

our system supports
1. multiple topics
2. efficient message publishing
3. reliable message delivery to subscribers
4. parallel subscriber execution ......what is subscriber execution?
5. high throughput and low latency ......how is this achieved? how it could not have been achieved? what would have been a poor design?

- partition (Kafka) - each topic is split into partitions which is the fundamental unit of parallelism in Kafka

- ordering maintained in a partition
- ordering not necessarily maintained across partitions
- each partition has an immutable sequence of messages
- Immutable: Once a message is written to a partition, it cannot be changed or deleted (until retention time expires)

- messages within a partition are assigned a sequential id called offset
- because of offsets we can achieve - at least once, at most once and exactly-once delivery

- partitions are distributed across "brokers" (servers with copies of partitions for fault tolerance) in a Kafka cluster

- consumers can be part of "consumer groups" for parallel processing from topic/partitions
- when multiple consumers belong to the same group, each one receives messages from a subset of partitions
- consumers track the latest offset they've processed for each partition

- Consumer Lag - the diff b/w producer offset and consumer offset indicating how many msgs are still left to be processed by consumer
 

 - parallel consumption - consumers run in parallel even if they consume from the same topic, improving throughput and scalability

 - when a message is published, all subscribers of that topic receive it in parallel

 Clarifying questions to ask
 1. Do we want message persistence?
 2. ordering guarantees
 3. message acknowledgement or retry mechanisms

In Topic class, either make 'messages' as CopyOnWriteArrayList or make its setter and getter synchronized

45:11 - processing of messages should be done outside synchronized cuz it can take time, so don't make others wait

need of TopicSubscriber class - cuz subscriber need to store offset for each topic

54:26 - resetting of offset leads to consumption from starting but automatically even without a new message being published?? Yess cuz resetting offset notifies all current subscribers...Refer resetOffset()

resetting offset helps in replaying the messages

Topics maintain a list of subscribers (observers) and notify them asynchronously when a new message is published => Observer DP

Kafka Controller serves as the broker
- The broker decouples publishers and subscribers
      A publisher doesn't need to know who the subscribers are
      A subscriber doesn’t need to know or care who the publisher is
- Broker enables independent scaling of publishers and subscribers

https://github.com/AshuOPragmatikosThrylos/Design-Patterns-Practice/tree/master/02.%20Observer
https://codewitharyan.com/tech-blogs/design-pub-sub-model-like-kafka

https://youtu.be/7gMm0iQNZGA (Imp)
Apache Kafka vs MQ
1. Basic Functionality - unlike MQ Kafka does not immediately remove a processed message from a topic
2. Broadcasting
    * MQ - each message in the Q can be processed only by a single consumer
    * Kafka - each message in the topic is processed by every subscribed consumer group
3. Message Ordering
    * MQ - no guarantee that messages will be processed by the same consumer in the same order they were added in the queue
    * Kafka - ordering guarantees when read from same partition
4. Message replay
    * MQ - does not allow replay
    * Kafka - It does not remove messages as soon as they are read and it maintains offset => replay possible
5. Consumer Limit
    * MQ - No limit
    * Kafka - Max number of consumers (within a single consumer group) of a topic == number of partitions in a topic




https://youtu.be/4BEzgPlLKTo
https://youtu.be/rhybuOqsbD8


