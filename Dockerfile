FROM openjdk:11
EXPOSE 8080
RUN mkdir ./app
COPY ./build/libs/gif-currency-1.0.jar ./app
CMD java -jar ./app/gif-currency-1.0.jar