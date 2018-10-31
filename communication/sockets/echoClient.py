import socket
import sys

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print("Usage: python3 echoClient.py <host name> <port number>")
        exit(1)

    hostname = sys.argv[1]
    portNumber = int(sys.argv[2])

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((hostname, portNumber))

    userInput = input().encode()
    while (userInput != None):
        s.send(userInput)
        s.send('\n'.encode())
        print('sent')
        data = s.recv(1024)
        print('echo: ' + repr(data))
        userInput = input().encode()
            
    s.close()
