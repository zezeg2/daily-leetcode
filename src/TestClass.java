import java.io.*;
import java.nio.charset.StandardCharsets;
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

class UserInput {

    public static class TextInput {

        String value = "";

        public void add(char c){
            value += c;
        }

        public String getValue(){
            return this.value;
        }
    }

    public static class NumericInput extends TextInput{
        @Override
        public void add(char c) {
            if (c >= '0' && c <= '9')  super.add(c);
        }
    }

    public static void main(String[] args) {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}

//---------------------------------------------------------------------

class DecoratorStream extends OutputStream
{
    private OutputStream stream;
    private String prefix;


    DecoratorStream(OutputStream stream, String prefix) {
        super();
        this.stream = stream;
        this.prefix = prefix;
    }

    @Override
    public void write(int b) throws IOException {
        byte[] result = new byte[4];

        result[0] = (byte) (b >> 24);
        result[1] = (byte) (b >> 16);
        result[2] = (byte) (b >> 8);
        result[3] = (byte) (b);

        write(result, 0, 4);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (prefix != null) {
            stream.write(prefix.getBytes(StandardCharsets.UTF_8));
            prefix = null;
        }
        stream.write(b, off, len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public static void main(String[] args) throws IOException {
        byte[] message = new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c, 0x20, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x21};
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DecoratorStream decoratorStream = new DecoratorStream(baos, "First line: ");
            decoratorStream.write(message);

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), "UTF-8"))) {
                System.out.println(reader.readLine());  //should print "First line: Hello, world!"
            }
        }
    }
}

class Folders {
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
        List<String> names = new ArrayList<>();
        while (xml.contains("folder name=")){

            xml = xml.substring(xml.indexOf("folder name=\"") + 13);
            String name = xml.substring(0, xml.indexOf("\""));
            if (name.charAt(0) == startingLetter) names.add(name);
            xml = xml.substring(xml.indexOf("\"")+1);

        }


        return names;
    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);

//        String a = "folder name=\"aasdkjh\"";
//
//        System.out.println(a.indexOf("folder name=\""));
//
//        a = a.substring(a.indexOf("folder name=\"") + 13);
//        a = a.substring(a.indexOf("\""));
//        System.out.println(a);
    }
}

