import java.util.*;

class TestClass {
}

interface AlertDAO {
    UUID addAlert(Date time);

    Date getAlert(UUID id);
}

class AlertService {
    private final AlertDAO storage;

    public AlertService(AlertDAO alertDAO) {
        this.storage = alertDAO;
    }

    public UUID raiseAlert() {
        if (storage == null) {

        }
        return this.storage.addAlert(new Date());
    }

    public Date getAlertTime(UUID id) {
        return this.storage.getAlert(id);
    }
}

class MapAlertDAO implements AlertDAO {
    private final Map<UUID, Date> alerts = new HashMap<UUID, Date>();

    @Override
    public UUID addAlert(Date time) {
        UUID id = UUID.randomUUID();
        this.alerts.put(id, time);
        return id;
    }

    @Override
    public Date getAlert(UUID id) {
        return this.alerts.get(id);
    }
}

//------------------------------------------------------------------------------
class IceCreamMachine {
    public String[] ingredients;
    public String[] toppings;

    public static class IceCream {
        public String ingredient;
        public String topping;

        public IceCream(String ingredient, String topping) {
            this.ingredient = ingredient;
            this.topping = topping;
        }
    }

    public IceCreamMachine(String[] ingredients, String[] toppings) {
        this.ingredients = ingredients;
        this.toppings = toppings;
    }

    public List<IceCream> scoops() {
        List<IceCream> list = new ArrayList<>();
        for (String s1 : ingredients) {
            for (String s2 : toppings) {
                list.add(new IceCream(s1, s2));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        IceCreamMachine machine = new IceCreamMachine(new String[]{
                "vanilla", "chocolate"
        }, new String[]{
                "chocolate sauce"
        });
        List<IceCream> scoops = machine.scoops();

        /*
         * Should print:
         * vanilla, chocolate sauce
         * chocolate, chocolate sauce
         */
        for (IceCream iceCream : scoops) {
            System.out.println(iceCream.ingredient + ", " + iceCream.topping);
        }
    }
}

//-----------------------------------------------------------------

class MergeNames {

        public static String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> set = new TreeSet<>();
        set.addAll(Arrays.asList(names1));
        set.addAll(Arrays.asList(names2));
        return new String[]{Arrays.toString(set.toArray())};
    }

    public static void main(String[] args) {
        String[] names1 = new String[]{"Ava", "Emma", "Olivia"};
        String[] names2 = new String[]{"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
    }
}


class QuadraticEquation {
    public static Roots findRoots(double a, double b, double c) {
        double x1 = 0;
        double x2 = 0;
        double discriminant = Math.pow(b, 2) - 4 * (a * c);
        x1 = ((-b) + Math.sqrt(discriminant)) / (2 * a);
        x2 = ((-b) - Math.sqrt(discriminant)) / (2 * a);
        return new Roots(x1,x2);
    }

    public static void main(String[] args) {
        Roots roots = QuadraticEquation.findRoots(2, 10, 8);
        System.out.println("Roots: " + roots.x1 + ", " + roots.x2);
    }
}

class Roots {
    public final double x1, x2;

    public Roots(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }
}

