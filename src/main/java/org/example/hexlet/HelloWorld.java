package org.example.hexlet;

import io.javalin.Javalin;
import org.example.hexlet.data.DataCourses;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {

         List<Course> COURSES = DataCourses.getCourses();

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
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

        app.get("/courses", ctx -> {
            var header = "IT programming courses";
            var page = new CoursesPage(COURSES, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");

            var filteredCourse = COURSES.stream()
                    .filter(course -> course.getId().equals(Long.parseLong(id)))
                    .collect(Collectors.toCollection(ArrayList::new));

            var header = "IT programming courses";
            var page = new CoursesPage(filteredCourse, header);
            ctx.render("courses/index.jte", model("page", page));
        });


        app.start(7070);
    }
}