FROM ubuntu:latest
LABEL authors="diksh"

ENTRYPOINT ["top", "-b"]