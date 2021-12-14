from kafka import KafkaConsumer
import time
consumer = None

while consumer is None:
    try:
        consumer = KafkaConsumer(auto_offset_reset='earliest',
                             bootstrap_servers='kafka:29092', consumer_timeout_ms=1000)
        print(consumer.topics())
        consumer.subscribe("test")
    except Exception as ex:
            print('Exception while connecting Kafka')
            print(str(ex))
            time.sleep(5)
while 1:
    print("Start Consumer")
    for msg in consumer:
        print(msg.value)