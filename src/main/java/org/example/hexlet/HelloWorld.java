package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {

//        var app = Javalin.create(config -> {
//            config.bundledPlugins.enableDevLogging();
//        });
//        app.get("/", ctx -> ctx.result("Hello World"));
//        app.start(7070);
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));
        app.get("/hello", ctx -> {
            var name  = ctx.queryParamAsClass("name", String.class).getOrDefault("Hello, World!");
            ctx.result(name);
        });
        app.start(7070);


    }
}