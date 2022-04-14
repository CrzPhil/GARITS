package GUIs;

import java.net.URL;

public class FindImages {


    public FindImages() {
    }

    public static URL getUrlForImage(String imageName, Class c){
        URL urlLogo = c.getResource("/data/" + imageName + ".png");
        return urlLogo;
    }
}
