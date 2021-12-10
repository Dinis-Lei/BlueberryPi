import random
import json
import time
from kafka import KafkaProducer


def generate_temperature_storage():
    temp = random.uniform(-1,1)
    timestamp = int(time.time())
    print(timestamp ,temp)
    return {"key": "storage_temprature", "args": {"timestamp": timestamp, "temp": temp} }

def connect_kafka_producer():
    _producer = None
    try:
        _producer = KafkaProducer(bootstrap_servers=['localhost:9092'], value_serializer=lambda v: json.dumps(v).encode('utf-8'))
    except Exception as ex:
        print('Exception while connecting Kafka')
        print(str(ex))
    finally:
        return _producer

def publish_message(producer_instance, topic_name, value):
    try:
        producer_instance.send(topic_name, value=value)
        producer_instance.flush()
        print('Message published successfully.')
    except Exception as ex:
        print('Exception in publishing message')
        print(str(ex))



if __name__ == "__main__":
    producer = connect_kafka_producer()
    while 1:
        time.sleep(1)
        publish_message(producer_instance=producer, topic_name="test", value=generate_temperature_storage())
