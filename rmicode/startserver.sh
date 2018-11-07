#!/bin/bash
rmiregistry &
java -Djava.rmi.server.codebase=file:. example.hello.Server
