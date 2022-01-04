import random
import json
import time
import pika
from numpy.random import default_rng

rng = default_rng()

class Sensor:
    def __init__(self, local, sensor_type, funcgood, funcbad, start_prob=[5,15], prob_delta=[1,1], value=None, step=0) -> None:
        self.local = local
        self.sensor_type = sensor_type
        self.funcgood = funcgood
        self.funcbad = funcbad
        self.alert = False
        self.prob = start_prob[0]
        self.start_prob = start_prob
        self.prob_delta = prob_delta
        self.value = value
        self.ts = 0
        self.step = step

    def change_state(self):
        r = random.randint(0,100)
        if r < self.prob:
            self.alert = not self.alert
            self.prob = self.start_prob[self.alert]
            #print("!!!!!!!!! CHANGED STATE !!!!!!!!!!", self.alert)
        else:
            self.prob += self.prob_delta[self.alert]

    def generate(self, channel):
        self.funcgood(self) if not self.alert else self.funcbad(self)
        msg = json.dumps({"key": self.sensor_type, "timestamp": self.ts, "temp": self.value, "location": self.local})
        channel.basic_publish(exchange='', routing_key='blueberry', body=msg)
        self.change_state()
        self.ts += self.step

def generate_ph(sensor):
    sensor.value = rng.normal(5, 0.4)

def generate_ph_alert(sensor):
    sensor.value = rng.normal(5, 0.9)

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

    sensors = []

    sensors.append(Sensor("Guarda","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5,(24*60*60)))
    sensors.append(Sensor("Minho","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5,(24*60*60)))
    sensors.append(Sensor("Vila Real","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5,(24*60*60)))

    curr_time = 0
    while 1:        
        big = curr_time
        for sensor in sensors:
            if sensor.ts <= curr_time:
                sensor.generate()
                if sensor.ts > big: big = sensor.ts
        curr_time = big
        time.sleep(1) # ajustar para acelerar/desacelerar o tempo (testing purposes)

    connection.close()