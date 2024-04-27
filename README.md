# I'm done.
This game is not worth my time anymore, it's dead. I'm fucking done, i hope you all die.

# Candy

A lightweight CPS API for Spigot 1.8.x
## How to implement into a plugin
It's as simple as this to implement Candy into your plugin:

```java
import dev.lugami.candy.craftcandy.Candy;
import dev.lugami.candy.base.CandyBridge;

public class CandyExample extends JavaPlugin {

    public void onEnable() {
        new Candy(this).setup(); // Initializes Candy into your plugin
        CandyBridge.registerHandler(new SimpleCPSHandler()); // Registers an CPS handler
    }

}
```
And simple as this to make your own CPS handler:

```java
import dev.lugami.candy.base.CandyCPSHandler;
import dev.lugami.candy.base.CandyPlayer;

private static class SimpleCPSHandler implements CandyCPSHandler {

    private final Map<CandyPlayer, Integer> map = new HashMap<>();

    @Override
    public void onClick(CandyPlayer player) {
        if (!map.containsKey(player)) {
            map.put(player, 0);
        }
        if (player.getCPS() >= 20) {
            if (map.get(player) >= 25) { 
                player.getPlayer().kickPlayer("You're doing more than 20 CPS!");
                map.remove(player);
            } else {
                int i = map.get(player) + 1;
                map.put(player, i);
            }
        }
    }
}
```
## Building

There's no challenge to build Candy, simply run:

```bash
mvn clean install
```

