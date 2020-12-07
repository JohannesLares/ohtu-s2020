package statistics;
import statistics.matcher.*;
import java.util.*;
public class QueryBuilder {
	List<Matcher> matcher;
	public QueryBuilder() {
		matcher = new ArrayList<>();
	}
	
	public Matcher build() {
		Matcher[] m = new Matcher[matcher.size()];
		if(matcher.size() == 0) return new All();
		return new And(matcher.toArray(m));
	}
	
	public Matcher buildOr() {
		Matcher[] m = new Matcher[matcher.size()];
		if(matcher.size() == 0) return new All();
		return new Or(matcher.toArray(m));
	}
	
	public QueryBuilder and(Matcher... matchers) {
		this.matcher.add(new And(matchers));
		return this;
	}
	
	public QueryBuilder hasAtLeast(int val, String cat) {
		this.matcher.add(new HasAtLeast(val, cat));
		return this;
	}
	
	public QueryBuilder hasFewerThan(int val, String cat) {
		this.matcher.add(new HasFewerThan(val, cat));
		return this;
	}
	
	public QueryBuilder not(Matcher m) {
		this.matcher.add(new Not(m));
		return this;
	}
	
	public QueryBuilder playsIn(String s) {
		this.matcher.add(new PlaysIn(s));
		return this;
	}
}
