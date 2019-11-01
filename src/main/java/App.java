import dao.SQL2oTreeDao;
import dao.Sql2oClanDao;
import dao.Sql2oUserDao;
import models.Clan;
import models.Tree;
import models.Users;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
        public static void main(String[] args){
                Sql2oClanDao clanDao = new Sql2oClanDao();
                SQL2oTreeDao treeDao = new SQL2oTreeDao();
                Sql2oUserDao userDao = new Sql2oUserDao();
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
                        List<Users> users = userDao.allUsers();
                        model.put("users", users);
                        return new ModelAndView(model, "templates/users.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        List<Clan> clans = clanDao.getAll();
                        model.put("clans", clans);
                        return  new ModelAndView(model, "clans.hbs");
                },new HandlebarsTemplateEngine());

                post("/clans/new",(req, res)->{
                        Map<String , Object> model = new HashMap<String, Object>();
                        return  new ModelAndView(model, "new-clan.hbs");
                }, new HandlebarsTemplateEngine());

                post("/trees/new", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        String name = req.queryParams("treename");
                        String species = req.queryParams("species");
                        String longitude = req.queryParams("longitude");
                        String latitude = req.queryParams("latitude");
                        Tree tree = new Tree(name, species, latitude, longitude);
                        return new ModelAndView(model, "new-tree.hbs");
                }, new HandlebarsTemplateEngine());

                get("/users/:id", (req, res)->{
                        Map<String , Object> model = new HashMap<String, Object>();
                        int id = Integer.parseInt(req.queryParams(":id"));
                        Users user = userDao.findUserById(id);
                        model.put("user", user);
                        return  new ModelAndView(model, "new-User.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans/:id", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        int id = Integer.parseInt(req.queryParams(":id"));
                        Clan clan = clanDao.findById(id);
                        model.put("clan", clan);
                        return  new ModelAndView(model, "new-clan.hbs");
                }, new HandlebarsTemplateEngine());

                get("/trees/:id", (req, res)->{
                        Map<String, Object> model =  new HashMap<String, Object>();
//                        int id = Integer.parseInt(req.queryParams(":id"));
//                        Tree tree = treeDao.findTreeById(id);
//                        model.put("tree", tree);
                        return new ModelAndView(model, "new-tree.hbs");
                }, new HandlebarsTemplateEngine());

                get("/trees", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        List<Tree> trees = treeDao.getAllTrees();
                        model.put("trees", trees);
                        return new ModelAndView(model, "templates/trees.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        List<Clan> clans =  clanDao.getAll();
                        model.put("clans", clans);
                        return new ModelAndView(model, "clans.hbs");
                }, new HandlebarsTemplateEngine());

                get("/clans/:id/users", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        int id = Integer.parseInt(req.queryParams(":id"));
                        Clan clan = clanDao.findById(id);
                        List<Users> users = clanDao.getClanMembers(clan);
                        return new ModelAndView(model, "templates/users.hbs");
                }, new HandlebarsTemplateEngine());


                get("/users/:id/trees", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        int id = Integer.parseInt(req.queryParams(":id"));
                        List<Tree> trees = treeDao.getTreesPlantedByUser(id);
                        model.put("trees", trees);
                        return new  ModelAndView(model, "templates/trees.hbs");
                }, new HandlebarsTemplateEngine());

                get("/trees/add", (req, res)->{
                        Map<String, Object> model = new HashMap<String, Object>();
                        return new ModelAndView(model, "tree-form.hbs");
                }, new HandlebarsTemplateEngine());


        }
}
