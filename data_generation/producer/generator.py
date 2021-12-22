import random
import json
import time
import pika
from numpy.random import default_rng

rng = default_rng()

class Sensor:
    def __init__(self, local, sensor_type, funcgood, funcbad, start_prob=[5,15], prob_delta=[1,1], value=None) -> None:
        self.local = local
        self.sensor_type = sensor_type
        self.funcgood = funcgood
        self.funcbad = funcbad
        self.alert = False
        self.prob = start_prob[0]
        self.start_prob = start_prob
        self.prob_delta = prob_delta
        self.value = value

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
        msg = json.dumps({"key": "plantation_temperature", "timestamp": int(time.time()), "temp": self.value, "location": self.local})
        channel.basic_publish(exchange='', routing_key='blueberry', body=msg)
        self.change_state()

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

    ph_Guarda = Sensor("Guarda","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5)
    ph_Minho = Sensor("Minho","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5)
    ph_VilaReal = Sensor("Vila Real","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5)

    count = 0
    while 1:
        time.sleep(1/(3600 * 24)) # ajustar para acelerar/desacelerar o tempo (testing purposes)
        # geradores segundo a segundo

        if count % 60 == 0:
            # geradores minuto a minuto

            pass
        if count % 3600 == 0:
            # geradores hora a hora
            
            pass
        if count % (3600 * 6) == 0:
            # geradores 6 em 6 horas

            pass
        if count % (3600 * 24) == 0:
            # geradores diários
            ph_Guarda.generate(channel)
            ph_Minho.generate(channel)
            ph_VilaReal.generate(channel)
            pass
        count += 1

    connection.close()