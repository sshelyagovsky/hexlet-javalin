package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));
        app.get("/hello", ctx -> {
            var name  = ctx.queryParamAsClass("name", String.class).getOrDefault("Hello, World!");
            ctx.result(name);
        });
        app.get("/users/{id}/post/{postId}", ctx -> {
            var id = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");
            ctx.result("UserId: " + id + " PostId: " + postId);
            });

        app.start(7070);
    }
}