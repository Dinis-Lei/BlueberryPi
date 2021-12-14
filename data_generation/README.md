## How to run without docker

### Importante

Mudar no Producer e Consumer o bootstrap_servers para 'localhost:9092'


### start servers

    bin/zookeeper-server-start.sh config/zookeeper.properties

    bin/kafka-server-start.sh config/server.properties

### create topic

    bin/kafka-topics.sh --create --partitions 1 --replication-factor 1 --if-not-exists --bootstrap-server localhost:9092 --topic test

### run scripts

    python3 generator.py

    python3 testConsumer.py


## Run with docker

    docker-compose build

    docker-compose up

    docker-compose exec kafka kafka-topics --create --topic test --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181
