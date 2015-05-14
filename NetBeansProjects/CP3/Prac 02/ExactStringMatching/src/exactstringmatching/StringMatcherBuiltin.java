/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exactstringmatching;

/**
 *
 * @author lewi0146
 */
public class StringMatcherBuiltin extends StringMatcher {

    String target;
    String pattern;
    
    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public void setPattern(String pattern) {
       this.pattern = pattern;
    }

    @Override
    public int match() {
        return target.indexOf(pattern);
    }

    @Override
    public int getNumberOfSteps() {
        return -1;
    }
    
}
