import models.Users;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
        public static void main(String[] args){
                staticFileLocation("public");


                get("/", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "index.hbs");
                }, new HandlebarsTemplateEngine());

                post("/users/new", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        String name = req.queryParams("name");
                        String username = req.queryParams("username");
                        String email = req.queryParams("email");
                        String clan = req.queryParams("clan");
                        String password = req.queryParams("password");
                        Users user = new Users(username, password);
                        model.put("user", user);
                        return new ModelAndView(model, "new-User.hbs");
                }, new HandlebarsTemplateEngine());

                get("/users", (req, res)->{
                        Map<String,Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "users.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return  new ModelAndView(model, "clans.hbs");
                },new HandlebarsTemplateEngine());

                post("/clans/new",(req, res)->{
                        Map<String , Object> model = new HashMap<String, Object>();
                        return  new ModelAndView(model, "new-clan.hbs");
                }, new HandlebarsTemplateEngine());

                post("/trees/new", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "new-tree.hbs");
                }, new HandlebarsTemplateEngine());

                get("/users/:id", (req, res)->{
                        Map<String , Object> model = new HashMap<String, Object>();
                        return  new ModelAndView(model, "new-User.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans/:id", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return  new ModelAndView(model, "new-clan.hbs");
                }, new HandlebarsTemplateEngine());

                get("/trees/:id", (req, res)->{
                        Map<String, Object> model =  new HashMap<String, Object>();
                        return new ModelAndView(model, "new-tree.hbs");
                }, new HandlebarsTemplateEngine());

                get("/trees", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "trees.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "clans.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans/:id/users", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "users.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans/:id/trees", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "trees.hbs");
                }, new HandlebarsTemplateEngine());

                get("/users/:id/trees", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new  ModelAndView(model, "trees.hbs");
                }, new HandlebarsTemplateEngine());



        }
}
