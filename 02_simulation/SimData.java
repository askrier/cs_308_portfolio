package XML;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

/**
 * Holds all the necessary data to start a simulation
 * Usually constructed from the XMLParser class
 * Errors held in DataException.java
 * Most data fields are just passed in and held as is
 * The vals data field is parsed into a List<List<Integer>> array
 * Assumes that all the data values passed to it are of the same type it needs
 * @author Andrew Krier
 */

public class SimData {

    public static final String DATA_TYPE = "simulation";
    public static final List<String> DATA_FIELDS = List.of(
            "author",
            "sim_type",
            "cell_sides",
            "cell_rows",
            "cell_columns",
            "spread_probability",
            "vals",
            "wrap_type",
            "neighbor_type"
    );

    private static final int AUTHOR_INDEX = 0;
    private static final int SIM_TYPE_INDEX = 1;
    private static final int CELL_SIDES_INDEX = 2;
    private static final int CELL_ROWS_INDEX = 3;
    private static final int CELL_COLUMNS_INDEX = 4;
    private static final int SPREAD_PROBABILITY_INDEX = 5;
    private static final int VALS_INDEX = 6;
    private static final int WRAP_INDEX = 7;
    private static final int NEIGHBOR_INDEX = 8;

    private static final int NUM_CELL_TYPES = 3;
    private static final int NUM_LIFE_CELL_TYPES = 2;
    private static final int NUM_RPS_CELL_TYPES = 5;

    private static final int SQUARE_SIDES = 4;
    private static final int TRIANGLE_SIDES = 3;
    private static final int HEX_SIDES = 6;

    private String mySimType;
    private String myAuthor;
    private int myCellSides;
    private int myCellRows;
    private int myCellColumns;
    private String myVals;
    private double mySpreadProb;
    private List<List<Integer>> myCellVals;
    private String myWrapType;
    private String myNeighborType;
    private boolean isRandom;

    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private static final String TITLES = "UserInterface";
    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ TITLES);

    private final String INVALID_CHAR_ERROR = myResources.getString("CharError");
    private final String OUT_OF_GRID_ERROR = myResources.getString("GridError");
    private final String INVALID_SHAPE_ERROR = myResources.getString("ShapeError");

    private final String LIFE_CELL = myResources.getString("LifeCell");
    private final String FIRE_CELL = myResources.getString("FireCell");
    private final String RPS_CELL = myResources.getString("RPSCell");

    /**
     * Primary constructor to load all the values into their private locations
     * Only used by the Map constructor
     * @param author
     * @param simType
     * @param cellSides
     * @param cellRows
     * @param cellColumns
     * @param vals
     * @param spreadProb
     * @param wrapType
     * @param neighborType
     */

    public SimData (String author, String simType, int cellSides, int cellRows, int cellColumns, String vals, double spreadProb, String wrapType, String neighborType) {
        mySimType = simType;
        myAuthor = author;
        myCellSides = cellSides;
        myCellRows = cellRows;
        myCellColumns = cellColumns;
        myVals = vals;
        mySpreadProb = spreadProb;
        myWrapType = wrapType;
        myNeighborType = neighborType;
    }

    /**
     * Calls other constructor with the mapped values, and initializes the val array
     * Checks to ensure the shape is valid
     * @param dataValues
     */
    public SimData (Map<String, String> dataValues) {
        this(dataValues.get(DATA_FIELDS.get(AUTHOR_INDEX)),
                dataValues.get(DATA_FIELDS.get(SIM_TYPE_INDEX)),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(CELL_SIDES_INDEX))),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(CELL_ROWS_INDEX))),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(CELL_COLUMNS_INDEX))),
                dataValues.get(DATA_FIELDS.get(VALS_INDEX)),
                Float.parseFloat(dataValues.get(DATA_FIELDS.get(SPREAD_PROBABILITY_INDEX))),
                dataValues.get(DATA_FIELDS.get(WRAP_INDEX)),
                dataValues.get(DATA_FIELDS.get(NEIGHBOR_INDEX)));
        setVals();
        checkShape();
    }

    /**
     * Getter for simType
     * @return Simulation type
     */
    public String getSimType () { return mySimType; }

    /**
     * Getter for author
     * @return Author name
     */
    public String getAuthor () { return myAuthor; }

    /**
     * Getter for cellRows
     * @return Number of rows
     */
    public int getRows () { return myCellRows; }

    /**
     * Getter for cellColumns
     * @return Number of columns
     */
    public int getColumns () { return myCellColumns; }

    /**
     * Getter for cellSides
     * @return Number of sides on the desired shape
     */
    public int getShape () { return myCellSides; }

    /**
     * Getter for spreadProb
     * @return Probability that fire will spread
     */
    public double getSpreadProb () { return mySpreadProb; }

    /**
     * Getter for valList
     * @return List of List of cell values
     */
    public List<List<Integer>> getValList () { return myCellVals; }

    /**
     * Getter for wrapType
     * @return What type of wrap the simulation uses
     */
    public String getWrapType () { return myWrapType; }

    /**
     * Getter for neighborHoodType
     * @return What kind of neighborhood the simulation uses
     */
    public String getNeighborType () { return myNeighborType; }

    private void checkShape() {
        if(isGoodShape()) { throw new DataException(INVALID_SHAPE_ERROR); }
    }

    private boolean isGoodShape() { return myCellSides != SQUARE_SIDES && myCellSides != TRIANGLE_SIDES && myCellSides != HEX_SIDES;}

    private void setVals () {
        int temp;
        myCellVals = new ArrayList<>();
        Random rand = new Random();

        for(int i = 0; i < myCellRows; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < myCellColumns; j++) {
                temp = retVal(isBorder(i, j), rand);
                row.add(temp);
            }
            myCellVals.add(row);
        }

        if(!myVals.isEmpty() && !isRandom) throw new DataException(OUT_OF_GRID_ERROR);
    }

    private int retVal (boolean isBorder, Random rand) {

        int limit = retLimit();

        if(startRandom()) {
            isRandom = true;
            if(mySimType.equals(FIRE_CELL) && isBorder) { return 0; }
            return rand.nextInt(limit);
        }
        int temp;
        try {
            temp = Integer.parseInt(myVals.substring(0, 1));
        } catch (Exception e) {
            throw new DataException(INVALID_CHAR_ERROR);
        }
        if (temp >= limit || temp < 0) {
            try {
                temp = temp % limit;
            } catch(Exception e) {
                throw new DataException(INVALID_CHAR_ERROR);
            }
        }
        myVals = myVals.substring(1);

        return temp;
    }

    private boolean startRandom () { return isRandom || myVals.charAt(0) == 'r' || myVals.charAt(0) == 'R'; }

    private int retLimit() {
        if(mySimType.equals(LIFE_CELL)) { return NUM_LIFE_CELL_TYPES; }
        else if(mySimType.equals(RPS_CELL)) { return NUM_RPS_CELL_TYPES; }
        else { return NUM_CELL_TYPES; }
    }

    private boolean isBorder (int x, int y) {
        return x == 0 || x == myCellRows - 1 || y == 0 || y == myCellColumns - 1;
    }

}

