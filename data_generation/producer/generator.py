import random
import json
import time
import pika


def generate_temperature_storage():
    temp = random.uniform(-1,1)
    timestamp = int(time.time())
    print(timestamp ,temp)
    return json.dumps({"key": "storage_temperature", "timestamp": timestamp, "temp": temp })

if __name__ == "__main__":
    print("Start GENERATOR")
    connection = None
    while connection is None:
        try:
            connection = pika.BlockingConnection(
                pika.ConnectionParameters(host='rabbitmq', port=5672))
        except Exception as ex:
            print('Exception while connecting Rabbit')
            print(str(ex))
            time.sleep(5)

    channel = connection.channel()

    channel.queue_declare(queue='blueberry')

    while 1:
        time.sleep(5)
        channel.basic_publish(exchange='', routing_key='blueberry', body=generate_temperature_storage())

    connection.close()