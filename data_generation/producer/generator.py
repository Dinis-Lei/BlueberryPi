from numpy.random import default_rng
import random
import json
import time
import pika

rng = default_rng()

start = 1640995200

alarm_period = 60*60*24*1

class Sensor:
    def __init__(self, local, sensor_type, funcgood, funcbad, start_prob=[5,15], prob_delta=[1,1], value=None, step=0) -> None:
        self.local = local
        self.sensor_type = sensor_type
        self.funcgood = funcgood
        self.funcbad = funcbad
        self.alert = False
        self.prob = start_prob[0]
        self.start_prob = start_prob
        self.prob_delta = [(step/alarm_period)*100, 30]
        self.value = value
        self.ts = start
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
        msg = json.dumps({"key": self.sensor_type, "timestamp": self.ts, "val": self.value, "location": self.local})
        print(msg)
        channel.basic_publish(exchange='', routing_key='blueberry', body=msg)
        self.change_state()
        self.ts += self.step

def generate_unit_loss(sensor):
    sensor.value = rng.normal(2.5, 0.7)
    if sensor.value < 0: sensor.value = 0

def generate_unit_loss_alert(sensor):
    sensor.value = rng.normal(4, 1)
    if sensor.value < 0: sensor.value = 0

def generate_stor_temp(sensor):
    sensor.value = 1.5
    delta = rng.normal(0, 0.4) if sensor.value < 1 else -abs(rng.normal(0, 0.4))
    sensor.value += delta
    sensor.alert = sensor.value > 1 or sensor.value < 0 

def generate_stor_temp_alert(sensor):
    delta = rng.normal(0, 0.4)
    sensor.value += abs(delta)

def generate_stor_humidity(sensor):
    sensor.value = rng.normal(92.5, 0.7)
    if sensor.value < 85.5: sensor.value = 85.5

def generate_stor_humidity_alert(sensor):
    sensor.value = rng.normal(85, 0.7)

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

def generate_net_harv(sensor):
    sensor.value = rng.normal(8, 0.5)
    if sensor.value < 0: sensor.value = 0

def generate_net_harv_alert(sensor):
    sensor.value = rng.normal(5, 0.5)
    if sensor.value < 0: sensor.value = 0

def generate_ph(sensor):
    sensor.value = rng.normal(5, 0.4)
    if sensor.value < 0: sensor.value = 0

def generate_ph_alert(sensor):
    sensor.value = rng.normal(5, 0.9)
    if sensor.value < 0: sensor.value = 0

#
# Caso seja 6h da manhã tentar simular a rega da plantação, logo haverá um pico na tensão da água 
#
def generate_water(sensor):
    if  sensor.ts/(3600) == 6:
        sensor.value = rng.normal(15, 2)
    else:
        delta = abs(rng.normal(0, 0.1))
        sensor.value += delta

def generate_water_alert(sensor):
    delta = abs(rng.normal(0, 1))
    sensor.value += delta


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
    
    sensors.append(Sensor("Guarda","store_humidity",generate_stor_humidity,generate_stor_humidity_alert,[0,0],[10,20],92.5,60))
    sensors.append(Sensor("Minho","store_humidity",generate_stor_humidity,generate_stor_humidity_alert,[0,0],[10,20],92.5,60))
    sensors.append(Sensor("VilaReal","store_humidity",generate_stor_humidity,generate_stor_humidity_alert,[0,0],[10,20],92.5,60))
    sensors.append(Sensor("Guarda","unit_loss",generate_unit_loss,generate_unit_loss_alert,[0,0],[10,25],1,7*24*60*60))
    sensors.append(Sensor("Minho","unit_loss",generate_unit_loss,generate_unit_loss_alert,[0,0],[10,25],1,7*24*60*60))
    sensors.append(Sensor("VilaReal","unit_loss",generate_unit_loss,generate_unit_loss_alert,[0,0],[10,25],1,7*24*60*60))
    sensors.append(Sensor("Guarda","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5,(24*60*60)))
    sensors.append(Sensor("Minho","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5,(24*60*60)))
    sensors.append(Sensor("VilaReal","ph",generate_ph,generate_ph_alert,[0,0],[10,30],5,(24*60*60)))

    sensors.append(Sensor("Guarda","plantation_temperature",generate_temperature,generate_temperature_alert,[0,0],[0.2,0.9],19,60))
    sensors.append(Sensor("Minho","plantation_temperature",generate_temperature,generate_temperature_alert,[0,0],[0.2,0.9],19,60))
    sensors.append(Sensor("VilaReal","plantation_temperature",generate_temperature,generate_temperature_alert,[0,0],[0.2,0.9],19,60))

    sensors.append(Sensor("Guarda","net_harvest",generate_net_harv,generate_net_harv_alert,[0,0],[10,30],5,(24*60*60)))
    sensors.append(Sensor("Minho","net_harvest",generate_net_harv,generate_net_harv_alert,[0,0],[10,30],5,(24*60*60)))
    sensors.append(Sensor("VilaReal","net_harvest",generate_net_harv,generate_net_harv_alert,[0,0],[10,30],5,(24*60*60)))

    sensors.append(Sensor("Guarda","water_tension",generate_water,generate_water_alert,[0,0],[0.2,0.9],40,60))
    sensors.append(Sensor("Minho","water_tension",generate_water,generate_water_alert,[0,0],[0.2,0.9],40,60))
    sensors.append(Sensor("VilaReal","water_tension",generate_water,generate_water_alert,[0,0],[0.2,0.9],40,60))

    sensors.append(Sensor("Guarda","store_temperature",generate_stor_temp,generate_stor_temp_alert,[0,0],[10,25],0.5,60))
    sensors.append(Sensor("Minho","store_temperature",generate_stor_temp,generate_stor_temp_alert,[0,0],[10,25],0.5,60))
    sensors.append(Sensor("VilaReal","store_temperature",generate_stor_temp,generate_stor_temp_alert,[0,0],[10,25],0.5,60))

    curr_time = 1640995200
    time.sleep(30)
    while 1:        
        big = curr_time
        for sensor in sensors:
            if sensor.ts <= curr_time:
                sensor.generate(channel)
                if sensor.ts > big: big = sensor.ts
        curr_time += 1
        time.sleep(1/60) # ajustar para acelerar/desacelerar o tempo (testing purposes)

    connection.close()
