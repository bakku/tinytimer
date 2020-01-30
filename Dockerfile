FROM clojure:openjdk-8-lein AS builder

RUN curl -sL https://deb.nodesource.com/setup_13.x | bash - && \
	apt-get install -y nodejs

RUN npm install -g shadow-cljs

# Force installation of shadow-cljs dependencies
RUN echo "{:source-paths [] :dependencies [] :builds {}}" > shadow-cljs.edn
RUN shadow-cljs classpath

WORKDIR /usr/src/app

COPY project.clj /usr/src/app/

RUN lein deps

COPY package.json /usr/src/app

RUN npm install

COPY . /usr/src/app

RUN shadow-cljs release prod

RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" /usr/bin/app-standalone.jar

FROM openjdk:8-alpine
COPY --from=builder /usr/bin/app-standalone.jar .
CMD ["java", "-jar", "app-standalone.jar"]
