

TCP
Es un protocolo orientado a la conexión. Consiste en enviar datos de la maquina A a la B, cuando a B le llegan 
datos confirma su recepción.
El protocolo TCP consta de dos clases Java:
La clase Servidor que va a crear un socket a través de ServerSocket para
que nosotros le mandemos un parámetro que indique el puerto por el que se va a establecer la comunicación.
Posteriormente para crear la conexión entre Cliente-Servidor se usará el método socket.accept para iniciar la conexion.
Por parte del cliente se va a crear otro socket al que le va a llegar un Host y el puerto por el que se conecta con el Servidor.

UDP
Es un protocolo no orientado a conexión se lleva a cabo cuando el equipo A le envía paquetes de información al equipo
B, el flujo se realiza en una sola dirección. Todo esto se realiza sin haber hecho un conexión con la máquina B por lo
tanto no se realiza la confirmación de B a A. La única información que el receptor conoce de A es la dirección IP.
