# Tinytimer

Tinytimer is a small web application with which users can create and share small countdown timers.

## Development

The application is written using Clojure for both the frontend and backend.

#### Initial setup

To start working the following tools are necessary:

- npm/yarn
- [Leiningen](https://github.com/technomancy/leiningen)
- [shadow-cljs](https://github.com/thheller/shadow-cljs)

After cloning the repository run `yarn` or `npm install` to download all the frontend dependencies. Furthermore, with

```
lein migrate
```

a SQLite database can be generated with all necessary tables which the application will use.

#### Coding

Usually during development four terminals are necessary:

1. In the first `shadow-cljs` watches for changes concerning the frontend code. It will update the `resources/public/js/dev/main.js` file in case it recognizes any change to the code.

```
shadow-cljs watch dev
```

2. In the second one, `shadow-cljs` provides a REPL which is connected to the browser environment:

```
shadow-cljs cljs-repl dev
```

3. In the third one, leiningen will serve the application and will reload the code upon change:

```
lein ring server
```

4. Lastly, for the backend code another REPL is necessary:

```
lein repl
```

## Testing

Executing the tests is fairly simple.

1. Create/Migrate the testing database: `lein with-profile test migrate`
2. Run the tests: `lein test`

## Production

The easiest way to build and deploy tinytimer is by using docker. Currently the production docker image of tinytimer is build using Github actions. The definition of the jobs is in `.github/workflows/ci.yml`. On production the image can be loaded into the local docker registry and run similar to:

```
sudo docker run \
	--name tinytimer \
	-d \
	--env APP_ENV=production \
	--env DATABASE_URL=jdbc:sqlite:/db/production.db \
	--env GOOGLE_SITE_VERIFICATION=SITE_VERIFICATION_KEY \
	-v /home/tinytimer/db:/db \
       	-p 8080:8080 \
	-t tinytimer:latest
```
