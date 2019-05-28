import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("template", "templates/index.vtl");
          model.put("stylists", Stylist.all());
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        get("/stylists", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("stylist", Stylist.all());
          model.put("template", "templates/stylists.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        post("/stylists", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          String name = request.queryParams("name");
          int age = Integer.parseInt(request.queryParams("age"));
          String phoneNumber = request.queryParams("phone_number");
          String idNumber = request.queryParams("id_number");
          String email = request.queryParams("email");
          int clientCount = 0;
          Stylist newStylist = new Stylist(name, age, idNumber, phoneNumber, email, clientCount);
          newStylist.save();
          model.put("template", "templates/index.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

      }
}
