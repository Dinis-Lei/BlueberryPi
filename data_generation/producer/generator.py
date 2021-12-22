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

def generate_temperature(sensor):
    delta = rng.normal(0, 0.1)
    delta = -abs(delta) if sensor.value > 20 else delta
    sensor.value += delta
    #print(msg, sensor.prob)

def generate_temperature_alert(sensor):
    mult = -1 if sensor.value > 25 else 1
    delta = (abs(rng.normal(0, 0.3)) if sensor.value < 21.5 else abs(rng.normal(0, 0.1))) * mult
    sensor.value += delta
    #print(msg, sensor.prob)
    
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
    
    plant_temp_Guarda = Sensor("Guarda","plantation_temperature",generate_temperature,generate_temperature_alert,[0,0],[0.2,0.9],19)
    plant_temp_Minho = Sensor("Minho","plantation_temperature",generate_temperature,generate_temperature_alert,[0,0],[0.2,0.9],19)
    plant_temp_VilaReal = Sensor("Vila Real","plantation_temperature",generate_temperature,generate_temperature_alert,[0,0],[0.2,0.9],19)

    count = 0
    while 1:
        time.sleep(1/60) # para efeitos de teste, 1 segundo em tempo real corresponderá a um minuto simulado pelo gerador!
        # geradores segundo a segundo
        
        if count % 60 == 0:
            # geradores minuto a minuto
            plant_temp_Guarda.generate(channel) 
            plant_temp_Minho.generate(channel)
            plant_temp_VilaReal.generate(channel)
            pass
        elif count % 3600 == 0:
            # geradores hora a hora
            pass
        count += 1

    connection.close()