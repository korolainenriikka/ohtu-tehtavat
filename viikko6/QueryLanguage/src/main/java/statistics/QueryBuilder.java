package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new And(matcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String param) {
        this.matcher = new And(matcher, new HasAtLeast(value, param));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String param) {
        this.matcher = new And(matcher, new HasFewerThan(value, param));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }

    public Matcher build() {
        Matcher builtMatcher = this.matcher;
        this.matcher = new All();
        return builtMatcher;
    }
}
