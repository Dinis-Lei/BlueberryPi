import random
import json
import time
import pika

class Sensor:
    def __init__(self, local, sensor_type, funcgood, funcbad) -> None:
        self.local = local
        self.sensor_type = sensor_type
        self.funcgood = funcgood
        self.funcbad = funcbad
        self.alert = False
        self.prob = 5
        self.start_prob = [5, 15]

    def change_state(self):
        r = random.randint(0,100)
        if r < self.prob:
            self.alert = not self.alert
            self.prob = self.start_prob[self.alert]
        else:
            self.prob += 1

    def generate(self):
        self.funcgood() if self.alert else self.funcbad()


    def __str__(self) -> str:
        pass




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