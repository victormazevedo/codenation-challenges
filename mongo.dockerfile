FROM mongo:latest
LABEL maintainer="Italo Barbosa"
ENTRYPOINT [ "bin/bash" ]
RUN mongo
RUN use mapfood
EXPOSE 27017