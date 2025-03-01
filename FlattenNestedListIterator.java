/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 //wrong process but passess
 public class FlattenNestedListIterator implements Iterator<Integer> {
    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
        
    }
    
    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            if(!st.peek().hasNext())
            {
                st.pop();
            }
            else if((nextEl = st.peek().next()).isInteger())
            {
                return true;
            }
            else{
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

//Time Complexity (TC):
//next(): O(1)
//hasNext(): O(n), where n is the total number of integers in all nested lists (since each element is processed once).
//Space Complexity (SC): O(d), where d is the maximum depth of the nested list (due to the stack storing iterators).
