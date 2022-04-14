package GUIs;

import java.awt.*;
import java.net.URL;

/**
 * Helper class to enable images to be found once converting to .jar.
 * Links images using ClassLoader() and getResource()
 */
public class FindImages {


    public FindImages() {
    }

    private static URL getUrlForImage(String imageName, Class c){
        URL urlLogo = c.getResource("/data/" + imageName + ".png");
        return urlLogo;
    }

    public static Image getImageDisplay(String imageName){
        Image icon = Toolkit.getDefaultToolkit().getImage(getUrlForImage(imageName, FindImages.class));
        return icon;
    }

    public static Image getImageLogo(){
        Image icon = Toolkit.getDefaultToolkit().getImage(getUrlForImage("logo", FindImages.class));
        return icon;
    }
}
