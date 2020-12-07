package statistics.matcher;
import java.lang.reflect.Method;

import statistics.Player;
public class HasFewerThan implements Matcher {
	
	private String category;
	private int value;
	
	public HasFewerThan(int value, String cat) {
		this.value = value;
		this.category = "get"+Character.toUpperCase(cat.charAt(0))+cat.substring(1, cat.length());
	}
	
	@Override
	public boolean matches(Player p) {
		try {                                    
            Method method = p.getClass().getMethod(category);
            int playersValue = (Integer)method.invoke(p);
            return playersValue<value;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+category.substring(3, category.length()).toLowerCase());
        }  
	}

}
