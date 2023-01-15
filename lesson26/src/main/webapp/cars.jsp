<%@ page import="com.tms.model.Car" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>

<%! Map<String, Car> cars = getCars(); %>
<%! public Map<String, Car> getCars() {
    Map<String, Car> cars = new HashMap<>();
    cars.put("1", new Car("Audi", "8000"));
    cars.put("2", new Car("BMW", "10000"));
    cars.put("3", new Car("Ford", "15000"));
    return cars;
}%>
<body>
<div class="container" class="position-absolute top-50 start-50 translate-middle">
    <div class="row">
        <div class="col-3">
            <form>
                <input type="number" placeholder="Enter car id:" name="id_for_get" min="1" max="20">
                <button type="submit">Search</button>
            </form>
        </div>

        <div class="col-4">
            <h2>Table with cars</h2>
            <table class="table" class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">cars</th>
                </tr>
                </thead>
                <tbody>
                <%
                    String id = request.getParameter("id_for_get");
                    if (id != null) {
                        if (id.isBlank()) {
                            for (String key : cars.keySet()) {
                                out.print("<tr><td>" + key + "</td>\n");
                                out.println("<td> " + cars.get(key) + "</td></tr>\n");
                            }
                        } else if (cars.get(id) == null) {
                            out.println("Specified id isn't valid");
                        } else {
                            out.print("<tr><td>" + id + "</td>\n");
                            out.println("<td> " + cars.get(id) + "</td></tr>\n");
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-5">
            <form>
                <input type="number" placeholder="Enter id:" name="id_for_create" min="1" max="20">
                <input type="text" placeholder="Enter model:" name="model">
                <input type="text" placeholder="Enter cost:" name="cost">
                <button type="submit">Create car</button>
            </form>

            <% String idForCreate = request.getParameter("id_for_create");
                String model = request.getParameter("model");
                String cost = request.getParameter("cost");
                if (idForCreate != null) {
                    if (idForCreate.isBlank() || model.isBlank() || cost.isBlank()) {
                        out.print("Specified parameters aren't valid");
                    } else if (cars.get(idForCreate) != null) {
                        out.print("Car with this id already exists");
                    } else {
                        cars.put(idForCreate, new Car(model, cost));
                    }
                }
            %>
            <form>
                <input type="number" placeholder="Enter id:" name="id_for_delete" min="1" max="20">
                <button type="submit">Delete car</button>
            </form>

            <% String idForDelete = request.getParameter("id_for_delete");
                if (idForDelete != null) {
                    if (!idForDelete.isBlank() && cars.get(idForDelete) != null) {
                        cars.remove(idForDelete);
                    }
                }
            %>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>

</html>