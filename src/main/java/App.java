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
          model.put("stylists", Stylist.all());
          model.put("template", "templates/stylists.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        get("/stylists/new-stylists", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("stylist", Stylist.all());
          model.put("template", "templates/stylist-form.vtl");
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
        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        post("/clients", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
          String firstName = request.queryParams("first_name");
          String lastName = request.queryParams("last_name");
          String phoneNumber = request.queryParams("phone_number");
          String email = request.queryParams("email");
          Client newClient = new Client(firstName, lastName, email, phoneNumber, stylist.getId());
          newClient.save();
          model.put("stylist", stylist);
          model.put("template", "templates/stylist.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        post("/stylists/:stylist_id/clients/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Client client = Client.find(Integer.parseInt(request.params("id")));
          int stylistId = Integer.parseInt(request.queryParams("stylistId"));
          String phoneNumber = request.queryParams("phoneNumber");
          String email = request.queryParams("email");
          Stylist stylist = Stylist.find(client.getStylistId());
          client.update(phoneNumber, email, stylistId);
          String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
          model.put("template", "templates/client.vtl");
          response.redirect(url);
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        get("/stylists/:stylist_id/clients/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
          Client client = Client.find(Integer.parseInt(request.params(":id")));
          model.put("stylist", stylist);
          model.put("client", client);
          model.put("stylists", Stylist.all());
          model.put("template", "templates/client.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Client client = Client.find(Integer.parseInt(request.params("id")));
          Stylist stylist = Stylist.find(client.getStylistId());
          client.delete();
          model.put("stylist", stylist);
          model.put("template", "templates/stylist.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        post("/stylists/:stylist_id/delete", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
          stylist.delete();
          model.put("stylist", stylist);
          model.put("template", "templates/stylists.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        get("/clients", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("clients", Client.all());
          model.put("template", "templates/clients.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        get("/clients/new-clients", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("stylists", Stylist.all());
          model.put("clients", Client.all());
          model.put("template", "templates/new-client.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


      }
}
