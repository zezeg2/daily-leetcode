import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

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
        return new Roots(x1, x2);
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

        public void add(char c) {
            value += c;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static class NumericInput extends TextInput {
        @Override
        public void add(char c) {
            if (c >= '0' && c <= '9') super.add(c);
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


class TwoSum {
    public static int[] findTwoSum(int[] list, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            int findElement = sum - list[i];
            if (map.get(findElement) != null) return new int[]{i, map.get(findElement)};
            map.put(list[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[]{3, 1, 5, 7, 5, 9}, 10);
        if (indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        }
    }
}

//---------------------------------------------------------------------

class DecoratorStream extends OutputStream {
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
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DecoratorStream decoratorStream = new DecoratorStream(baos, "First line: ");
            decoratorStream.write(message);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), "UTF-8"))) {
                System.out.println(reader.readLine());  //should print "First line: Hello, world!"
            }
        }
    }
}

class Folders {

    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);
        NodeList node = doc.getElementsByTagName("folder");
        Collection<String> result = new ArrayList<>();
        for (int i = 0; i < node.getLength(); i++) {
            String nodeName = node.item(i).getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.toCharArray()[0] == startingLetter) {
                result.add(nodeName);
            }
        }
        return result;
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
        for (String name : names)
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

class Node {
    public int value;
    public Node left, right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

class BinarySearchTree {
    public static boolean contains(Node root, int value) {
        if (root.value == value) return true;
        else if (root.value > value) {
            if (root.left == null) return false;
            return contains(root.left, value);
        } else {
            if (root.right == null) return false;
            return contains(root.right, value);
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        System.out.println(contains(n2, 3));
    }
}

class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public Song getNextSong() {
        return nextSong;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        Set<Song> list = new HashSet<>();
        Song current = this;
        while (current.getNextSong() != null) {
            if (list.contains(current.getNextSong())) {
                return true;
            }
            list.add(current);
            current = current.getNextSong();
        }
        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}

class SortedSearch {

    public static int countNumbers1(int[] sortedArray, int lessThan) {
        int start = 0;
        int end = sortedArray.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (sortedArray[mid] < lessThan) {
                if (mid < sortedArray.length - 1 && sortedArray[mid + 1] < lessThan) { // check id next value is also valid
                    start = mid + 1;
                    continue;
                } else
                    return mid + 1;
            }

            if (sortedArray[mid] >= lessThan) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;

    }
    public static int countNumbers(int[] sortedArray, int lessThan) {
        int start = 0;
        int end = sortedArray.length - 1;
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;
            if (sortedArray[mid] < lessThan) {
                if (mid < sortedArray.length - 1 && sortedArray[mid + 1] < lessThan) { // check id next value is also valid
                    start = mid + 1;
                    continue;
                } else
                    return mid + 1;
            }
            if (sortedArray[mid] >= lessThan) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[]{0,1,2,3,5,7,9,11,14}, 4));
    }
}


class TrainComposition {

    LinkedList<Integer> wagons = new LinkedList<Integer>();
    public void attachWagonFromLeft(int wagonId) {
        wagons.addFirst(wagonId);
    }
    public void attachWagonFromRight(int wagonId) {
        wagons.addLast(wagonId);
    }
    public int detachWagonFromLeft() {
        if (!wagons.isEmpty()) return wagons.removeFirst();
        else throw new IndexOutOfBoundsException("No wagons available");
    }
    public int detachWagonFromRight() {
        if (!wagons.isEmpty()) return wagons.removeLast();
        else throw new IndexOutOfBoundsException("No wagons available");
    }
    public static void main (String[] args){
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(7);
        train.attachWagonFromLeft(13);
        System.out.println(train.detachWagonFromRight()); // 7
        System.out.println(train.detachWagonFromLeft()); // 13
    }
}


