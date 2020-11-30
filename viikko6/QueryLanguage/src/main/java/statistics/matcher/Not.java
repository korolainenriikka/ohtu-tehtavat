package statistics.matcher;

import statistics.Player;

public class Not implements Matcher{
    private Matcher m;

    public Not(Matcher m) {
        this.m = m;
    }

    @Override
    public boolean matches(Player p) {
        if (this.m.matches(p)) {
            return false;
        }

        return true;
    }
}
