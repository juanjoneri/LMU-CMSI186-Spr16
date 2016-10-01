import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MapColorer {

    private static boolean isPosible = true;
    private Region[] regions;
    private static int[] colors = {1, 2, 3, 4};

    public void colorMap() {
        // You fill this in.
        // Use System.out.println() to output the colored map.
        // See the instructions for the proper format.
        // It is okay to write private helper methods in this class.
        // If you're doing things recursively, you will need one.
        //
        // Remember your job is to systematically work your way through
        // Region.getAllRegionsAsArray() calling
        // canColorWith()
        // setColor()
        // removeColor()
        // You will be going forward and backward through the array.

        regions = Region.getAllRegionsAsArray();

        for(int i = 0; i < regions.length; i++){

            if(i == -1){
                isPosible = false;
                return;
            }

            for(int j = 0; j <= colors.length; j++){

                if(j == 4){
                    //Meaning we could not color it
                    regions[i].removeColor();
                    i-=2;
                    break;
                }

//                           has not been assigned a value yet                                                  we have been here before, check if any color larger works
                if( (regions[i].getColor() == null && regions[i].canColorWith(colors[j])) || (regions[i].getColor() != null && j >= regions[i].getColor() && regions[i].canColorWith(colors[j])) ){
                    regions[i].setColor(colors[j]);
                    break;
                }
            }
        }
    }

    public void outputResult(){
        if(isPosible){
            for(Region region : regions){

                System.out.println(region.getName() + ":" + region.getColor());

                for(Region neighbor : region.getNeighbors()){
                    //lots of redundancies but it does the job
                    System.out.println(region.getName() + "," + neighbor.getName());
                }
            }
        } else {
            System.out.println("IMPOSSIBLE MAP");
        }
    }

    public void readMapFromStandardInput() {
        new BufferedReader(new InputStreamReader(System.in))
            .lines()
            .forEach(line -> {
                String[] pair = line.trim().split(",");
                Region region0 = Region.forName(pair[0]);
                Region region1 = Region.forName(pair[1]);
                region0.addNeighbor(region1);
            });
    }

    public static void main(String[] args) {
        MapColorer mapColorer = new MapColorer();
        mapColorer.readMapFromStandardInput();
        mapColorer.colorMap();
        mapColorer.outputResult();
    }
 }

/**
* This class is full of a lot of stuff you don't need to know.
*
* But don't hesitate to ask about such things if you like.
*/
class Region {

    private static Map<String, Region> allRegions = new TreeMap<>();

    public static Region forName(String name) {
        allRegions.putIfAbsent(name, new Region(name));
        return allRegions.get(name);
    }

    public static Region[] getAllRegionsAsArray() {
        return allRegions.values().toArray(new Region[allRegions.size()]);
    }

    private String name;
    private Integer color;
    private Set<Region> neighbors = new HashSet<>();

    private Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getColor() {
        return color;
    }

    public boolean canColorWith(int color) {
        return !neighbors.stream().anyMatch(n -> Objects.equals(n.color,color));
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void removeColor() {
        this.color = null;
    }

    public Set<Region> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Region region) {
        Objects.requireNonNull(region);
        neighbors.add(region);
        region.neighbors.add(this);
    }
}
