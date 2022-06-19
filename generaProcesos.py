#tiempoEnCPU,periodoES,esperaES,prioridad (1-99),tipo (Usuario o SO)
from random import randint

procesos = ""
for x in range(0, 500):
    procesos += str(randint(2000, 100000))
    procesos += "," + str(randint(2000, 100000))
    procesos += "," + str(randint(2000, 20000))
    prioridad = randint(1, 99)
    tipo = "Usuario"
    procesos += "," + str(prioridad)
    if prioridad <= 15:
        tipo = "SO"
    procesos += "," + tipo + "\n"

f = open("procesos.txt", "w")
f.write(procesos)
f.close()