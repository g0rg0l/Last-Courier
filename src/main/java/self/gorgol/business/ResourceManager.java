package self.gorgol.business;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceManager {

    @Getter
    private static final ResourceManager instance = new ResourceManager();

    private final Map<String, BufferedImage> imageResources = new HashMap<>();

    private ResourceManager() {  }

    // image resources -------------------------------------------------------------------------------------------------
    public static final String CHARACTER_SPRITES = "/player_sprites.png";

    public BufferedImage getResource(String path) {
        if (imageResources.containsKey(path)) {
            return imageResources.get(path);
        }

        try {
            BufferedImage resource = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            imageResources.put(path, resource);
            return resource;
        }
        catch (Exception e) {
            throw new RuntimeException("There is no image resource with path: " + path);
        }
    }

}
