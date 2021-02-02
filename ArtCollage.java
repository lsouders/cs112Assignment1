/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author:
 *
 *************************************************************************/

import java.awt.Color;

import org.w3c.dom.css.RGBColor;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {

        // WRITE YOUR CODE HERE
        this.collageDimension = 4;
        this.tileDimension = 100;
        this.original = new Picture(filename);
        int w = tileDimension *collageDimension, h = tileDimension * collageDimension;
        this.collage = new Picture(w, h);
        for (int i = 0; i < collage.width(); i++) {
            for (int j = 0; j < collage.height(); j++) {
                int col = i * original.width() / w;
                int row = j * original.height() / h;
                Color color = original.get(col, row);
                collage.set(i, j, color);
            }
        }
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {

        // WRITE YOUR CODE HERE
        this.collageDimension = cd;
        this.tileDimension = td;
        this.original = new Picture(filename);
        int w = tileDimension * collageDimension, h = tileDimension * collageDimension;
        this.collage = new Picture(w, h);
        for (int i = 0; i < collage.width(); i++) {
            for (int j = 0; j < collage.height(); j++) {
                int col = i * original.width() / w;
                int row = j * original.height() / h;
                Color color = original.get(col, row);
                collage.set(i, j, color);
            }
        }

    }


    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {

        // WRITE YOUR CODE HERE
        return collageDimension;
    }


    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {

	    // WRITE YOUR CODE HERE
        return tileDimension;
    }


    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {

	    // WRITE YOUR CODE HERE
        return original;
    }


    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

	    // WRITE YOUR CODE HERE
        return collage;
    }
    

    /*
     * Display the original image
     * Assumes that original has been initialized
    */ 
    public void showOriginalPicture() {

        // WRITE YOUR CODE HERE
        original.show();
    }


    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {

        // WRITE YOUR CODE HERE
        collage.show();
    }


    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE

        // scale given image to match collage scale (scaled image will be store in original)
        Picture orig = new Picture(filename);
        Picture scaled = new Picture(tileDimension, tileDimension);
        for (int i = 0; i < tileDimension; i++) {
            for (int j = 0; j < tileDimension; j++) {
                int col = i * orig.width() / tileDimension;
                int row = j * orig.height() / tileDimension;
                Color color = orig.get(col, row);
                scaled.set(i, j, color);
            }
        }
        
        // copy original (scaled 'filename' image) to proper tile from given coordinates
        //  finds pixel values of top left corner of panel that is being replace
        int ycoord = collageRow * tileDimension, xcoord = collageCol * tileDimension; 
        int rcount = 0, ccount = 0;

        for (int i = xcoord; i < xcoord + tileDimension; i++) {
            if (ccount == tileDimension) ccount = 0;
            rcount = 0;
            for (int j = ycoord; j < ycoord + tileDimension; j++) {
                if (rcount == tileDimension)
                    rcount = 0;
                Color color = scaled.get(ccount, rcount);
                collage.set(i, j, color);
                rcount++;
            }
            ccount++;
        }
        
    }
    

    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {

        // WRITE YOUR CODE HERE
        
        // scale original (make image tile-sized)
        Picture scaled = new Picture(tileDimension, tileDimension);
        for (int i = 0; i < tileDimension; i++) {
            for (int j = 0; j < tileDimension; j++) {
                int col = i * original.width() / tileDimension;
                int row = j * original.height() / tileDimension;
                Color color = original.get(col, row);
                scaled.set(i, j, color);
            }
        }
        
        // after tiledimension size picture, reset counter for display and continue displaying one row at a time
        int w = tileDimension *collageDimension, h = tileDimension * collageDimension;
        int rcount = 0, ccount = 0;

        for (int i = 0; i < h; i++) {
            if (ccount == tileDimension) ccount = 0;
            rcount = 0;
            for (int j = 0; j < w; j++) {
                if (rcount == tileDimension)
                    rcount = 0;
                Color color = scaled.get(ccount, rcount);
                collage.set(i, j, color);
                rcount++;
            }
            ccount++;
        }

    }








    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see CS111 Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
    
    public void colorizeTile (String component,  int collageCol, int collageRow) {

	    // WRITE YOUR CODE HERE
    }
*/







    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void grayscaleTile (int collageCol, int collageRow) {

	    // WRITE YOUR CODE HERE
    }






    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {

        ArtCollage art = new ArtCollage(args[0]); 
        
        art.makeCollage();
        //art.replaceTile(args[1], 3, 3);
        //art.showOriginalPicture();
        art.showCollagePicture();
        

    }



}
